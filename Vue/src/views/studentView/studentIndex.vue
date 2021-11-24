<template>
  <el-container>
    <el-aside width="180px" > <student-aside/> </el-aside>
    <el-container >
      <el-header><student-header/></el-header>
      <el-main style="padding: 0px"> <router-view style="flex: 1;"></router-view> </el-main>
    </el-container>
  </el-container>
</template>

<script>
import StudentHeader from "@/components/studentHeader";
import StudentAside from "@/components/studentAside";
export default {
  name: "studentIndex",
  components: {StudentAside, StudentHeader},
  beforeRouteEnter:(to,from,next)=>{
    let name = sessionStorage.getItem("username");
    if(name===''||name===null){
      next({
        path:"/"
      })
    }else {
      next();
    }
  }
}
</script>

<style scoped>

</style>