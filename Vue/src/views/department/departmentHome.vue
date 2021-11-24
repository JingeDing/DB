<template>
  <div style="margin: 10px">
    <el-row style="height: 50px;width: 690px;">
      <el-button type="primary" @click="addDepartment" style="margin-left: 480px"
      >新增学院信息</el-button>
    </el-row>

    <el-table
        :data="tableData"
        style="width: 100%"
    >
      <el-table-column label="学院编号" prop="dno" />
      <el-table-column label="学院名称" prop="dname" />
      <el-table-column align="left">
        <template #header>
          <el-input v-model="search" size="mini" placeholder="Type to search" @keyup.enter="searchData"  />
        </template>
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
        layout="prev, pager, next"
        :page-size="6"
        :page-count="Math.ceil(total/6)"
        @current-change="handleCurrentChange">
    </el-pagination>

    <el-dialog v-model="centerDialogVisible" title="新增学院表单" width="30%" center>
      <el-form
          ref="formLabelAlign"
          :label-position="labelPosition"
          label-width="100px"
          :model="formLabelAlign"
          :rules="rules"
      >
        <el-form-item label="学院编号" prop="dno">
          <el-input v-model="formLabelAlign.dno" maxlength="2"  placeholder="输入学院的正确编号" ></el-input>
        </el-form-item>
        <el-form-item label="学院名称" prop="dname">
          <el-input v-model="formLabelAlign.dname" placeholder="输入学院的中文名称"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="onSubmit">提交</el-button>
        <el-button @click="centerDialogVisible = false">取消</el-button>
      </span>
      </template>
    </el-dialog>

    <el-dialog v-model="centerDialogVisibleByEdit" title="修改学院信息" width="30%" center>
      <el-form
          ref="formLabelAlignByEdit"
          :label-position="labelPosition"
          label-width="100px"
          :model="formLabelAlignByEdit"
          :rules="rules"
      >
        <el-form-item label="学院编号" prop="dno">
          <el-input v-model="formLabelAlignByEdit.dno" maxlength="2" disabled ></el-input>
        </el-form-item>
        <el-form-item label="学院名称" prop="dname">
          <el-input v-model="formLabelAlignByEdit.dname" ></el-input>
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
  </div>
  <div>

    <img src="../../assets/photo/zhonglou.jpg" style="height: calc(100vh - 60px);position: fixed;right: 10px">
  </div>
</template>

<script>
import axios from "axios";
import { ref } from 'vue';

export default {
  name: "departmentHome",
  data() {
    return {
      total:null,
      rules:{
        dno:[
          {required:true,pattern:/^[0-9][0-9]{1}$/ ,message:"请输入01-99之间的两位整数",trigger:"blur"},
        ],
        dname:[
          {required:true,pattern:/^[\u4e00-\u9fa5]+$/,message:"学院名称必须使用中文",trigger:"blur"}
        ]
      },
      tableData: [
        {
          dno: '',
          dname: '',
        }
      ],
      search: '',
      searchList:[
        {
          dno: '',
          dname: '',
        }
      ],
      labelPosition: 'left',
      formLabelAlign: {
        dno: '',
        dname: '',
      },
      formLabelAlignByEdit:{
        dno: '',
        dname: '',
      },
      row:"",
    }
  },
  methods: {
    searchData(){
        if(this.search===''||this.search==null){
          this.searchList = []; // 结果列表置空
          axios.get("URL/department/selectAllDepartment/1/6").then((resp)=>{
            this.tableData=resp.data.list;
            this.total=resp.data.total;
          })
        }else{
          axios.get("URL/department/selectAllDepartment/0/100").then((resp)=>{
           let list=resp.data.list;
            this.searchList = []; // 结果列表置空
            let regStr =  '';
            // 初始化正则表达式
            for(let i=0; i<this.search.length; i++){
              regStr = regStr + '(' + this.search[i] + ')([\\s]*)'; //跨字匹配
            }
            let reg = new RegExp(regStr);
            console.log(reg);
            console.log(list);
            for(let i=0; i<list.length; i++){
              let name = list[i].dname; //按照名字匹配
              let regMatch = name.match(reg);
              if(null !== regMatch) {// 将匹配的数据放入结果列表中
                this.searchList.push(list[i]);
              }
            }
            this.total=this.searchList.length;
            this.tableData=this.searchList;
          })
        }
    },
    handleEdit(index, row) {
      this.centerDialogVisibleByEdit=true;
      console.log(index, row);
      axios.get("URL/department/selectById/"+row.dno).then((resp)=>{
        this.formLabelAlignByEdit=resp.data;
      })
    },
    handleDelete(index, row) {
      this.dialogVisible=true;
      this.row=row;
      console.log(index,row);
    },
    handleCurrentChange(currentPage){
      axios.get("URL/department/selectAllDepartment/"+currentPage+"/6").then(resp=>{
        this.tableData=resp.data.list;
        this.total=parseInt(resp.data.total);
      })
    },

    onSubmit(){
      this.$refs['formLabelAlign'].validate(valid =>{
        if(valid){
          axios({
            method: "post",
            URL:"URL/department/addDepartment",
            data:this.formLabelAlign,
          }).then((resp)=>{
            if(resp.data.code===200){
              this.$alert("["+this.formLabelAlign.dno+"]"+this.formLabelAlign.dname+":提交成功","成功",{
                confirmButtonText:"确认",
              })
            }else {
              this.$alert("["+this.formLabelAlign.dno+"]"+this.formLabelAlign.dname+":提交失败,请检查学院编号是否重复","失败",{
                confirmButtonText:"确认",
              })
            }
            this.handleCurrentChange(1);
          })
        }else {
          this.$alert("请检查提交内容的格式是否正确","警告",{
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
            URL:"URL/department/updateDepartment",
            data:this.formLabelAlignByEdit,
          }).then((resp)=>{
            if(resp.data.code===200){
              this.$alert("["+this.formLabelAlignByEdit.dno+"]"+this.formLabelAlignByEdit.dname+":修改成功","成功",{
                confirmButtonText:"确认",
              })
            }else {
              this.$alert("["+this.formLabelAlignByEdit.dno+"]"+this.formLabelAlignByEdit.dname+":修改失败,请检查学院编号是否重复","错误",{
                confirmButtonText:"确认",
              })
            }
            this.handleCurrentChange(1);
          })
        }else {
          this.$alert("请检查提交内容的格式是否正确","警告",{
            confirmButtonText:"确认",
          })
        }
      })
      this.centerDialogVisibleByEdit=false;
    },
    addDepartment(){
      this.centerDialogVisible=true;
      this.formLabelAlign={};
    },
    onDelete(){
      console.log(this.row);
      axios.get("URL/department/deleteById/"+this.row.dno).then((resp)=>{
        if(resp.data.code===200){
          this.$alert("["+this.row.dno+"]"+this.row.dname+":已删除成功","成功",{
            confirmButtonText:"确认",
          })
        }else{
          this.$alert("["+this.row.dno+"]"+this.row.dname+":删除失败","警告",{
            confirmButtonText:"确认",
          })
        }
      })
      this.dialogVisible=false;
      this.handleCurrentChange(1);
    }

  },
  created() {
    axios.get("URL/department/selectAllDepartment/1/6").then((resp)=>{
      this.tableData=resp.data.list;
      this.total=resp.data.total;
    })
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