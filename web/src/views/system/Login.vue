<template>
  <div class="login-container">
    <div class="login">
      <h2>坤江电力信息化管理系统</h2>
      <el-form :model="form" :rules="rules" ref="loginRef">
        <el-form-item prop="username">
          <el-input v-model="form.username" placeholder="账户" @keyup.enter="login">
            <template #prepend>
              <el-icon>
                <Component is="User"/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" v-model="form.password" placeholder="密码" @keyup.enter="login">
            <template #prepend>
              <el-icon>
                <Component is="Lock"/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button class="login-button" type="primary" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="footer">
      <a href="https://beian.miit.gov.cn/" target="_blank">辽ICP备2021000991号-1</a>
    </div>
  </div>
</template>
<script setup>
import {ref, reactive, getCurrentInstance, toRaw} from 'vue';
import {useRouter} from 'vue-router';
import {setName, setToken} from "@/utils/session.js";


const globalProperties = getCurrentInstance().appContext.config.globalProperties
const message = globalProperties.$message
const post = globalProperties.$post

const router = useRouter()
const loginRef = ref()
const form = reactive({
  username: '',
  password: ''
})

const rules = {
  username: [{required: true, message: '请输入账户', trigger: 'blur'}],
  password: [{required: true, message: '请输入密码', trigger: 'blur'}]
}

const login = () => {
  loginRef.value.validate(valid => {
    if (valid) {
      post('/login', toRaw(form)).then(res => {
        if (res.status) {
          setToken(res.token)
          setName(res.name)
          message.success(`欢迎回来，${res.name}`)
          router.push('/')
        } else {
          message.error(res.message)
        }
      })
    }
  })
}

</script>
<style scoped lang="less">
.login-container {
  background: #f4f4f5;
  height: 100%;
  text-align: center;

  .login {
    width: 333px;
    margin: 0 auto;
    padding-top: 222px;

    .login-button {
      width: 100%;
    }

  }

  .footer {
    position: absolute;
    bottom: 60px;
    width: 100%;
  }
}
</style>