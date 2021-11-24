<template>

  <div style="margin-left: 15px">
    <el-descriptions
        class="margin-top"
        title="个人信息"
        :column="2"
        :size="medium"
        border
    >
      <el-descriptions-item>
        <template #label>
          <i class="el-icon-user"></i>
          姓名
        </template>
        {{this.studentData.sname}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <i class="el-icon-star-off"></i>
          学号
        </template>
        {{ userId }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label v-if="this.studentData.sex==='男'">
          <i class="el-icon-male"></i>
          性别
        </template>
        <template #label v-else>
          <i class="el-icon-female"></i>
          性别
        </template>
        {{this.studentData.sex}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <i class="el-icon-postcard"></i>
          班级编号
        </template>
        {{this.studentData.cno}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <i class="el-icon-school"></i>
          学校
        </template>
        郑州大学
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <i class="el-icon-tickets"></i>
          院系
        </template>
        {{this.studentDepartment}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <i class="el-icon-collection-tag"></i>
          身份
        </template>
        本科生
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <i class="el-icon-zoom-in"></i>
          班级名称
        </template>
        {{this.studentClassName}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <i class="el-icon-timer"></i>
          学制
        </template>
        {{this.studentMajorYear}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <i class="el-icon-time"></i>
          年级
        </template>
        {{this.studentEnterYear}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <i class="el-icon-coin"></i>
          专业
        </template>
        <el-tag>{{this.studentMajor}}</el-tag>
      </el-descriptions-item>
    </el-descriptions>


  </div>



</template>

<script lang="ts">
import axios from "axios";

export default {
  name: "StudentInfo",
  data() {
    return {
      size: '',
      userId:"",
      studentData: [
        {
          sno: "",
          sname: "",
          sex: "",
          cno: "",
          department: {
            dno: "",
            dname: ""
          },
          classItem: {
            cno: "",
            cname: "",
            year: "",
            major: "",
            dno: null,
            department: null,
            cyear: "4"
          }
        }
      ],
      studentDepartment:"",
      studentClassName:"",
      studentEnterYear:"",
      studentMajor:"",
      studentMajorYear:"",
    }
  },
  created() {
    this.userId=sessionStorage.getItem("username");
    axios.get("URL/student/queryById/"+this.userId).then((resp)=>{
      this.studentData=resp.data;
      this.studentDepartment=resp.data.department.dname;
      this.studentClassName=resp.data.classItem.cname;
      this.studentEnterYear=resp.data.classItem.year;
      this.studentMajor=resp.data.classItem.major;
      this.studentMajorYear=resp.data.classItem.cyear;
    })
  }
}
</script>

<style scoped>

</style>