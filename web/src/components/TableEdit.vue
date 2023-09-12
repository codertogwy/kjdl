<template>
  <el-table
      :data="modelValue"
      border
      show-overflow-tooltip
  >
    <el-table-column
        v-for="column in columns"
        :label="column.label"
        :prop="column.prop"
        :width="column.width"
    />
    <el-table-column fixed="right" label="操作" width="130" header-align="center" align="center">
      <template #default="scope">
        <el-button link type="primary" @click="edit(scope.$index)">编辑</el-button>
        <el-divider direction="vertical"/>
        <el-popconfirm @confirm="modelValue.splice(scope.$index,1)" title="确定删除数据吗">
          <template #reference>
            <el-button link type="danger">删除</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>
  <el-button class="add-button" @click="edit(null)">添加明细</el-button>
  <el-dialog
      v-model="state.visible"
      :title="'title'"
      :width="600"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      draggable
      destroy-on-close
  >
    <BaseForm ref="formRef" :form="state.form" :columns="columns"/>

    <template #footer>
      <el-button @click="state.visible=false">关闭</el-button>
      <el-button type="primary" @click="save()">保存</el-button>
    </template>
  </el-dialog>
</template>
<script setup>
import {reactive, ref} from "vue";
import BaseForm from "@/components/BaseForm.vue";

const props = defineProps(['modelValue', 'columns'])
const emit = defineEmits(['update:modelValue'])

const formRef = ref()
const state = reactive({
  visible: false,
  index: 0,
  form: {}
})


const edit = (index) => {
  state.index = index
  state.form = null === index ? {} : {...props.modelValue[index]}
  state.visible = true
}

const save = () => {
  formRef.value.validate(valid => {
    if (valid) {
      if (null === state.index) {
        if (!props.modelValue) {
          emit('update:modelValue', [state.form])
        } else {
          props.modelValue.push(state.form)
        }
      } else {
        props.modelValue[state.index] = state.form
      }
      state.visible = false
    }
  })
}


</script>

<style scoped lang="less">
.add-button {
  margin-top: 4px;
  width: 100%;
}
</style>