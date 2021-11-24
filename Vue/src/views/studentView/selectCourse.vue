<template>
  <el-table
      :data="tableData"
      style="width: 100%"
      border
  >
    <el-table-column prop="course.no" label="课程编号" width="150" align="center" sortable/>
    <el-table-column prop="course.semester" label="课程学期" width="120" align="center" />
    <el-table-column prop="course.name" label="课程名称" width="120" align="center"/>
    <el-table-column prop="teacher.tname" label="授课教师" width="120" align="center"/>
    <el-table-column prop="course.credit" label="课程学分" width="120" align="center"/>
    <el-table-column prop="course.weekday" label="星期数" width="100" align="center"/>
    <el-table-column prop="course.ctime" label="时间" width="100" align="center"/>
    <el-table-column align="center">
      <template #header>
        <el-input v-model="searchByName" size="mini" placeholder="输入课程名称查找" @keyup.enter="searchData" />
      </template>
      <template #default="scope">
        <el-button size="small" type="primary" @click="handleSelect(scope.$index, scope.row)"
        >点击选修</el-button
        >
      </template>
    </el-table-column>
  </el-table>



  <el-dialog v-model="centerDialogVisible" title="提醒" width="30%" center>
    <span>
      <center> 请您确认是否选修:</center>
      <br/><center>
      {{this.no}}:[{{this.name}}]-授课教师:{{this.teacher}}
      </center>
    </span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="OnSelect"
        >确认</el-button
        >
      </span>
    </template>
  </el-dialog>


</template>

<script lang="ts">
import axios from "axios";
import { defineComponent, ref } from 'vue'

export default {
  name: "selectCourse",
  inject:['reload'],
  data() {
    return {
      no:"",
      name:"",
      teacher:"",
      semester:"",
      weekday:"",
      ctime:"",
      tableData: [
        {
          sno: null,
          no: null,
          grade: null,
          gpa: null,
          course: {
            no: "",
            name: "",
            credit: 4,
            semester: "",
            tno: null,
            weekday: "",
            ctime: "",
            teacher: null
          },
          teacher: {
            tno: null,
            tname: "",
            title: null,
            email: null,
            dno: null,
            department: null
          }
        },
      ],
      searchByName: '',
      searchListByName:[
        {
          sno: null,
          no: null,
          grade: null,
          gpa: null,
          course: {
            no: "",
            name: "",
            credit: 4,
            semester: "",
            tno: null,
            weekday: "",
            ctime: "",
            teacher: null
          },
          teacher: {
            tno: null,
            tname: "",
            title: null,
            email: null,
            dno: null,
            department: null
          }
        },

      ],
    }
  },
  methods: {
    handleSelect(index, row) {
      console.log(index, row)
      this.centerDialogVisible=true;
      this.no=row.course.no;
      this.name=row.course.name;
      this.teacher=row.teacher.tname;
      this.row=row;
    },
    OnSelect(){
      this.centerDialogVisible = false;
      this.semester=this.row.course.semester;
      this.weekday=this.row.course.weekday;
      this.ctime=this.row.course.ctime;
      console.log(this.weekday);
      console.log(this.ctime);
      switch (this.weekday) {
        case "周一" : this.weekday="1"; break;
        case "周二" : this.weekday="2"; break;
        case "周三" : this.weekday="3"; break;
        case "周四" : this.weekday="4"; break;
        case "周五" : this.weekday="5"; break;
        case "周六" : this.weekday="6"; break;
        case "周日" : this.weekday="7"; break;
      }
      switch (this.ctime) {
        case "08:00-09:40" : this.ctime="1"; break;
        case "10:10-11:50" : this.ctime="2"; break;
        case "14:00-15:40" : this.ctime="3"; break;
        case "16:00-17:40" : this.ctime="4"; break;
        case "19:00-20:40" : this.ctime="5"; break;
      }
      console.log(this.weekday);
      console.log(this.ctime);
      axios.get("URL/sc/insert/"+sessionStorage.getItem("username")+"/"+this.no+"/"+this.weekday+"/"+this.ctime+"/"+this.semester).then((resp)=>{
        if(resp.data.code===200){
          this.$alert(this.name+":选课成功","成功",{
            confirmButtonText:"确认",
          })
        }else {
          this.$alert(this.name+":选课失败，请检查该课程是否与已选课程时间冲突","失败",{
            confirmButtonText:"确认",
          })
        }
      })
      this.reload();
    },

    searchData(){
      if(this.searchByName===''||this.searchByName==null){
        this.searchListByName = []; // 结果列表置空
        axios.get("URL/sc/selectCourseBySnoThatCanSelect/"+sessionStorage.getItem("username")).then((resp)=>{
          this.tableData=resp.data;
        })
      }else{
        axios.get("URL/sc/selectCourseBySnoThatCanSelect/"+sessionStorage.getItem("username")).then((resp)=>{
          let list=resp.data;
          this.searchListByName = []; // 结果列表置空
          let regStr =  '';
          // 初始化正则表达式
          for(let i=0; i<this.searchByName.length; i++){
            regStr = regStr + '(' + this.searchByName[i] + ')([\\s]*)'; //跨字匹配
          }
          let reg = new RegExp(regStr);
          console.log(reg);
          console.log(list);
          for(let i=0; i<list.length; i++){
            let name = list[i].course.name; //按照名字匹配
            let regMatch = name.match(reg);
            if(null !== regMatch) {// 将匹配的数据放入结果列表中
              this.searchListByName.push(list[i]);
            }
          }
          this.tableData=this.searchListByName;
        })
      }
    }

  },
  created() {
    axios.get("URL/sc/selectCourseBySnoThatCanSelect/"+sessionStorage.getItem("username")).then((resp)=>{
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