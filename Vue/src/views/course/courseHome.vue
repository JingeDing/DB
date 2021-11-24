<template>
  <el-container>
    <el-header>
      <div style="height: 80%;margin: 20px;line-height: 50px" >
        快速查找：
        <el-input v-model="searchByName" size="medium" placeholder="根据课程名称" @keyup.enter="searchDataByName" style="width: 20%" />
        <el-input v-model="searchByTeacher" size="medium" placeholder="根据教师姓名" @keyup.enter="searchDataByTeacher" style="width: 20%" />
        <el-button type="primary" @click="add" style="margin-left: 38%">新增课程</el-button>
      </div>
    </el-header>

    <el-main class="el-main" >
      <el-table :data="tableData" stripe style="width: 100%;padding-top: 0" >
        <el-table-column prop="no" label="课程编号" width="120" align="center" sortable/>
        <el-table-column prop="semester" label="课程学期" width="120" align="center" />
        <el-table-column prop="name" label="课程名称" width="150" align="center"/>
        <el-table-column prop="teacher.tname" label="授课教师" width="120" align="center"/>
        <el-table-column prop="credit" label="课程学分" width="100" align="center"/>
        <el-table-column label="课程时间" align="center">
          <el-table-column prop="weekday" label="周次" width="100" align="center"/>
          <el-table-column prop="ctime" label="时间" width="100" align="center"/>
        </el-table-column>
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

  <el-dialog v-model="centerDialogVisible" title="新增课程" width="32%" top>
    <el-form
        ref="formLabelAlign"
        :label-position="labelPosition"
        label-width="100px"
        :model="formLabelAlign"
        :rules="rules"
    >
      <el-form-item label="课程学期" prop="semester">
        <el-select v-model="formLabelAlign.semester" placeholder="Select" >
          <el-option
              v-for="item in semester"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="授课教师" prop="tno">
        <el-select v-model="formLabelAlign.tno" placeholder="Select" >
          <el-option
              v-for="item in options"
              :key="item.tno"
              :label="item.tno+':'+item.tname"
              :value="item.tno"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="课程学分" prop="credit">
        <el-select v-model="formLabelAlign.credit" placeholder="Select" >
          <el-option
              v-for="item in credit"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="周次" prop="weekday">
        <el-select v-model="formLabelAlign.weekday" placeholder="Select" >
          <el-option
              v-for="item in weekday"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="课程节数" prop="ctime">
        <el-select v-model="formLabelAlign.ctime" placeholder="Select" >
          <el-option
              v-for="item in ctime"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="课程编号" prop="no">
        <el-input v-model="formLabelAlign.no" maxlength="12"  placeholder="输入课程编号" ></el-input>
      </el-form-item>
      <el-form-item label="课程名称" prop="name">
        <el-input v-model="formLabelAlign.name" placeholder="输入课程的中文名称"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="onSubmit">提交</el-button>
        <el-button @click="centerDialogVisible = false">取消</el-button>
      </span>
    </template>
  </el-dialog>

  <el-dialog v-model="centerDialogVisibleByEdit" title="修改课程信息" width="35%" top>
    <el-form
        ref="formLabelAlignByEdit"
        :label-position="labelPosition"
        label-width="100px"
        :model="formLabelAlignByEdit"
        :rules="rules"
    >
      <el-form-item label="课程编号" prop="no">
        <el-input v-model="formLabelAlignByEdit.no" maxlength="12"  placeholder="输入课程编号" disabled ></el-input>
      </el-form-item>
      <el-form-item label="课程学期" prop="semester">
        <el-select v-model="formLabelAlignByEdit.semester" placeholder="Select" >
          <el-option
              v-for="item in semester"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="授课教师" prop="tno">
        <el-select v-model="formLabelAlignByEdit.tno" placeholder="Select" >
          <el-option
              v-for="item in options"
              :key="item.tno"
              :label="item.tno+':'+item.tname"
              :value="item.tno"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="课程学分" prop="credit">
        <el-select v-model="formLabelAlignByEdit.credit" placeholder="Select" >
          <el-option
              v-for="item in credit"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="周次" prop="weekday">
        <el-select v-model="formLabelAlignByEdit.weekday" placeholder="Select" >
          <el-option
              v-for="item in weekday"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="课程节数" prop="ctime">
        <el-select v-model="formLabelAlignByEdit.ctime" placeholder="Select" >
          <el-option
              v-for="item in ctime"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="课程名称" prop="name">
        <el-input v-model="formLabelAlignByEdit.name" placeholder="输入课程的中文名称"></el-input>
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
  name: "courseHome",
  data(){
    return{
      total:100,
      tableData:[
        {
          no: "",
          name: "",
          credit: 3,
          weekday:"",
          ctime:"",
          semester: "",
          tno: "",
          teacher: {
            tno: "",
            tname: "",
            title: null,
            email: null,
            dno: null,
            department: null
          }
        },
      ],
      labelPosition: 'left',
      formLabelAlign: {
        no: "",
        name: "",
        credit: 3,
        weekday:"",
        ctime:"",
        semester: "",
        tno: "",
        teacher: {
          tno: "",
          tname: "",
        }
      },
      rules:{
        semester: [
          {required:true}
        ],
        tno:[
          {required:true}
        ],
        credit:[
          {required:true}
        ],
        weekday:[
          {required:true}
        ],
        ctime:[
          {required:true}
        ],
        no:[
          {required:true,pattern:/^[a-zA-Z0-9]{5,12}$/,message:"5-12位数字字母",trigger:"blur"}
        ],
        name:[
          {required:true,pattern:/^[\u4e00-\u9fa5]+$/,message:"课程名称需使用中文名称",trigger:"blur"}
        ]
      },
      formLabelAlignByEdit:{
        no: "",
        name: "",
        credit: '',
        weekday:"",
        ctime:"",
        semester: "",
        tno: "",
        teacher: {
          tno: "",
          tname: "",
        }
      },
      options: [
        {
          tno: "",
          tname: "",
        }
      ],
      value: '',
      credit:[
        {
          value:1,
          label:"1学分",
        },
        {
          value:2,
          label:"2学分",
        },        {
          value:3,
          label:"3学分",
        },        {
          value:4,
          label:"4学分",
        },        {
          value:5,
          label:"5学分",
        },
      ],
      ctime:[
        {
          value:"1",
          label:"1-2节",
        },
        {
          value:"2",
          label:"3-4节",
        },
        {
          value:"3",
          label:"5-6节",
        },
        {
          value:"4",
          label:"7-8节",
        },
        {
          value:"5",
          label:"9-10节",
        },
      ],
      weekday:[
        {
          value:"1",
          label:"周一",
        },
        {
          value:"2",
          label:"周二",
        },
        {
          value:"3",
          label:"周三",
        },
        {
          value:"4",
          label:"周四",
        },
        {
          value:"5",
          label:"周五",
        },
        {
          value:"6",
          label:"周六",
        },
        {
          value:"7",
          label:"周日",
        },
      ],
      semester:[
        {
          value: "2020春",
          label: "2020春",
        },
        {
          value:"2020秋",
          label:"2020秋",
        },
        {
          value: "2021春",
          label: "2021春",
        },
        {
          value:"2021秋",
          label:"2021秋",
        },
        {
          value: "2022春",
          label: "2022春",
        },
      ],
      searchByName: '',
      searchListByName:[
        {
          no: "",
          name: "",
          credit: "",
          ctime: "",
          weekday:"",
          semester: "",
          tno: "",
          teacher: {
            tno: "",
            tname: "",
          }
        }
      ],
      searchByTeacher: '',
      searchListByTeacher:[
        {
          no: "",
          name: "",
          credit: "",
          ctime: "",
          weekday:"",
          semester: "",
          tno: "",
          teacher: {
            tno: "",
            tname: "",
          }
        }
      ],
    }
  },

  methods:{
    handleCurrentChange(currentPage){
      axios.get("URL/course/selectAll/"+currentPage+"/6").then(resp=>{
        this.tableData=resp.data.list;
        this.total=parseInt(resp.data.total);
      })
    },

    handleEdit(index, row) {
      this.centerDialogVisibleByEdit=true;
      // console.log(index, row);
      axios.get("URL/teacher/selectAll/0/100").then((res)=>{
        this.options=res.data.list;
      });
      axios.get("URL/course/selectById/"+row.no).then((resp)=>{
        console.log(resp.data);
        this.formLabelAlignByEdit=resp.data;
      })
    },

    handleDelete(index, row) {
      this.dialogVisible=true;
      this.row=row;
      console.log(index,row);
    },

    add(){
      axios.get("URL/teacher/selectAll/0/100").then((res)=>{
        this.options=res.data.list;
      });
      this.centerDialogVisible=true;
      this.formLabelAlign={};
    },

    onSubmit(){
      console.log(this.formLabelAlign);
      this.$refs['formLabelAlign'].validate(valid =>{
        if(valid){
          axios({
            method: "post",
            URL:"URL/course/addCourse",
            data:this.formLabelAlign,
          }).then((resp)=>{
            if(resp.data.code===200){
              this.$alert(this.formLabelAlign.name+":添加成功","成功",{
                confirmButtonText:"确认",
              })
            }else {
              this.$alert(this.formLabelAlign.name+":添加失败","失败",{
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
            URL:"URL/course/update",
            data:this.formLabelAlignByEdit,
          }).then((resp)=>{
            if(resp.data.code===200){
              this.$alert(this.formLabelAlignByEdit.name+":修改成功","成功",{
                confirmButtonText:"确认",
              })
            }else {
              this.$alert(this.formLabelAlignByEdit.name+":修改失败","错误",{
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
      axios.get("URL/course/deleteById/"+this.row.no).then((resp)=>{
        if(resp.data.code===200){
          this.$alert(this.row.no+this.row.name+":已删除成功","成功",{
            confirmButtonText:"确认",
          })
        }else{
          this.$alert(this.row.no+this.row.name+":删除失败","警告",{
            confirmButtonText:"确认",
          })
        }
      })
      this.dialogVisible=false;
      this.handleCurrentChange(1);
    },

    searchDataByName(){
      if(this.searchByName===''||this.searchByName==null){
        this.searchListByName = []; // 结果列表置空
        axios.get("URL/course/selectAll/1/6").then((resp)=>{
          this.tableData=resp.data.list;
          this.total=resp.data.total;
        })
      }else{
        axios.get("URL/course/selectAll/0/100").then((resp)=>{
          let list=resp.data.list;
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
            let name = list[i].name; //按照名字匹配
            let regMatch = name.match(reg);
            if(null !== regMatch) {// 将匹配的数据放入结果列表中
              this.searchListByName.push(list[i]);
            }
          }
          this.total=this.searchListByName.length;
          this.tableData=this.searchListByName;
        })
      }
    },

    searchDataByTeacher(){
      if(this.searchByTeacher===''||this.searchByTeacher==null){
        this.searchListByTeacher = []; // 结果列表置空
        axios.get("URL/course/selectAll/1/6").then((resp)=>{
          this.tableData=resp.data.list;
          this.total=resp.data.total;
        })
      }else{
        axios.get("URL/course/selectAll/0/100").then((resp)=>{
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
            let name = list[i].teacher.tname; //按照名字匹配
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
    axios.get("URL/course/selectAll/1/6").then((resp)=>{
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