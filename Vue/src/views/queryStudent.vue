<template>
  <el-container>
    <el-header>
      <div style="height: 80%;margin: 20px;line-height: 50px" >
        快速查找：
        <el-input v-model="searchByDname" size="medium" placeholder="根据学院名称" @keyup.enter="searchDataByDname" style="width: 20%" />
        <el-input v-model="searchByMajor" size="medium" placeholder="根据专业名称" @keyup.enter="searchDataByMajor" style="width: 20%" />
        <el-button type="primary" @click="addStudent" style="margin-left: 38%">新增学生</el-button>
      </div>
    </el-header>

    <el-main class="el-main" >
      <el-table :data="tableData" stripe style="width: 100%;padding-top: 0">
        <el-table-column prop="sno" label="学号" width="120" align="center" sortable/>
        <el-table-column prop="department.dname" label="院系名称" width="120" align="center"/>
        <el-table-column prop="classItem.cname" label="班级名称" width="120" align="center"/>
        <el-table-column prop="sname" label="姓名" width="80" align="center"/>
        <el-table-column prop="sex" label="性别" width="60" align="center"/>
        <el-table-column prop="classItem.major" label="专业名称" width="180" align="center" />
        <el-table-column prop="classItem.year" label="入学年份" width="80" align="center" />
        <el-table-column prop="classItem.cyear" label="学制" width="80" align="center" />
        <el-table-column align="center" width="200">
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
          :page-size="8"
          :page-count="Math.ceil(total/8)"
          @current-change="handleCurrentChange">
      </el-pagination>
    </el-main>
  </el-container>


  <el-dialog v-model="centerDialogVisible" title="新增学生" width="35%" center>
    <el-form
        ref="formLabelAlign"
        :label-position="labelPosition"
        label-width="100px"
        :model="formLabelAlign"
        :rules="rules"
    >
      <el-form-item label="所属班级" prop="cno">
        <el-select v-model="formLabelAlign.cno" placeholder="Select" >
          <el-option
              v-for="item in options"
              :key="item.cno"
              :label="item.cname"
              :value="item.cno"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="选择性别" prop="sex">
        <el-radio-group v-model="formLabelAlign.sex">
          <el-radio  label="男">男</el-radio>
          <el-radio  label="女">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="学生学号" prop="sno">
        <el-input v-model="formLabelAlign.sno" maxlength="12"  placeholder="请输入12位的学号" ></el-input>
      </el-form-item>
      <el-form-item label="学生姓名" prop="sname">
        <el-input v-model="formLabelAlign.sname" placeholder="输入学生的姓名"></el-input>
      </el-form-item>
    </el-form>
    <template #footer >
      <span class="dialog-footer" style="">
        <el-button type="primary" @click="onSubmit">提交</el-button>
        <el-button @click="centerDialogVisible = false">取消</el-button>
      </span>
    </template>
  </el-dialog>


  <el-dialog v-model="centerDialogVisibleByEdit" title="修改学生信息" width="35%" center>
    <el-form
        ref="formLabelAlignByEdit"
        :label-position="labelPosition"
        label-width="100px"
        :model="formLabelAlignByEdit"
        :rules="rules"
    >
      <el-form-item label="学生学号" prop="sno">
        <el-input v-model="formLabelAlignByEdit.sno"  disabled ></el-input>
      </el-form-item>
      <el-form-item label="选择性别" prop="sex">
        <el-radio-group v-model="formLabelAlignByEdit.sex">
          <el-radio  label="男">男</el-radio>
          <el-radio  label="女">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="所属班级" prop="cno">
        <el-select v-model="formLabelAlignByEdit.cno" placeholder="Select" >
          <el-option
              v-for="item in options"
              :key="item.cno"
              :label="item.cname"
              :value="item.cno"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="学生姓名" prop="sname">
        <el-input v-model="formLabelAlignByEdit.sname" ></el-input>
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

<script lang="ts">
import axios from "axios";
import {ref} from "vue";

