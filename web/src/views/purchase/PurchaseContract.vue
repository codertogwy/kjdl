<template>
  <TableForm title="采购合同" url="purchaseContract" :columns="columns" :width="1280">
    <template #form="{form}">
      <el-divider content-position="left">货款明细</el-divider>
      <TableEdit v-model="form['purchasePaymentList']" :columns="paymentColumns"></TableEdit>
      <el-divider content-position="left">供货明细</el-divider>
      <TableEdit v-model="form['purchaseDetailList']" :columns="detailColumns"></TableEdit>
    </template>
  </TableForm>
</template>
<script setup>
import TableForm from "@/components/TableForm.vue";
import TableEdit from "@/components/TableEdit.vue";
import {getCurrentInstance, reactive, ref} from "vue";

const globalProperties = getCurrentInstance().appContext.config.globalProperties
const post = globalProperties.$post

const columns = [
  {
    label: '合同号',
    prop: 'code',
    span: 16,
  },
  {
    label: '签订日期',
    prop: 'date',
    type: 'date',
    span: 8,
  },
  {
    label: '合同金额',
    prop: 'amount',
    type: 'number',
    span: 8,
  },
  {
    label: '税率',
    prop: 'rate',
    span: 8,
    type: 'select',
    selectOptions: [{value: 13, label: 13}, {value: 9, label: 9}, {value: 6, label: 6}, {value: 0, label: 0}]
  },
  {
    label: '不含税金额',
    prop: 'noTax',
    type: 'number',
    span: 8,
  },
  {
    label: '电子合同',
    prop: 'fileList',
    type: 'upload',
  },
  {
    label: '收到',
    prop: 'receive',
    onlyTable: true,
  },
]

const paymentColumns = [
  {
    label: '计划支付日期',
    prop: 'date',
    type: 'date',
    required: true,
  },
  {
    label: '计划支付金额',
    prop: 'amount',
    type: 'number',
    rules: [{
      required: true,
      message: `请输入计划支付金额(数字)`,
    }]
  },
  {
    label: '支付比例',
    prop: 'proportion',
    required: true,
  },
  {
    label: '支付类型',
    prop: 'type',
    required: true,
  },
  {
    label: '支付条件',
    prop: 'conditions',
    required: true,
  },
  {
    label: '币种',
    prop: 'currency',
    required: true,
  },
  {
    label: '备注',
    prop: 'remark',
  },
]

const a = ref([])


let r = []

await post(`/material/findAll`).then(data => {
  const res = []
  if (data) {
    data.forEach(e => {
      let label = e['name']
      if (e['model'])
        label += `-${e['model']}`
      if (e['specification'])
        label += `-${e['specification']}`
      if (e['material'])
        label += `-${e['material']}`
      if (e['standard'])
        label += `-${e['standard']}`
      res.push({value: 1, label})
    })
    r = res
  }
})

const detailColumns = [
  {
    label: '物资',
    prop: 'material',
    type: 'select',
    selectOptions: r
  },
  {
    label: '计量单位',
    prop: 'unit',
  },
  {
    label: '订货数量',
    prop: 'quantity',
  },
  {
    label: '单价',
    prop: 'price',
  },
  {
    label: '金额',
    prop: 'amount',
  },
  {
    label: '申请单号',
    prop: 'code',
  },
  {
    label: '计量方式',
    prop: 'mode',
  },
  {
    label: '品牌及型号说明',
    prop: 'description',
  },
  {
    label: '备注',
    prop: 'remark',
  },
]
const getMaterial = () => {

}

getMaterial()
</script>
