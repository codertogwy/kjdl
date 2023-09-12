<template>
  <el-menu
      class="container"
      active-text-color="#ffd04b"
      background-color="#545c64"
      text-color="#fff"
      :collapse="state.isCollapse"
      router
      :default-active="state.active"
  >
    <template v-for="(firstMenu) in menu.children">
      <!--一级菜单-->
      <el-sub-menu v-if="firstMenu.children" :index="firstMenu.path">
        <template #title>
          <el-icon>
            <component :is="firstMenu.meta['icon']"/>
          </el-icon>
          <span>{{ firstMenu.meta['title'] }}</span>
        </template>
        <!--二级页面-->
        <el-menu-item v-for="(secondMenu) in firstMenu.children" :index="secondMenu.path">
          <el-icon>
            <component :is="secondMenu.meta['icon']"/>
          </el-icon>
          <span>{{ secondMenu.meta['title'] }}</span>
        </el-menu-item>
      </el-sub-menu>
      <!--一级页面-->
      <el-menu-item v-else :index="firstMenu.path">
        <el-icon>
          <component :is="firstMenu.meta['icon']"/>
        </el-icon>
        <span>{{ firstMenu.meta['title'] }}</span>
      </el-menu-item>
    </template>
    <!--折叠展开-->
    <div class="collapseContainer" @click="state.isCollapse=!state.isCollapse">
      <div class="collapse">
        <el-icon size="18px">
          <component :is="state.isCollapse?'Expand':'Fold'"/>
        </el-icon>
      </div>
    </div>
  </el-menu>
</template>

<script setup>
import {computed, reactive} from "vue";
import {useRoute, useRouter} from "vue-router";

const route = useRoute()

const state = reactive({
  isCollapse: false,
  active: computed(() => route.path)
})

const menu = useRouter().getRoutes().filter(obj => obj.path === '/')[0]
</script>

<style scoped>
.container {
  height: 100%;
}

/*noinspection CssUnusedSymbol*/
.container:not(.el-menu--collapse) {
  width: 250px;
}

.collapseContainer {
  height: 56px;
  width: 100%;
  position: absolute;
  bottom: 0;
}

.collapse {
  line-height: 56px;
  height: 56px;
  width: 63px;
  text-align: center;
  color: var(--el-menu-text-color);
}


.collapseContainer:hover {
  background-color: var(--el-menu-hover-bg-color);
}

.collapseContainer:hover .collapse {
  color: var(--el-menu-active-color);
}
</style>