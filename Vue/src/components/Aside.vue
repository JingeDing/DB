<template>
      <el-menu
        style="width: 180px"
        default-active=$router.path
        :default-openeds="defaultOpened"
        class="el-menu-vertical-demo"
        router
        >
        <el-menu-item :index="$router.options.routes[1].children[0].path"> <i class="el-icon-s-home"></i>教务系统主页</el-menu-item>
        <el-sub-menu v-for="(items,index) in $router.options.routes.slice(3) " :index="index+''">
          <template #title><i class="el-icon-menu"></i>{{items.name}}</template>
          <el-menu-item v-for="(item,index2) in items.children" :index="item.path">
            <i class="el-icon-right"></i>{{item.name}}
            </el-menu-item>
        </el-sub-menu>
      </el-menu>
</template>

<script>
    export default {
      name: "Aside",
      data () {
        return {
          defaultOpened: ['0'],
          uniqueOpened: false
        }
      }

    }
 </script>

<style scoped>
</style>