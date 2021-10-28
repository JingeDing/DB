<template>
  <div>
    <body id="poster">
      <el-form class="login-container" label-position="left" label-width="0px">
        <h3 class="login_title">教务系统登录</h3>
        <el-form-item>
          <el-input
            type="text"
            v-model="loginForm.username"
            auto-complete="off"
            placeholder="账号"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-input
            type="password"
            v-model="loginForm.password"
            auto-complete="off"
            placeholder="密码"
          ></el-input>
        </el-form-item>
        <el-form-item style="width: 100%">
          <el-button
            type="primary"
            style="width: 100%; background: #505458; border: none"
            v-on:click="login"
            >登录</el-button
          >
        </el-form-item>
      </el-form>
    </body>
    <div></div>
  </div>
</template>
<script>
import axios from "axios";

export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    login() {
      const _this = this;
      this.axios({
        method: "post",
        url: "",
        data: this.loginForm,
      })
        .then((response) => {
          let { msg, code, user } = response;
          if (response.data.code === 200) {
            _this.$message({
              message: "登录成功",
              type: "success",
            });
            if (this.loginForm.username === "admin") {
              sessionStorage.setItem("token", response.data.token);
              sessionStorage.setItem("username", "admin");
              this.$router.push({ path: "/home" });
            } else {
              sessionStorage.setItem("username", response.data.data.username);
              this.$router.push({ path: "/studentView" });
            }
          } else {
            _this.$message({
              message: "账号或密码错误",
              type: "error",
            });
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
};
</script>

<style>
#poster {
  background-image: url("");
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}

body {
  margin: 0px;
}

.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 150px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
</style>
