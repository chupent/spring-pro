<template>
  <el-form  class="login-container" label-position="left" label-width="0px">
    <h3 class="login_title">系统登录</h3>
    <el-form-item prop="account">
      <el-input type="text" v-model="username"auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="checkPass">
      <el-input type="password" v-model="password"auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%" @click="submitClick">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import {mapActions} from 'vuex'
    export default {
      name: "login-page",
      data(){
        return {
          username:"15721539459",
          password:"admin"
        }
      },methods:{
        ...mapActions({add_Routes: 'add_Routes'}),
        submitClick(){
          //参数方式一
          var paramter = new URLSearchParams()
          paramter.append('username',this.username)
          paramter.append('password',this.password)
          this.$post(this.$api.login,paramter).then((res)=>{
            console.log(res)
            if(200==res.resCode){
              var userinfo = JSON.stringify(res.resData);
              console.log(userinfo)
              sessionStorage.setItem("userinfo",userinfo)
              var routerData = [{
                path: '/page1',
                name: 'page1',
                component:'page1'
              }]
              sessionStorage.setItem('routes', JSON.stringify(routerData))
              this.add_Routes(routerData);
            }else {
              alert(res.resMessage)
            }
          })
        }
      }
    }
</script>

<style scoped>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin-top:10%;
    margin-left: 35%;
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
  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }
</style>
