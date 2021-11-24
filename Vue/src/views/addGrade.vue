<template>
  <div>

    <div >
      <div style="margin-top: 20px;margin-bottom: 20px">
        <el-select v-model="courseNo" placeholder="选择需要录入成绩的课程"  style="width: 40%; margin-left: 20%" >
          <el-option
              v-for="item in courseList"
              :key="item.no"
              :label="item.no+item.name"
              :value="item.no"
          >
          </el-option>
        </el-select>
        <el-button type="primary" style="width: 100px" @click="searchDB">搜索</el-button>
      </div>
    </div>

    <div>
      <el-table :data="tableData" style="width: 80%;margin: 0 auto">
        <el-table-column prop="no" label="课程编号" width="150" />
        <el-table-column prop="course.name" label="课程名称" width="150" />
        <el-table-column prop="sno" label="学生学号" width="150" />
        <el-table-column prop="student.sname" label="学生姓名" width="150" />
        <el-table-column prop="grade" label="分数"  width="80"/>
        <el-table-column align="left">
          <template #default="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
            >修改</el-button
            >
            <el-button
                size="mini"
                type="success"
                @click="handleAdd(scope.$index, scope.row)"
            >录入成绩</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>


    <el-dialog v-model="centerDialogVisibleByEdit" title="修改成绩" width="35%" center>
      <el-form
          ref="formLabelAlign"
          :label-position="labelPosition"
          label-width="100px"
          :model="formLabelAlignByEdit"
          :rules="rules"
      >
        <el-form-item label="课程编号" prop="sno">
          <el-input v-model="formLabelAlignByEdit.no"  disabled ></el-input>
        </el-form-item>
        <el-form-item label="课程名称" prop="sno">
          <el-input v-model="formLabelAlignByEdit.name"  disabled ></el-input>
        </el-form-item>
        <el-form-item label="学生学号" prop="sno">
          <el-input v-model="formLabelAlignByEdit.sno" disabled ></el-input>
        </el-form-item>
        <el-form-item label="成绩" prop="sno">
          <el-input v-model="formLabelAlignByEdit.grade" maxlength="3"  placeholder="请输入0-100内的整数" ></el-input>
        </el-form-item>
      </el-form>
      <template #footer >
      <span class="dialog-footer" style="">
        <el-button type="primary" @click="onEdit">提交</el-button>
        <el-button @click="centerDialogVisibleByEdit = false">取消</el-button>
      </span>
      </template>
    </el-dialog>



    <el-dialog v-model="centerDialogVisibleByAdd" title="录入成绩" width="35%" center>
      <el-form
          ref="formLabelAlign"
          :label-position="labelPosition"
          label-width="100px"
          :model="formLabelAlignByAdd"
          :rules="rules"
      >
        <el-form-item label="课程编号" prop="sno">
          <el-input v-model="formLabelAlignByAdd.no"  disabled ></el-input>
        </el-form-item>
        <el-form-item label="课程名称" prop="sno">
          <el-input v-model="formLabelAlignByAdd.name"  disabled ></el-input>
        </el-form-item>
        <el-form-item label="学生学号" prop="sno">
          <el-input v-model="formLabelAlignByAdd.sno" disabled ></el-input>
        </el-form-item>
        <el-form-item label="成绩" prop="sno">
          <el-input v-model="formLabelAlignByAdd.grade" maxlength="3"  placeholder="请输入0-100内的整数" ></el-input>
        </el-form-item>
      </el-form>
      <template #footer >
      <span class="dialog-footer" style="">
        <el-button type="primary" @click="onAdd">提交</el-button>
        <el-button @click="centerDialogVisibleByAdd = false">取消</el-button>
      </span>
      </template>
    </el-dialog>

  </div>






</template>

<script>
import axios from "axios";
import {ref} from "vue";

export default {
  name: "addGrade",
  inject:['reload'],
  data(){
    return{
      courseNo:"",
      courseList:[
        {
          no: "",
          name: "",
        }
      ],
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
      ],
      labelPosition: 'left',
      formLabelAlignByEdit: {
        no:"",
        sno:"",
        name:"",
        sname:"",
        grade:"",
      },
      formLabelAlignByAdd: {
        no:"",
        sno:"",
        name:"",
        sname:"",
        grade:"",
      },
    }
  },
  methods:{
    searchDB(){
      axios.get("URL/sc/selectByNo/"+this.courseNo).then((resp)=>{
        this.tableData=resp.data;
      })
    },
    handleEdit(index,row){
      console.log(index,row)
      if(row.grade===""||row.grade===null){
        this.$alert(row.student.sname+"同学的"+row.course.name+"成绩尚未录入数据库","失败",{
          confirmButtonText:"确认",
        })
      }else {
        this.centerDialogVisibleByEdit=true;
        this.formLabelAlignByEdit.no=row.no;
        this.formLabelAlignByEdit.sno=row.sno;
        this.formLabelAlignByEdit.name=row.course.name;
        this.formLabelAlignByEdit.grade=row.grade;
      }
    },
    onEdit(){
      axios.get("URL/sc/updateGrade/"+this.formLabelAlignByEdit.sno+"/"+this.formLabelAlignByEdit.no+"/"+this.formLabelAlignByEdit.grade).then((resp)=>{
        if(resp.data.code===200){
          this.$alert("修改成功,请刷新页面后查看","成功",{
            confirmButtonText:"确认",
          })
        }else {
          this.$alert("修改失败","失败",{
            confirmButtonText:"确认",
          })
        }
      })
      // this.reload();
      this.centerDialogVisibleByEdit=false;
    },
    handleAdd(index,row){
      console.log(index,row)
      if(row.grade!==""&&row.grade!==null){
        this.$alert(row.student.sname+"同学的"+row.course.name+"成绩已存在，请重选修改成绩","失败",{
          confirmButtonText:"确认",
        })
      }else {
        this.centerDialogVisibleByAdd=true;
        this.formLabelAlignByAdd.no=row.no;
        this.formLabelAlignByAdd.sno=row.sno;
        this.formLabelAlignByAdd.name=row.course.name;
      }
    },
    onAdd(){
      axios.get("URL/sc/insertSC/"+this.formLabelAlignByAdd.sno+"/"+this.formLabelAlignByAdd.no+"/"+this.formLabelAlignByAdd.grade).then((resp)=>{
        if(resp.data.code===200){
          this.$alert("提交成功,请刷新页面后查看","成功",{
            confirmButtonText:"确认",
          })
        }else {
          this.$alert("提交失败","失败",{
            confirmButtonText:"确认",
          })
        }
      })
      // this.reload();
      this.centerDialogVisibleByAdd=false;
    }
  },
  created() {
    axios.get("URL/course/selectAll/0/100").then((resp)=>{
      this.courseList=resp.data.list;
    })
  },
  setup() {
    return {
      centerDialogVisibleByEdit: ref(false),
      centerDialogVisibleByAdd:ref(false),
    }
  },
}
</script>

<style scoped>

</style>