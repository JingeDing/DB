<template>

  <el-container>
    <el-header>
      <div style="height: 40px;display: flex;margin-left: 38%">
        <div>
          <img src="../../assets/zzulogo.png" style="height: 40px;width: 40px">
        </div>
        <div style="margin-top: 10px">
          郑州大学学生成绩单
        </div>
      </div>


      <el-descriptions  :column="3"  border>
        <el-descriptions-item label="已修总学分" label-align="center" align="center"
        >{{this.total}}</el-descriptions-item
        >
        <el-descriptions-item label="平均分" label-align="center" align="center"
        >{{this.average}}</el-descriptions-item
        >
        <el-descriptions-item label="平均绩点" label-align="center" align="center"
        >{{this.gpa}}</el-descriptions-item
        >
      </el-descriptions>




    </el-header>
    <el-main>
      <el-table
          :data="tableData"
          style="width: 100%"
          stripe
          border
      >
        <el-table-column prop="course.semester" label="课程学期"  align="center" style="width: 20%" sortable/>
        <el-table-column prop="no" label="课程编号" align="center" style="width: 20%" />
        <el-table-column prop="course.name" label="课程名称"  align="center" style="width: 20%" />
        <el-table-column prop="course.credit" label="课程学分"  align="center" style="width: 20%"/>
        <el-table-column prop="grade" label="课程成绩"  align="center" style="width: 10%"/>
        <el-table-column prop="gpa" label="绩点"  align="center" style="width: 10%" />
      </el-table>
    </el-main>
  </el-container>



</template>

<script>
import axios from "axios";

export default {
  name: "selectMark",
  data(){
    return{
      total:0,
      average:0,
      gpa:0,
      dataLength:0,
      tableData:[
        {
          sno: null,
          no: "",
          grade: "",
          gpa: "",
          course: {
            no: null,
            name: "",
            credit: "",
            semester: "",
            tno: null,
            weekday: null,
            ctime: null,
            teacher: null
          },
          teacher: null
        }
      ],
    }
  },
  created() {
    axios.get("URL/sc/selectMark/"+sessionStorage.getItem("username")).then((resp)=>{
      this.tableData=resp.data;
      this.dataLength=resp.data.length;
      let total=0;
      let totalMark=0;
      let tempGpa=0;
      let gradePoint=0;
      for(let i=0;i<this.dataLength;i++){
        if(parseInt(this.tableData[i].grade)>=60){
          total+=parseInt(this.tableData[i].course.credit);
          if(parseInt(this.tableData[i].grade)<65){
            tempGpa=1.0;
          }else if(parseInt(this.tableData[i].grade)<70){
            tempGpa=1.7;
          }else if(parseInt(this.tableData[i].grade)<75){
            tempGpa=2.2;
          }else if(parseInt(this.tableData[i].grade)<80){
            tempGpa=2.7;
          }else if(parseInt(this.tableData[i].grade)<85){
            tempGpa=3.2;
          }else if(parseInt(this.tableData[i].grade)<90){
            tempGpa=3.7;
          }else {
            tempGpa=4.0;
          }
          gradePoint+=tempGpa*parseInt(this.tableData[i].course.credit);
        }
        totalMark+=parseInt(this.tableData[i].grade);
      }
      this.total=total;
      this.average=(totalMark/this.dataLength).toFixed(2);
      this.gpa=(gradePoint/total).toFixed(4);
    })
  }

}
</script>

<style scoped>

</style>