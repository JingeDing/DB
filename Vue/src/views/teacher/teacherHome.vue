<template>
  <el-container>
    <el-header>
      <div style="height: 80%;margin: 20px;line-height: 50px" >
        快速查找：
        <el-input v-model="searchByDname" size="medium" placeholder="根据学院名称" @keyup.enter="searchDataByDname" style="width: 20%" />
        <el-input v-model="searchByTeacher" size="medium" placeholder="根据教师姓名" @keyup.enter="searchDataByTeacher" style="width: 20%" />
        <el-button type="primary" @click="add" style="margin-left: 38%">新增教师</el-button>
      </div>
    </el-header>

    <el-main class="el-main" >
      <el-table :data="tableData" stripe style="width: 100%;padding-top: 0">
        <el-table-column prop="department.dno" label="院系编号" width="120" align="center"/>
        <el-table-column prop="department.dname" label="院系名称" width="120" align="center"/>
        <el-table-column prop="tno" label="教师编号" width="120" align="center" sortable/>
        <el-table-column prop="tname" label="教师姓名" width="120" align="center"/>
        <el-table-column prop="title" label="教师职称" width="120" align="center"/>
        <el-table-column prop="email" label="教师邮箱" width="180" align="center"/>
        <el-table-column align="center">
          <template #default="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button
            >
            <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          background layout="prev, pager, next"
          :page-size="6"
          :page-count="Math.ceil(total/6)"
          @current-change="handleCurrentChange">
      </el-pagination>
    </el-main>
  </el-container>






  <el-dialog v-model="centerDialogVisible" title="新增班级" width="35%" top>
    <el-form
        ref="formLabelAlign"
        :label-position="labelPosition"
        label-width="100px"
        :model="formLabelAlign"
        :rules="rules"
    >
      <el-form-item label="所属院系" prop="dno">
        <el-select v-model="formLabelAlign.dno" placeholder="Select" >
          <el-option
              v-for="item in options"
              :key="item.dno"
              :label="item.dname"
              :value="item.dno"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="教师编号" prop="tno">
        <el-input v-model="formLabelAlign.tno" maxlength="5"  placeholder="输入教师的编号,eg:24101" ></el-input>
      </el-form-item>
      <el-form-item label="教师姓名" prop="tname">
        <el-input v-model="formLabelAlign.tname" placeholder="请输入教师姓名"></el-input>
      </el-form-item>
      <el-form-item label="教师职称" prop="title">
        <el-input v-model="formLabelAlign.title" placeholder="输入教师职称"></el-input>
      </el-form-item>
      <el-form-item label="教师邮箱" prop="email">
        <el-input v-model="formLabelAlign.email" placeholder="输入教师邮箱"></el-input>
      </el-form-item>
    </el-form>
    <template #footer >
      <span class="dialog-footer" style="">
        <el-button type="primary" @click="onSubmit">提交</el-button>
        <el-button @click="centerDialogVisible = false">取消</el-button>
      </span>
    </template>
  </el-dialog>



  <el-dialog v-model="centerDialogVisibleByEdit" title="修改班级信息" width="35%" top>
    <el-form
        ref="formLabelAlignByEdit"
        :label-position="labelPosition"
        label-width="100px"
        :model="formLabelAlignByEdit"
        :rules="rules"
    >
      <el-form-item label="所属院系" prop="department.dname">
        <el-input v-model="formLabelAlignByEdit.department.dname" disabled ></el-input>
      </el-form-item>
      <el-form-item label="教师编号" prop="tno">
        <el-input v-model="formLabelAlignByEdit.tno" disabled ></el-input>
      </el-form-item>
      <el-form-item label="教师姓名" prop="tname">
        <el-input v-model="formLabelAlignByEdit.tname" placeholder="请输入教师姓名"></el-input>
      </el-form-item>
      <el-form-item label="教师职称" prop="title">
        <el-input v-model="formLabelAlignByEdit.title" placeholder="输入教师职称"></el-input>
      </el-form-item>
      <el-form-item label="教师邮箱" prop="email">
        <el-input v-model="formLabelAlignByEdit.email" placeholder="输入教师邮箱"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="onSubmitByEdit">提交</el-button>
        <el-button @click="centerDialogVisibleByEdit = false">取消</el-button>
      </span>
    </template>
  </el-dialog>

  <el-dialog v-model="dialogVisible" title="警告" width="30%" center>
    <center>
      <span>你确定要删除这条记录吗？</span>
    </center>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="onDelete()">确认</el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </span>
    </template>
  </el-dialog>








