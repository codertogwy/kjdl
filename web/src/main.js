import {createApp} from 'vue'
import App from './App.vue'

//https://element-plus.gitee.io/zh-CN/
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
//https://router.vuejs.org/zh/index.html
import router from '@/plugins/router';

import icons from '@/plugins/icons.js';
import axios from '@/plugins/axios.js';

import '@/styles/index.less'



const app = createApp(App)
app.use(ElementPlus).use(router).use(icons).use(axios)
app.mount('#app')
