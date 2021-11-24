<template>
  <div style="height: 50px;line-height: 50px;border-bottom: 1px solid  #ccc; display: flex ">
    <div style="width: 10px"></div>
    <div style="flex: 1"><center style="color: #409EFF">教务管理系统（学生）</center></div>
    <div style="width: 100px">
      <el-dropdown>
  <span class="el-dropdown-link">
   {{user}}<i class="el-icon-arrow-down el-icon--right"></i>
  </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="setPassword" >修改密码</el-dropdown-item>
            <el-dropdown-item @click="logout">退出</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>

    <el-dialog v-model="centerDialogVisible" title="修改密码" width="30%" center>
      <el-form
          ref="formLabelAlign"
          :label-position="labelPosition"
          label-width="100px"
          :model="formLabelAlign"
      >
        <el-form-item label="学生学号" prop="username">
          <el-input v-model="formLabelAlign.username" disabled ></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="password">
          <el-input v-model="formLabelAlign.password" placeholder="输入新密码" type="password"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="onSubmit">提交</el-button>
        <el-button @click="centerDialogVisible = false">取消</el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";
import {ref} from "vue";

export default {
  name: "studentHeader",
  data(){
    return{
      username:"",
      user:"",
      labelPosition: 'left',
      formLabelAlign: {
        username: '',
        password: '',
      },
    }
  },
  methods:{
    logout(){
      sessionStorage.clear();
      this.$router.push("/");
    },
    setPassword(){
      this.centerDialogVisible=true;
      this.formLabelAlign.username=sessionStorage.getItem("username");

    },
    onSubmit(){
      this.$refs['formLabelAlign'].validate(valid =>{
        if(valid){
          //URL/
          axios.get("" + this.formLabelAlign.username + "/" + this.formLabelAlign.password).then((resp)=>{
            if(resp.data.code===200){
              this.$alert(this.user+":密码修改成功","成功",{
                confirmButtonText:"确认",
              })
            }else {
              this.$alert(this.user+":密码修改失败","失败",{
                confirmButtonText:"确认",
              })
            }
          })
        }else {
          this.$alert("请检查提交内容的格式是否正确","警告",{
            confirmButtonText:"确认",
          })
        }
      })
      this.centerDialogVisible=false;
    }
  },
  created() {
    this.username=sessionStorage.getItem("username");
    axios.get("URL/student/queryById/"+this.username).then((resp)=>{
      this.user=resp.data.sname;
    })
  },
  setup() {
    return {
      centerDialogVisible: ref(false),
    }
  },
}
</script>

<style scoped>

</style>