<template>
  <el-dialog
      v-model="visible"
      :title="title"
      :width="width"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      draggable
      destroy-on-close
  >
    <div v-loading="loading" element-loading-text="正在提交...">
      <slot/>
    </div>
    <template #header>
      <slot name="header"/>
    </template>
    <template #footer>
      <slot name="footer">
        <el-button @click="emit('update:modelValue', false)">关闭</el-button>
        <el-button type="primary" :loading="loading" @click="emit('submit')">提交</el-button>
      </slot>
    </template>
  </el-dialog>
</template>

<script setup>
import {computed} from "vue";

const props = defineProps(['modelValue', 'loading', 'title', 'width'])
const emit = defineEmits(['update:modelValue', 'submit'])

const visible = computed({
  get() {
    return props.modelValue
  },
  set(value) {
    emit('update:modelValue', value)
  }
})

</script>