export default {
  name: "queryStudent",
  data() {
    return {
      total:100,
      tableData: [
        {
          sno: "",
          sname: "",
          sex: "",
          cno: "",
          department: {
            dno: "",
            dname: ""
          },
          classItem: {
            cno: "",
            cname: "",
            year: 2019,
            major: "",
            dno: null,
            department: null,
            cyear: ""
          }
        }
      ],
      rules:{
        cno:[
          {required:true}
        ],
        sex:[
          {required:true}
        ],
        sno:[
          {required:true,pattern:/^[0-9]{12}$/,message:"学号需为12位整数",trigger:"blur"}
        ],
        sname:[
          {required:true,pattern:/^[\u4e00-\u9fa5]+$/,message:"学生姓名只能用中文",trigger:"blur"}
        ]
      },
      labelPosition: 'left',
      formLabelAlign: {
        cno: '',
        sno: '',
        sname: '',
        sex: '',
      },
      formLabelAlignByEdit:{
        sno: "",
        sname: "",
        sex: "",
        cno: "",
        department: {
          dno: "",
          dname: ""
        },
        classItem: {
          cno: "",
          cname: "",
          year: "",
          major: "",
          dno: null,
          department: null,
          cyear: "4"
        }
      },
      options: [
        {
          cno: "",
          cname: "",
          year: 2019,
          cyear: "",
          major: "",
          dno: "",
          department: {
            dno: "",
            dname: ""
          },
        }
      ],
      value: '',
      searchByDname: '',
      searchListByDname:[
        {
          cno: "",
          cname: "",
          year: 2019,
          major: "",
          dno: "",
          department: {
            dno: "",
            dname: ""
          },
          cyear: ""
        }
      ],
      searchByMajor: '',
      searchListByMajor:[
        {
          sno: "",
          sname: "",
          sex: "",
          cno: "",
          department: {
            dno: "",
            dname: ""
          },
          classItem: {
            cno: "",
            cname: "",
            year: "",
            major: "",
            dno: null,
            department: null,
            cyear: ""
          }
        }
      ],

    }
  },
  methods:{
    handleCurrentChange(currentPage){
      this.axios.get("URL/student/selectAllStudent/"+currentPage+"/8").then((resp) => {
        this.tableData = resp.data.list;
        this.total=resp.data.total;
      })
    },
    addStudent(){
      axios.get("URL/class/selectAll/0/100").then((res)=>{
        this.options=res.data.list;
      });
      this.centerDialogVisible=true;
      this.formLabelAlign={};
    },

    handleEdit(index, row) {
      this.centerDialogVisibleByEdit=true;
      console.log(index, row);
      axios.get("URL/student/queryById/"+row.sno).then((resp)=>{
        this.formLabelAlignByEdit=resp.data;
      });
      axios.get("URL/class/selectAll/0/100").then((res)=>{
        this.options=res.data.list;
      });
    },
    handleDelete(index, row) {
      this.dialogVisible=true;
      this.row=row;
      console.log(index,row);
    },
    onSubmit(){
      console.log(this.formLabelAlign);
      this.$refs['formLabelAlign'].validate(valid =>{
        if(valid){
          axios({
            method: "post",
            URL/:"URL/student/addStudent",
            data:this.formLabelAlign,
          }).then((resp)=>{
            if(resp.data.code===200){
              this.$alert(this.formLabelAlign.sname+":提交成功","成功",{
                confirmButtonText:"确认",
              })
            }else {
              this.$alert(this.formLabelAlign.sname+":提交失败","失败",{
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
      console.log(this.formLabelAlignByEdit);
      this.$refs['formLabelAlignByEdit'].validate(valid =>{
        if(valid){
          axios({
            method: "post",
            URL/:"URL/student/updateStudent",
            data:this.formLabelAlignByEdit,
          }).then((resp)=>{
            if(resp.data.code===200){
              this.$alert(this.formLabelAlignByEdit.sname+":修改成功","成功",{
                confirmButtonText:"确认",
              })
            }else {
              this.$alert(this.formLabelAlignByEdit.sname+":修改失败","错误",{
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
      axios.get("URL/student/deleteById/"+this.row.sno).then((resp)=>{
        if(resp.data.code===200){
          this.$alert(this.row.sname+":已删除成功","成功",{
            confirmButtonText:"确认",
          })
        }else{
          this.$alert(this.row.sname+":删除失败","警告",{
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
        axios.get("URL/student/selectAllStudent/1/8").then((resp)=>{
          this.tableData=resp.data.list;
          this.total=resp.data.total;
        })
      }else{
        axios.get("URL/student/selectAllStudent/0/300").then((resp)=>{
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
    searchDataByMajor(){
      if(this.searchByMajor===''||this.searchByMajor==null){
        this.searchListByMajor = []; // 结果列表置空
        axios.get("URL/student/selectAllStudent/1/8").then((resp)=>{
          this.tableData=resp.data.list;
          this.total=resp.data.total;
        })
      }else{
        axios.get("URL/student/selectAllStudent/0/300").then((resp)=>{
          let list=resp.data.list;
          this.searchListByMajor = []; // 结果列表置空
          let regStr =  '';
          // 初始化正则表达式
          for(let i=0; i<this.searchByMajor.length; i++){
            regStr = regStr + '(' + this.searchByMajor[i] + ')([\\s]*)'; //跨字匹配
          }
          let reg = new RegExp(regStr);
          console.log(reg);
          console.log(list);
          for(let i=0; i<list.length; i++){
            let name = list[i].classItem.major; //按照名字匹配
            let regMatch = name.match(reg);
            if(null !== regMatch) {// 将匹配的数据放入结果列表中
              this.searchListByMajor.push(list[i]);
            }
          }
          this.total=this.searchListByMajor.length;
          this.tableData=this.searchListByMajor;
        })
      }
    },
  },

  created() {
    this.axios.get("URL/student/selectAllStudent/1/8").then((resp) => {
      this.tableData = resp.data.list;
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