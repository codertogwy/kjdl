<template>
  <div class="headerContainer">
    <div class="left">
      <div class="logo">
        <el-image :src="logo"/>
      </div>
      <el-breadcrumb separator-icon="ArrowRight">
        <el-breadcrumb-item>
          <el-icon>
            <component is="Location"/>
          </el-icon>
        </el-breadcrumb-item>
        <el-breadcrumb-item v-for="item in breadcrumb"><b>{{ item.meta.title }}</b></el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="right">
      <div class="hover-trigger" @click="toggle()">
        <el-icon class="icon" size="18px">
          <component is="FullScreen"/>
        </el-icon>
      </div>
      <div class="hover-trigger">
        <el-dropdown style="height: 100%" :teleported="false">
          <el-icon class="icon" size="18px">
            <component is="Switch"/>
          </el-icon>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="no">🇨🇳<span class="dropdown-text">简体中文</span></el-dropdown-item>
              <el-dropdown-item @click="no">🇭🇰<span class="dropdown-text">繁体中文</span></el-dropdown-item>
              <el-dropdown-item @click="no">🇺🇸<span class="dropdown-text">English</span></el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>

      <div class="hover-trigger">
        <el-dropdown placement="bottom-end" style="height: 100%" :teleported="false">
          <el-space class="icon">
            <el-avatar :size="25" src="https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png"/>
            <span>刘克轩</span>
          </el-space>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="go('/workbench')">
                <el-icon>
                  <component is="House"/>
                </el-icon>
                <span class="dropdown-text">个人主页</span>
              </el-dropdown-item>
              <el-dropdown-item @click="go('/setting')">
                <el-icon>
                  <component is="Setting"/>
                </el-icon>
                <span class="dropdown-text">账号设置</span>
              </el-dropdown-item>
              <el-dropdown-item @click="logout" divided>
                <el-icon>
                  <component is="SwitchButton"/>
                </el-icon>
                <span class="dropdown-text">退出</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>

<script setup>
import logo from '@/assets/logo.png'
import {useRoute, useRouter} from "vue-router";
import {computed, getCurrentInstance} from "vue";
import {removeName, removeToken} from "@/utils/session.js";

const {proxy} = getCurrentInstance()
const router = useRouter()
const route = useRoute()
const breadcrumb = computed(() => route.matched.slice(1))

const no = () => {
  proxy.$message.info('待开发')
}
const go = path => {
  router.push(path)
}
const logout = () => {
  go('/login')
  removeToken()
  removeName()
  proxy.$message.success('退出成功')
}

const toggle = () => {

}
</script>

<style scoped lang="less">
.headerContainer {
  height: 49px;
  border-bottom: 1px solid var(--el-border-color);
  margin: 0 -20px;
  padding: 0 20px;

  .left {
    height: 100%;
    display: inline-flex;
    align-items: center;

    .logo {
      width: 230px;
      margin-right: 20px;
    }

  }

  .right {
    height: 100%;
    float: right;
    display: flex;

    .icon {
      align-items: center;
      height: 100%;
      padding: 0 10px;
    }
  }

  .hover-trigger {
    height: 100%;
  }

  .hover-trigger :hover {
    background: #f6f6f6;
    cursor: pointer;
  }

  .dropdown-text {
    margin-left: 8px;
  }
}


.icon {
}
</style>