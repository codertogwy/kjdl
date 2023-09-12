<template>
  <el-config-provider :locale="config.locale" :button="config.button" :message="config.message">
    <router-view/>
  </el-config-provider>
</template>

<script setup>
import {reactive} from "vue";
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'

const config = reactive({
  locale: zhCn,
  button: {
    autoInsertSpace: true
  },
  message: {
    max: 3
  }
})
</script>