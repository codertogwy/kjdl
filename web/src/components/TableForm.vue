<template>
  <BaseForm
      class="search"
      :style="{height:state.isExpand?'auto':'50px'}"
      :search="true"
      :form="state.search"
      :columns="columns"
  />
  <div class="operation">
    <slot name="operation">
      <el-button type="primary" @click="edit(null)">新增</el-button>
      <el-button type="primary" @click="exportXlsx()">全部导出</el-button>
    </slot>
    <div class="right">
      <el-button type="primary" @click="getData" :loading="state.tableLoading" icon="Search">查询</el-button>
      <el-button @click="getData" :loading="state.tableLoading" icon="RefreshRight">重置</el-button>
      <el-button text bg type="info" @click="state.isExpand=!state.isExpand">{{ state.isExpand ? '收 起' : '展 开' }}
        <el-icon>
          <component :is="state.isExpand?'ArrowUp':'ArrowDown'"/>
        </el-icon>
      </el-button>
    </div>
  </div>
  <el-table
      :data="state.data.slice((state.currentPage - 1) * state.pageSize, state.currentPage * state.pageSize)"
      border
      highlight-current-row
      row-key="id"
      show-overflow-tooltip
      height="440"
      v-loading="state.tableLoading"
      element-loading-text="正在加载..."
  >
    <template v-for="column in columns">
      <el-table-column
          v-if="'upload'===column.type"
          :label="column.label"
          :prop="column.prop"
          :width="column.width"
      >
        <template #default="{ row }">
          <template v-for="(file,index) in row[column.prop]">
            <el-link @click="downloadUrl(file.cos,file.name)" type="success">{{ file.name }}</el-link>
            <br v-if="index!==row[column.prop].length"/>
          </template>
        </template>
      </el-table-column>
      <el-table-column
          v-else-if="!column.onlyForm"
          :label="column.label"
          :prop="column.prop"
          :width="column.width"
      />
    </template>
    <el-table-column fixed="right" label="操作" width="130" header-align="center" align="center">
      <template #default="scope">
        <el-button link type="primary" @click="edit(scope.row)">编辑</el-button>
        <el-divider direction="vertical"/>
        <el-popconfirm @confirm="del(scope.row)" title="确定删除数据吗">
          <template #reference>
            <el-button link type="danger">删除</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
      class="pagination"
      layout="sizes,prev,pager,next,jumper,->,total"
      :total="state.data.length"
      background
      :page-sizes="[10,20,50,100,9999]"
      v-model:page-size="state.pageSize"
      v-model:current-page="state.currentPage"
  />

  <el-dialog
      v-model="state.visible"
      :title="title"
      :width="width"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      draggable
      destroy-on-close
  >
    <BaseForm ref="formRef" :form="state.form" :columns="columns"/>
    <slot name="form" :form="state.form"/>
    <template #footer>
      <el-button @click="state.visible=false">关闭</el-button>
      <el-button type="primary" :loading="state.formLoading" @click="submit()">提交</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import {getCurrentInstance, reactive, ref, toRaw} from "vue";
import * as xlsx from "xlsx";
import BaseForm from "@/components/BaseForm.vue";
import {downloadUrl} from "@/utils/cos.js";
import {YMD} from "@/utils/format.js";

const props = defineProps(['url', 'columns', 'title', 'width'])

const globalProperties = getCurrentInstance().appContext.config.globalProperties
const message = globalProperties.$message
const post = globalProperties.$post


const formRef = ref()
const state = reactive({
  // search
  isExpand: false,
  search: {},
  // table
  data: [],
  tableLoading: false,
  // pagination
  pageSize: 10,
  currentPage: 1,
  // form
  form: {},
  visible: false,
  formLoading: false,
})

const edit = (row) => {
  state.form = row ? {...row} : {}
  state.visible = true
}

const submit = () => {
  formRef.value.validate(valid => {
    if (valid) {
      state.formLoading = true
      post(`/${props.url}/save`, toRaw(state.form)).then(() => {
        message.success('提交成功')
        state.formLoading = false
        state.visible = false
        getData()
      })
    }
  })
}
const del = (row) => {
  state.tableLoading = true
  post(`/${props.url}/delete`, toRaw(row)).then(() => {
    message.success('删除成功')
    getData()
  })
}
const getData = () => {
  state.tableLoading = true
  post(`/${props.url}/findAll`, toRaw(state.search)).then(res => {
    state.data = [...res]
    state.tableLoading = false
  })
}

const exportXlsx = () => {
  const columns = toRaw(props.columns)
  const data = toRaw(state.data).map(m => {
    const d = {}
    columns.forEach(c => d[c.label] = m[c.prop])
    return d
  })
  const book = xlsx.utils.book_new()
  xlsx.utils.book_append_sheet(book, xlsx.utils.json_to_sheet(data), "导出")
  xlsx.writeFileXLSX(book, `${YMD()}-${props.title}导出.xlsx`)
}

getData()
defineExpose({getData})
</script>

<style scoped lang="less">
.search {
  overflow: hidden;
}

.operation {
  margin-bottom: 15px;

  .right {
    float: right;
  }
}

.pagination {
  margin-top: 15px;
}

</style>

