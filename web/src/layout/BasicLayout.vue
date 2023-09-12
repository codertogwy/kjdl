<template>
  <el-container class="layout-container">
    <el-header height="auto">
      <Header/>
    </el-header>
    <el-container>
      <el-aside width="auto">
        <Aside/>
      </el-aside>
      <el-main class="layout-main">
        <el-card class="layout-main-card">
          <router-view/>
        </el-card>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import Aside from "@/layout/components/Aside.vue";
import Header from "@/layout/components/Header.vue";
</script>

<style scoped lang="less">
.layout-container {
  height: 100%;

  .layout-main {
    background-color: #f4f4f5;

    .layout-main-card {
      min-height: calc(100% - 2px)
    }
  }
}
</style>