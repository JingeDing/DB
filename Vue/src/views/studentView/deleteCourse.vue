<template>

  <el-table :data="tableData" style="width: 100%" border>
    <el-table-column prop="no" label="课程编号"  align="center" />
    <el-table-column prop="course.semester" label="学期" align="center"/>
    <el-table-column prop="course.name" label="课程名称"  align="center"/>
    <el-table-column prop="teacher.tname" label="教师" align="center"/>
    <el-table-column prop="course.credit" label="学分" align="center"/>
    <el-table-column prop="course.weekday" label="星期" align="center"/>
    <el-table-column prop="course.ctime" label="时间" align="center"/>
    <el-table-column align="center">
      <template #default="scope">
        <el-button size="small" type="primary" @click="handleDelete(scope.$index, scope.row)"
        >退课</el-button>
      </template>
    </el-table-column>
  </el-table>


  <el-dialog v-model="centerDialogVisible" title="提醒" width="30%" center>
    <span>
      <center> 您确定要退课？</center>
      <br/><center>
      {{this.no}}:[{{this.name}}]-授课教师:{{this.teacher}}
      </center>
    </span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="OnDelete"
        >确认</el-button
        >
      </span>
    </template>
  </el-dialog>


</template>

<script>

import axios from "axios";
import {ref} from "vue";

export default {
  name:"deleteCourse",
  inject:['reload'],
  data(){
    return{
      no:"",
      name:"",
      teacher:"",
      tableData:[
        {
          sno: "",
          no: "",
          grade: "",
          gpa: "",
          course: {
            no: "",
            name: "",
            credit: "",
            semester: "",
            tno: null,
            weekday: "",
            ctime: "",
            teacher: null
          },
          teacher: {
            tno: "",
            tname: "",
            title: null,
            email: null,
            dno: null,
            department: null
          }
        }
      ],

    }
  },
  methods:{
    handleDelete(index,row){
      console.log(index,row);
      this.no=row.no;
      this.name=row.course.name;
      this.teacher=row.teacher.tname;
      this.centerDialogVisible=true;
    },
    OnDelete(){
      this.centerDialogVisible=false;
      axios.get("URL/sc/delete/"+sessionStorage.getItem("username")+"/"+this.no).then((resp)=>{
        if(resp.data.code===200){
          this.$alert(this.name+":退课成功","成功",{
            confirmButtonText:"确认",
          })
        }else {
          this.$alert(this.name+":退课失败","失败",{
            confirmButtonText:"确认",
          })
        }
      })
      this.reload();
    }
  },

  created() {
    axios.get("URL/sc/selectBySno/"+sessionStorage.getItem("username")).then((resp)=>{
      this.tableData=resp.data;
    })
  },

  setup() {
    return {
      centerDialogVisible: ref(false),
    }
  },
}

</script>

<style scoped>

</style>