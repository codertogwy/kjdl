<template>
  <TableForm title="物资列表" :width="600" url="material" :columns="columns"/>
</template>
<script setup>
import TableForm from "@/components/TableForm.vue";

const columns = [
  {
    label: '物资名称',
    prop: 'name',
    required: true,
  },
  {
    label: '型号',
    prop: 'model',
  },
  {
    label: '规格',
    prop: 'specification',
  },
  {
    label: '材质',
    prop: 'material'
  },
  {
    label: '执行标准',
    prop: 'standard'
  },
]
</script>
