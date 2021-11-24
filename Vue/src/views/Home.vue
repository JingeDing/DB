<template>
  <div class="home">

    <el-carousel height="151px" >
      <el-carousel-item > <img src="../assets/photo/zzu.jpg" ></el-carousel-item>
      <el-carousel-item> <img src="../assets/photo/header.jpg"></el-carousel-item>
      <el-carousel-item> <img src="../assets/photo/blue.jpg"></el-carousel-item>
    </el-carousel>
    &nbsp;&nbsp;&nbsp;
    <center style="margin-top: 20px">
      <h2>🌟欢迎登录教务管理系统🌟</h2>
      <br/>

      <p>&#128541 该项目用到的技术栈：</p>
      <p>&#127752前端: Vue+Element UI</p>
      <p>&#127919后端=> Spring Boot</p>
      <p>&#9889 前后端交互=>Axios</p>
      <p>&#9925数据库=>MySQL 5.7</p>
      <p>&#127759运行环境=>Centos 7.6</p>

      <br/>
      <a href="https://github.com/JingeDing/DB" target="_blank" style=" text-decoration:none;">项目源码地址</a>



      <dib style="  position: fixed;  bottom: 10px; right: 28%">
        <div>
          Contact: &nbsp; &nbsp;
          <img src="../assets/photo/email.png" width="18" height="18" />&nbsp;
          <a href="mailto:jingeding@gmail.com" style=" text-decoration:none;">Email</a>&nbsp;
          <img src="../assets/photo/github.png" width="18" height="18" />&nbsp;
          <a href="https://github.com/JingeDing" style=" text-decoration:none;">Github</a>
        </div>
        <dib>
          Copyright © 2021 JingeDing. All Rights Reserved.
        </dib>
      </dib>



    </center>

  </div>
</template>



<script >
import axios from "axios";

export default {
  name: "Home",
  data() {
    return {
      tableData: [
        {
          id: '',
          name: '',
          gender: '',
          age:``,
        }
      ],
    }
  },
  created() {
    axios.get("URL/student/queryAll").then((resp)=>{
      this.tableData=resp.data;
    })

  },
}
</script>


<style scoped>

</style>