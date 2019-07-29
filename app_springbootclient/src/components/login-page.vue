<template>
  <div class="content">
    <div class="login-container">
      <el-row>
        <el-col :span="21">
          <div class="grid-content bg-purple">
            <h3 class="login_title">系统登录</h3>
          </div>
        </el-col>
        <el-col :span="3">
          <div class="grid-content bg-purple-light" @click="openQRCode">
            <i class="el-icon-monitor" ></i>
          </div>
        </el-col>
      </el-row>

      <div class="class_1" :hidden="loginType">
        <el-form   label-position="left" label-width="0px">
          <el-form-item prop="account">
            <el-input type="text" v-model="username"auto-complete="off" placeholder="账号"></el-input>
          </el-form-item>
          <el-form-item prop="checkPass">
            <el-input type="password" v-model="password"auto-complete="off" placeholder="密码"></el-input>
          </el-form-item>
          <el-form-item style="width: 100%">
            <el-button type="primary" style="width: 100%" :loading="loading" @click="submitClick">{{!loading?'登录':'正在登陆中..'}}</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="class_1" :hidden="!loginType">
        <el-image :src="'data:image/png;base64,'+image" @click="refQRCode" style="width: 150px;height: 150px"/>
      </div>
    </div>
  </div>
</template>

<script>
  import {mapActions} from 'vuex'
    export default {
      name: "login-page",
      data(){
        return {
          loginType:false,
          username:"15721539459",
          password:"admin",
          image:"",
          loading:false
        }
      },methods:{
        ...mapActions({add_Routes: 'add_Routes'}),
        submitClick(){
          this.loading=true
          //参数方式一
          var paramter = new URLSearchParams()
          paramter.append('username',this.username)
          paramter.append('password',this.password)
          this.$postRequest('/login',paramter).then(res=>{
            this.loading=false
            if(undefined!=res&&null!=res){
              sessionStorage.setItem("userinfo",JSON.stringify(res))
              this.add_Routes(res.resources);
            }
          })
        },
        openQRCode(){
          if(this.loginType){
            this.loginType = false
          }else {
            this.loginType = true
            this.refQRCode()
          }
        },
        refQRCode(){
          this.$getRequest('/api/user/getQRCode',{time:(new Date()).valueOf()}).then(res=>{
            this.image = res
          })
        }
      }
    }
</script>

<style scoped>
  .content{
    background-image:url("../assets/9f18148a7e8ad7975b3f001e5a93ef92.jpg");
    height: 100%;
  }
  .login-container {
    border-radius: 4px;
    background-clip: padding-box;
    margin-top:10%;
    margin-left: 35%;
    width: 350px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #efefef;
    min-height: 300px;
  }
  .class_1{
    padding: 0px 35px 15px 35px;
  }
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
  .bg-purple {
    /*background: #d3dce6;*/
  }
  .bg-purple-light {
    /*background: #e5e9f2;*/
    text-align: center;
  }
  .grid-content {
    min-height: 36px;
    padding-top: 10px;
  }
</style>
