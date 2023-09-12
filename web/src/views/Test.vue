<script setup>
import TableForm from "@/components/TableForm.vue";

const columns = [
  {
    label: '物资名称',
    prop: 'name',
    required: true,
  },
  {
    label: '物资名称',
    prop: 'name',
    required: true,
    onlyForm: true,
    onlyTable: true,
  },
]
</script>

<template>
  <TableForm title="物资列表" :width="600" url="material" :columns="columns"/>
</template>
