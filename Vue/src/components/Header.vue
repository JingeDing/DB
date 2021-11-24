<template>
<div style="height: 50px;line-height: 50px;border-bottom: 1px solid  #ccc; display: flex ">
  <div style="width: 100px;margin-left: 20px"><img src="../assets/zzulogo2.jpg" style="width: 160px;height: 49px"> </div>
  <div style="flex: 1"><center style="color: #409EFF">教务管理系统</center></div>
  <div style="width: 100px">
    <el-dropdown>
  <span class="el-dropdown-link">
  管理员<i class="el-icon-arrow-down el-icon--right"></i>
  </span>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item @click="logout">退出</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</div>
</template>

<script>
export default {
  name: "Header",
  methods:{
    logout(){
      sessionStorage.clear();
      this.$router.push("/");
    }
  }
}
</script>

<style scoped>

</style>