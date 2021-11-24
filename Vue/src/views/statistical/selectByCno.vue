<template>
  <div>
    <div >
      <div style="margin-top: 20px;margin-bottom: 20px">
        <el-input v-model="input" placeholder="输入要查询的班级编号" style="width: 45%; margin-left: 20%"/>
        <el-button type="primary" style="width: 110px" @click="searchDB">搜索</el-button>
      </div>
    </div>

    <div style="margin: 0 auto">
      <el-table :data="tableData" style="width: 88%;margin: 0 auto;" >
        <el-table-column prop="department.dname" label="学院名称" width="110" align="center"/>
        <el-table-column prop="classItem.year" label="入学年份" width="80" align="center"/>
        <el-table-column prop="cno" label="班级编号" width="100" align="center"/>
        <el-table-column prop="classItem.cname" label="班级名称" width="120" align="center" />
        <el-table-column prop="classItem.major" label="专业" width="140" align="center"/>
        <el-table-column prop="classItem.cyear" label="学制" width="80" align="center"/>
        <el-table-column prop="sno" label="学号" width="120" align="center" />
        <el-table-column prop="sname" label="姓名" width="100" align="center" />
        <el-table-column prop="sex" label="性别" align="center" />
      </el-table>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "selectByCno",
  data(){
    return{
      input:null,
      tableData:[
        {
          sno: "",
          sname: "",
          sex: "",
          cno: "",
          department: {
            dno: null,
            dname: ""
          },
          classItem: {
            cno: "",
            cname: "",
            year: null,
            major: "",
            dno: null,
            department: null,
            cyear: null,
          }
        },
      ]
    }
  },
  methods:{
    searchDB(){
      console.log(this.input);
      if(this.input===null||this.input===""){
        this.$alert("请输入班级编号，班级编号不能为空","失败",{
          confirmButtonText:"确认",
        })
        this.tableData=null;
      }else {
        axios.get("URL/student/selectByCno/"+this.input).then((resp)=>{
          this.tableData=resp.data;
        })
      }

    },
  }
}
</script>

<style scoped>

</style>