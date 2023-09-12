<template>

  <el-button type="primary" @click="emit('edit')">新增</el-button>
  <el-button type="primary" @click="exportXlsx()">全部导出</el-button>

  <div class="space-vertical"/>
  <el-table
      :data="data.slice((state.currentPage - 1) * state.pageSize, state.currentPage * state.pageSize)"
      border
      highlight-current-row
      row-key="id"
      show-overflow-tooltip
      v-loading="loading"
      element-loading-text="正在加载..."
      height="440"
  >
    <el-table-column v-for="column in columns" :label="column.label" :prop="column.prop" :width="column.width"/>
    <el-table-column fixed="right" label="操作" width="130" header-align="center" align="center">
      <template #default="scope">
        <el-button link type="primary" @click="emit('edit',scope.row)">编辑</el-button>
        <el-divider direction="vertical"/>
        <el-button link type="danger" @click="emit('del',scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <div class="space-vertical"/>
  <el-pagination
      layout="sizes,prev,pager,next,jumper,->,total"
      :total="data.length"
      background
      :page-sizes="[10,20,50,100,9999]"
      v-model:page-size="state.pageSize"
      v-model:current-page="state.currentPage"
  />
</template>

<script setup>
import {reactive, toRaw} from "vue";

import * as xlsx from "xlsx";

const props = defineProps(['data', 'columns', 'loading'])
const emit = defineEmits(['edit', 'del'])
const state = reactive({
  pageSize: 10,
  currentPage: 1,
})

const exportXlsx = () => {
  const columns = toRaw(props.columns)
  const data = toRaw(props.data).map(m => {
    const d = {}
    columns.forEach(c => d[c.label] = m[c.prop])
    return d
  })
  const ws = xlsx.utils.json_to_sheet(data)
  const wb = xlsx.utils.book_new()
  xlsx.utils.book_append_sheet(wb, ws, "Data");
  xlsx.writeFileXLSX(wb, '导出.xlsx')
}

</script>