</template>

<script>
import axios from "axios";
import {ref} from "vue";

export default {
  name: "teacherHome",
  data() {
  return {
    total:100,
    tableData: [
      {
        tno: "",
        tname: '',
        title: "",
        email: '',
        department: {
          dbo: '',
          dname: '',
        }
      }
    ],
    rules:{
      dno:[
        {required:true}
      ],
      tno:[
        {required:true,pattern:/^[0-9]+$/,message:"教师ID必须是纯数字",trigger:"blur"},
      ],
      tname:[
        {required:true,pattern:/^[\u4e00-\u9fa5.a-zA-Z]+$/,message:"姓名只允许包含中文或英文或.",trigger:"blur"}
      ],
      email:[
        {required:true,pattern:/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,message:"eg:xxx@zzu.edu.cn",trigger:"blur"}
      ],
      title:[
        {required:true,pattern:/^[\u4e00-\u9fa5]+$/,message:"教师职称请用中文",trigger:"blur"}
      ]
    },
    labelPosition: 'left',
    formLabelAlign: {
      tno: "",
      tname: "",
      title: "",
      email:'',
      dno: "84",
    },
    formLabelAlignByEdit:{
      tno: "",
      tname: "",
      title: "",
      email:'',
      dno: "",
      department: {
        dno: "",
        dname: ""
      },
    },
    options: [
      {
        dno: '24',
        dname: '信息工程学院',
      }
    ],
    value: '',
    searchByDname: '',
    searchListByDname:[
      {
        tno: "",
        tname: "",
        title: '',
        email: "",
        dno: "",
        department: {
          dno: "",
          dname: ""
        },
      }
    ],
    searchByTeacher: '',
    searchListByTeacher:[
      {
        tno: "",
        tname: "",
        title: '',
        email: "",
        dno: "",
        department: {
          dno: "",
          dname: ""
        },
      }
    ],

    }
  },
  methods:{
    handleCurrentChange(currentPage){
      axios.get("URL/teacher/selectAll/"+currentPage+"/6").then(resp=>{
        this.tableData=resp.data.list;
        this.total=parseInt(resp.data.total);
      })
    },
    handleEdit(index, row) {
      this.centerDialogVisibleByEdit=true;
      console.log(index, row);
      axios.get("URL/teacher/selectById/"+row.tno).then((resp)=>{
        this.formLabelAlignByEdit=resp.data;
      })
    },
    handleDelete(index, row) {
      this.dialogVisible=true;
      this.row=row;
      console.log(index,row);
    },
    add(){
      axios.get("URL/department/selectAllDepartment/0/100").then((res)=>{
        this.options=res.data.list;
      });
      this.centerDialogVisible=true;
      this.formLabelAlign={};
    },
    onSubmit(){
      this.$refs['formLabelAlign'].validate(valid =>{
        if(valid){
          axios({
            method: "post",
            URL/:"URL/teacher/addTeacher",
            data:this.formLabelAlign,
          }).then((resp)=>{
            if(resp.data.code===200){
              this.$alert(this.formLabelAlign.tname+":添加成功","成功",{
                confirmButtonText:"确认",
              })
            }else {
              this.$alert(this.formLabelAlign.tname+":添加失败,请检查教师编号是否重复","失败",{
                confirmButtonText:"确认",
              })
            }
            this.handleCurrentChange(1);
          })
        }else {
          this.$alert("请确保表单内容符合验证规则","警告",{
            confirmButtonText:"确认",
          })
        }
      })
      this.centerDialogVisible=false;
    },
    onSubmitByEdit(){
      this.$refs['formLabelAlignByEdit'].validate(valid =>{
        if(valid){
          axios({
            method: "post",
            URL/:"URL/teacher/updateTeacher",
            data:this.formLabelAlignByEdit,
          }).then((resp)=>{
            if(resp.data.code===200){
              this.$alert(this.formLabelAlignByEdit.tname+":修改成功","成功",{
                confirmButtonText:"确认",
              })
            }else {
              this.$alert(this.formLabelAlignByEdit.tname+":修改失败","错误",{
                confirmButtonText:"确认",
              })
            }
            this.handleCurrentChange(1);
          })
        }else {
          this.$alert("请检查表单项是否符合要求","警告",{
            confirmButtonText:"确认",
          })
        }
      })
      this.centerDialogVisibleByEdit=false;
    },
    onDelete(){
      console.log(this.row);
      axios.get("URL/teacher/deleteById/"+this.row.tno).then((resp)=>{
        if(resp.data.code===200){
          this.$alert(this.row.tname+":已删除成功","成功",{
            confirmButtonText:"确认",
          })
        }else{
          this.$alert(this.row.tname+":删除失败","警告",{
            confirmButtonText:"确认",
          })
        }
      })
      this.dialogVisible=false;
      this.handleCurrentChange(1);
    },

    searchDataByDname(){
      if(this.searchByDname===''||this.searchByDname==null){
        this.searchListByDname = []; // 结果列表置空
        axios.get("URL/teacher/selectAll/1/6").then((resp)=>{
          this.tableData=resp.data.list;
          this.total=resp.data.total;
        })
      }else{
        axios.get("URL/teacher/selectAll/0/100").then((resp)=>{
          let list=resp.data.list;
          this.searchListByDname = []; // 结果列表置空
          let regStr =  '';
          // 初始化正则表达式
          for(let i=0; i<this.searchByDname.length; i++){
            regStr = regStr + '(' + this.searchByDname[i] + ')([\\s]*)'; //跨字匹配
          }
          let reg = new RegExp(regStr);
          console.log(reg);
          console.log(list);
          for(let i=0; i<list.length; i++){
            let name = list[i].department.dname; //按照名字匹配
            let regMatch = name.match(reg);
            if(null !== regMatch) {// 将匹配的数据放入结果列表中
              this.searchListByDname.push(list[i]);
            }
          }
          this.total=this.searchListByDname.length;
          this.tableData=this.searchListByDname;
        })
      }
    },
    searchDataByTeacher(){

      if(this.searchByTeacher===''||this.searchByTeacher==null){
        this.searchListByTeacher = []; // 结果列表置空
        axios.get("URL/teacher/selectAll/1/6").then((resp)=>{
          this.tableData=resp.data.list;
          this.total=resp.data.total;
        })
      }else{
        axios.get("URL/teacher/selectAll/0/100").then((resp)=>{
          let list=resp.data.list;
          this.searchListByTeacher = []; // 结果列表置空
          let regStr =  '';
          // 初始化正则表达式
          for(let i=0; i<this.searchByTeacher.length; i++){
            regStr = regStr + '(' + this.searchByTeacher[i] + ')([\\s]*)'; //跨字匹配
          }
          let reg = new RegExp(regStr);
          console.log(reg);
          console.log(list);
          for(let i=0; i<list.length; i++){
            let name = list[i].tname; //按照名字匹配
            let regMatch = name.match(reg);
            if(null !== regMatch) {// 将匹配的数据放入结果列表中
              this.searchListByTeacher.push(list[i]);
            }
          }
          this.total=this.searchListByTeacher.length;
          this.tableData=this.searchListByTeacher;
        })
      }
    }


  },
  created() {
    axios.get("URL/teacher/selectAll/1/6").then((resp)=>{
      this.tableData=resp.data.list;
      this.total=resp.data.total;
    });

  },


  setup() {
    return {
      centerDialogVisible: ref(false),
      centerDialogVisibleByEdit:ref(false),
      dialogVisible:ref(false),

    }
  },
}
</script>

<style scoped>

</style>