<template>

  <el-container>
    <el-header>
      <div style="height: 80%;margin: 20px;line-height: 50px" >
        快速查找：
        <el-input v-model="searchByDname" size="medium" placeholder="根据学院名称" @keyup.enter="searchDataByDname" style="width: 20%" />
        <el-input v-model="searchByMajor" size="medium" placeholder="根据专业名称" @keyup.enter="searchDataByMajor" style="width: 20%" />
        <el-button type="primary" @click="addClass" style="margin-left: 38%">新增班级</el-button>
      </div>
    </el-header>

    <el-main class="el-main" >
      <el-table :data="tableData" stripe style="width: 100%;padding-top: 0">
        <el-table-column prop="year" label="入学年份" width="120" align="center" sortable/>
        <el-table-column prop="dno" label="院系编号" width="120" align="center"/>
        <el-table-column prop="department.dname" label="院系名称" width="120" align="center"/>
        <el-table-column prop="cno" label="班级编号" width="120" align="center"/>
        <el-table-column prop="cname" label="班级名称" width="120" align="center"/>
        <el-table-column prop="cyear" label="学制" width="100" align="center" />
        <el-table-column prop="major" label="专业名称" align="center" />
        <el-table-column align="left">
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
      <el-form-item label="班级编号" prop="cno">
        <el-input v-model="formLabelAlign.cno" maxlength="8"  placeholder="输入班级的编号,eg:19240101" ></el-input>
      </el-form-item>
      <el-form-item label="班级名称" prop="cname">
        <el-input v-model="formLabelAlign.cname" placeholder="输入班级的名称,eg:19计科1班"></el-input>
      </el-form-item>
      <el-form-item label="入学年份" prop="year">
        <el-select v-model="formLabelAlign.year" placeholder="Select" >
          <el-option
              v-for="item in year"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="专业名称" prop="major">
        <el-input v-model="formLabelAlign.major" placeholder="输入专业名称"></el-input>
      </el-form-item>
      <el-form-item label="专业学制" prop="cyear">
        <el-input v-model="formLabelAlign.cyear" placeholder="输入专业学制"></el-input>
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
      <el-form-item label="班级编号" prop="cno">
        <el-input v-model="formLabelAlignByEdit.cno"  disabled ></el-input>
      </el-form-item>
      <el-form-item label="班级名称" prop="cname">
        <el-input v-model="formLabelAlignByEdit.cname" placeholder="输入班级的名称,eg:19计科1班"></el-input>
      </el-form-item>
      <el-form-item label="入学年份" prop="year">
        <el-select v-model="formLabelAlignByEdit.year" placeholder="Select" >
          <el-option
              v-for="item in year"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="专业名称" prop="major">
        <el-input v-model="formLabelAlignByEdit.major" placeholder="输入专业名称"></el-input>
      </el-form-item>
      <el-form-item label="专业学制" prop="cyear">
        <el-input v-model="formLabelAlignByEdit.cyear" placeholder="输入专业学制"></el-input>
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
  name: "classHome",
  data() {
    return {
      total:100,
      rules:{
        dno:[
          {required:true}
        ],
        cno:[
          {required:true,},
        ],
        cname:[
          {required:true,pattern:/^[\u4e00-\u9fa50-9]+$/,message:"请不要输入除中文和数字外的其他字符",trigger:"blur"}
        ],
        year:[
          {required:true}
        ],
        cyear:[
          {required:true,pattern:/^[2-5]$/,message:"学制输入只能是2—5年",trigger:"blur"}
        ],
        major:[
          {required:true,pattern:/^[\u4e00-\u9fa5]+$/,message:"专业名称请使用中文",trigger:"blur"}
        ]
      },
      tableData: [
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
      labelPosition: 'left',
      formLabelAlign: {
        cno: "",
        cname: "",
        year: 2019,
        major: "",
        dno: "",
        cyear: "",
      },
      formLabelAlignByEdit:{
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
      },
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
      options: [
        {
          dno: '',
          dname: '',
        }
      ],
      value: '',
      year:[
        {
          value:2017,
          label:"2017",
        },
        {
          value:2018,
          label:"2018",
        },        {
          value:2019,
          label:"2019",
        },        {
          value:2020,
          label:"2020",
        },        {
          value:2021,
          label:"2021",
        },
      ]
    }
  },
  methods:{
    handleCurrentChange(currentPage){
      axios.get("URL/class/selectAll/"+currentPage+"/6").then(resp=>{
        this.tableData=resp.data.list;
        this.total=parseInt(resp.data.total);
      })
    },
    handleEdit(index, row) {
      this.centerDialogVisibleByEdit=true;
      console.log(index, row);
      axios.get("URL/class/selectById/"+row.cno).then((resp)=>{
        this.formLabelAlignByEdit=resp.data;
      })
    },
    handleDelete(index, row) {
      this.dialogVisible=true;
      this.row=row;
      console.log(index,row);
    },
    addClass(){
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
            URL:"URL/class/addClass",
            data:this.formLabelAlign,
          }).then((resp)=>{
            if(resp.data.code===200){
              this.$alert(this.formLabelAlign.cname+":提交成功","成功",{
                confirmButtonText:"确认",
              })
            }else {
              this.$alert(this.formLabelAlign.cname+":提交失败,请检查班级编号的格式及是否已存在此班级","失败",{
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
            URL:"URL/class/updateClass",
            data:this.formLabelAlignByEdit,
          }).then((resp)=>{
            if(resp.data.code===200){
              this.$alert(this.formLabelAlignByEdit.cname+":修改成功","成功",{
                confirmButtonText:"确认",
              })
            }else {
              this.$alert(this.formLabelAlignByEdit.department.dname+":修改失败，如有问题请联系数据库管理员","错误",{
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
      axios.get("URL/class/deleteById/"+this.row.cno).then((resp)=>{
        if(resp.data.code===200){
          this.$alert("["+this.row.dno+"]"+this.row.cname+":已删除成功","成功",{
            confirmButtonText:"确认",
          })
        }else{
          this.$alert("["+this.row.dno+"]"+this.row.cname+":删除失败","警告",{
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
        axios.get("URL/class/selectAll/1/6").then((resp)=>{
          this.tableData=resp.data.list;
          this.total=resp.data.total;
        })
      }else{
        axios.get("URL/class/selectAll/0/100").then((resp)=>{
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
        axios.get("URL/class/selectAll/1/6").then((resp)=>{
          this.tableData=resp.data.list;
          this.total=resp.data.total;
        })
      }else{
        axios.get("URL/class/selectAll/0/100").then((resp)=>{
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
            let name = list[i].major; //按照名字匹配
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
    axios.get("URL/class/selectAll/1/6").then((resp)=>{
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
.el-main{
  --el-main-padding: 15px;
}

</style>