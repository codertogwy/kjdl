<template>
  <el-form
      ref="formRef"
      :model="form"
      label-position="right"
      label-width="auto"
      status-icon
      :inline="search"
  >
    <slot name="form" :form="form">
      <!--搜索-->
      <template v-if="search" v-for="column in columns">
        <el-form-item
            v-if="!column.onlyTable"
            :label="column.label"
        >
          <el-input-number
              v-if="'number'===column.type"
              v-model.number="form[column.prop]"
              controls-position="right"
          />
          <el-date-picker
              v-else-if="'date'===column.type"
              v-model="form[column.prop]"
              value-format="YYYY-MM-DD"
          />
          <el-select
              v-else-if="'select'===column.type"
              v-model="form[column.prop]"
          >
            <el-option
                v-for="option in column.selectOptions"
                :key="option.value"
                :label="option.label"
                :value="option.value"
            />
          </el-select>
          <el-input
              v-else
              v-model="form[column.prop]"
          />
        </el-form-item>
      </template>
      <!--Form-->
      <el-row v-else :gutter="20">
        <template v-for="column in columns">
          <el-col v-if="!column.onlyTable" :span="column.span">
            <el-form-item
                :label="column.label"
                :prop="column.prop"
                :rules="rules(column)"
            >
              <el-input-number
                  v-if="'number'===column.type"
                  v-model.number="form[column.prop]"
                  controls-position="right"
              />
              <el-date-picker
                  v-else-if="'date'===column.type"
                  v-model="form[column.prop]"
                  value-format="YYYY-MM-DD"
              />
              <el-select
                  v-else-if="'select'===column.type"
                  v-model="form[column.prop]"
              >
                <el-option
                    v-for="option in column.selectOptions"
                    :key="option.value"
                    :label="option.label"
                    :value="option.value"
                />
              </el-select>
              <el-upload
                  v-else-if="'upload'===column.type"
                  v-model:file-list="form[column.prop]"
                  multiple
                  :http-request="upload"
                  :on-success="success"
                  :on-preview="preview"
                  class="upload"
              >
                <el-button type="primary">点击上传</el-button>
              </el-upload>
              <el-input
                  v-else
                  v-model="form[column.prop]"
              />
            </el-form-item>
          </el-col>
        </template>
      </el-row>
    </slot>
  </el-form>
</template>
<script setup>
import {ref} from "vue";
import {downloadUrl, uploadFile} from "@/utils/cos.js";

defineProps(['form', 'columns', 'search'])
const formRef = ref()

const upload = async (options) => {
  await uploadFile(options.file, options.onProgress, options.onError, data => {
    options.onSuccess(data)
  })
}
const success = (response, uploadFile) => {
  if (response) {
    uploadFile.cos = response
  }
}

const preview = (uploadFile) => {
  downloadUrl(uploadFile.cos, uploadFile.name)
}

const rules = (column) => {
  if (column.required) {
    let rules = [
      {
        required: true,
        message: `请输入${column.label}`,
      }
    ]
    if (column.rules) {
      rules = rules.concat(column.rules)
    }
    return rules
  }
  return column.rules ? column.rules : []
}

const validate = (fun) => {
  formRef.value.validate(fun)
}

defineExpose({validate})
</script>
<style scoped>
.upload {
  width: 100%;
}
</style>