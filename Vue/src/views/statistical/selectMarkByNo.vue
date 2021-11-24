<template>
  <div>
    <div >
      <div style="margin-top: 20px;margin-bottom: 20px">
        <el-input v-model="input" placeholder="请输入课程的编号" style="width: 40%; margin-left: 20%"/>
        <el-button type="primary" style="width: 100px" @click="searchDB">搜索</el-button>
      </div>
    </div>

    <div style="margin: 0 auto">
      <el-table :data="tableData" style="width: 80%;margin: 0 auto">
        <el-table-column prop="no" label="课程编号" width="180" />
        <el-table-column prop="course.name" label="课程名称" width="180" />
        <el-table-column prop="sno" label="学生学号" width="180" />
        <el-table-column prop="student.sname" label="学生姓名" width="180" />
        <el-table-column prop="grade" label="分数" />
      </el-table>
    </div>
  </div>

</template>

<script>
import axios from "axios";

export default {
  name: "selectMarkByNo",
  data(){
    return{
      input:null,
      tableData:[
        {
          no:"",
          sno:"",
          grade:"",
          student:{
            sname:"",
          },
          course:{
            name:"",
          }
        }
      ]
    }
  },
  methods:{
    searchDB(){
      console.log(this.input);
      if(this.input===null||this.input===""){
        this.$alert("请输入课程编号，课程编号不能为空","失败",{
          confirmButtonText:"确认",
        })
        this.tableData=null;
      }else {
        let courseNo=this.input;
        axios.get("URL/sc/selectByNo/"+courseNo).then((resp)=>{
          this.tableData=resp.data;
        })
      }

    },

  }

}
</script>

<style scoped>

</style>