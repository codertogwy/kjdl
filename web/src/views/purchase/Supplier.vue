<template>
  <TableForm title="供应商" :width="600" url="supplier" :columns="columns"/>
</template>
<script setup>
import TableForm from "@/components/TableForm.vue";
import {validateEmail, validateMobilePhone} from "@/utils/validate.js";

const columns = [

  {
    label: '企业名称',
    prop: 'name',
    required: true,
  },
  {
    label: '统一社会信用代码',
    prop: 'code',
    required: true,
  },
  {
    label: '法人',
    prop: 'legal'
  },
  {
    label: '所在地区',
    prop: 'region'
  },
  {
    label: '详细地址',
    prop: 'address'
  },
  {
    label: '联系人',
    prop: 'contact',
  },
  {
    label: '联系电话',
    prop: 'phone',
    rules: [{validator: validateMobilePhone}]
  },
  {
    label: '电子邮箱',
    prop: 'email',
    rules: [{validator: validateEmail}]
  },
  {
    label: '开户银行',
    prop: 'bank'
  },
  {
    label: '银行账号',
    prop: 'account'
  },
]

</script>
