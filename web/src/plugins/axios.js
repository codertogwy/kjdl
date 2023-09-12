import axios from 'axios'
import router from "./router";
import {ElMessage} from "element-plus";
import {getToken} from "@/utils/session.js";

const service = axios.create({
    baseURL: '/api',
    timeout: 6 * 1000,
})

service.interceptors.request.use(
    (config) => {
        const token = getToken()
        if (token) {
            config.headers.token = token
        }
        return config
    },
    (error) => {
        return Promise.reject(error)
    }
)
service.interceptors.response.use(
    (response) => {
        if (response.status === 200) {
            switch (response.data.code) {
                case 200:
                    return response.data.data
                case 401:
                    ElMessage.error("认证失效")
                    router.push('/login')
                    ElMessage.error("权限不足")
                    break
                case 403:
                    ElMessage.error("权限不足")
                    break
                default:
                    ElMessage.error("服务出错了，请联系管理员")
            }
        }
    },
    (error) => {
        return Promise.reject(error)
    }
)

export default {
    install(app) {
        app.config.globalProperties.$post = service.post
        app.config.globalProperties.$get = service.get
    }
}
