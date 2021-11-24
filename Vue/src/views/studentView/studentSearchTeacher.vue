<template>
  <div style="margin: 0 auto">
    <table style="margin: 0 auto">
      <tr v-for="item in tableData">
        <td>
          <el-card class="box-card" style="margin-top: 30px" >
            <template #header>
              <div>
                <span>{{item.course.no}}:[{{item.course.name}}]—{{item.tname}}</span>
              </div>
            </template>
            <div style="margin-left: 20px">
              <div>职称：{{item.title}}</div>
              <div>工号：{{item.tno}}</div>
              <div>学院：{{item.department.dname}}</div>
              <div>邮箱：{{item.email}}</div>
            </div>
          </el-card>
        </td>
      </tr>
    </table>
  </div>



</template>

<script>
import axios from "axios";

export default {
  name: "studentSearchTeacher",
  data(){
    return{
      total:1,
      tableData:[
        {
          tno: "",
          tname: "",
          title: "",
          email: "",
          dno: null,
          department: {
            dno: null,
            dname: ""
          },
          course: {
            no: "",
            name: "",
            credit: null,
            semester: null,
            tno: null,
            weekday: null,
            ctime: null,
            teacher: null,
            sc: null
          },
          sc: null
        },
      ],

    }
  },
  created() {
    axios.get("URL/teacher/selectBySno/"+sessionStorage.getItem("username")).then((resp)=>{
      console.log(resp.data);
      this.total=resp.data.length;
      this.tableData=resp.data;
    })
  }
}
</script>

<style scoped>


.box-card {
  width: 400px;
  height: 200px;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}
</style>