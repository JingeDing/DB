<template>
    <el-menu
        style="width: 180px;height:100%;position: fixed; "
        default-active=$router.path
        :default-openeds="defaultOpened"
        class="el-menu-vertical-demo"
        router
        background-color="#303133"
        text-color="#FFFFFF"
    >
      <!--      #F2F6FC-->
      <!-- padding-bottom:9999px; margin-bottom:-9999px;   -->
      <el-menu-item :index="$router.options.routes[2].children[0].path">
        <i class="el-icon-s-home"></i>{{$router.options.routes[2].children[0].name}}
      </el-menu-item>
      <el-menu-item :index="$router.options.routes[2].children[1].path">
        <i class="el-icon-user"></i>{{$router.options.routes[2].children[1].name}}
      </el-menu-item>
      <el-menu-item :index="$router.options.routes[2].children[2].path">
        <i class="el-icon-search"></i>{{$router.options.routes[2].children[2].name}}
      </el-menu-item>
      <el-menu-item :index="$router.options.routes[2].children[3].path">
        <i class="el-icon-plus"></i>{{$router.options.routes[2].children[3].name}}
      </el-menu-item>
      <el-menu-item :index="$router.options.routes[2].children[4].path">
        <i class="el-icon-refresh"></i>{{$router.options.routes[2].children[4].name}}
      </el-menu-item>
      <el-menu-item :index="$router.options.routes[2].children[5].path">
        <i class="el-icon-time"></i> {{$router.options.routes[2].children[5].name}}
      </el-menu-item>
      <el-menu-item :index="$router.options.routes[2].children[6].path">
        <i class="el-icon-orange"></i>{{$router.options.routes[2].children[6].name}}
      </el-menu-item>
    </el-menu>

</template>
<script>
export default {
  name: "studentAside",
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