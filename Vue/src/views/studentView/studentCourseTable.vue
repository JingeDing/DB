<template>
  <el-table :data="tableData" style="width: 100%" border>
      <el-table-column prop="Time" label="时间" style="width: 12.5%" align="center" />
      <el-table-column prop="Mo" label="周一" style="width: 12.5%" align="center"/>
      <el-table-column prop="Tu" label="周二" style="width: 12.5%" align="center"/>
      <el-table-column prop="We" label="周三" style="width: 12.5%" align="center"/>
      <el-table-column prop="Th" label="周四" style="width: 12.5%" align="center"/>
      <el-table-column prop="Fr" label="周五" style="width: 12.5%" align="center"/>
      <el-table-column prop="Sa" label="周六" style="width: 12.5%" align="center"/>
      <el-table-column prop="Su" label="周日" style="width: 12.5%" align="center"/>
  </el-table>

</template>

<script lang="ts">

import axios from "axios";

export default {
  name: "studentCourseTable",
  data() {
    return {
      tableData: [
        {
          Time: '08:00-08:45',
          Mo:"",
          Tu:"",
          We:"",
          Th:"",
          Fr:"",
          Sa:"",
          Su:"",
        },
        {
          Time: '08:55-09:40',
          Mo:"",
          Tu:"",
          We:"",
          Th:"",
          Fr:"",
          Sa:"",
          Su:"",
        },
        {
          Time: '10:10-10:55',
          Mo:"",
          Tu:"",
          We:"",
          Th:"",
          Fr:"",
          Sa:"",
          Su:"",
        },
        {
          Time: '11:05-11:50',
          Mo:"",
          Tu:"",
          We:"",
          Th:"",
          Fr:"",
          Sa:"",
          Su:"",
        },
        {
          Time: '14:00-14:45',
          Mo:"",
          Tu:"",
          We:"",
          Th:"",
          Fr:"",
          Sa:"",
          Su:"",
        },
        {
          Time: '14:55-15:40',
          Mo:"",
          Tu:"",
          We:"",
          Th:"",
          Fr:"",
          Sa:"",
          Su:"",
        },
        {
          Time: '16:00-16:45',
          Mo:"",
          Tu:"",
          We:"",
          Th:"",
          Fr:"",
          Sa:"",
          Su:"",
        },
        {
          Time: '16:55-17:40',
          Mo:"",
          Tu:"",
          We:"",
          Th:"",
          Fr:"",
          Sa:"",
          Su:"",
        },
        {
          Time: '19:00-19:45',
          Mo:"",
          Tu:"",
          We:"",
          Th:"",
          Fr:"",
          Sa:"",
          Su:"",
        },
        {
          Time: '19:55-20:40',
          Mo:"",
          Tu:"",
          We:"",
          Th:"",
          Fr:"",
          Sa:"",
          Su:"",
        },
      ],
    }
  },
  methods:{
    test(){
      console.log(this.tableData)
    }

  },
  created() {
    axios.get("URL/course/CourseTableBySno/"+sessionStorage.getItem("username")).then((resp)=>{
      let array=resp.data;
      console.log(array);
      console.log(array[1][1]!=="0");
      for (let i=0,k=0;i<10;i=i+2){
        for (let j=0;j<7;j++){
          if(array[k][j]!==null){
            switch (j){
              case 0:
                this.tableData[i].Mo=array[k][j];
                this.tableData[i+1].Mo=array[k][j];break;
              case 1:
                this.tableData[i].Tu=array[k][j];
                this.tableData[i+1].Tu=array[k][j];break;
              case 2:
                this.tableData[i].We=array[k][j];
                this.tableData[i+1].We=array[k][j];break;
              case 3:
                this.tableData[i].Th=array[k][j];
                this.tableData[i+1].Th=array[k][j];break;
              case 4:
                this.tableData[i].Fr=array[k][j];
                this.tableData[i+1].Fr=array[k][j];break;
              case 5:
                this.tableData[i].Sa=array[k][j];
                this.tableData[i+1].Sa=array[k][j];break;
              case 6:
                this.tableData[i].Su=array[k][j];
                this.tableData[i+1].Su=array[k][j];break;
            }
          }
        }
        k++;
      }
    })
  }


}
</script>



