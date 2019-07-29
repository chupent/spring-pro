<template>
  <el-container style="height: 100%;">
    <el-header class="app_header">
      <el-dropdown>
        <i class="el-icon-setting" style="margin-right: 15px"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>查看</el-dropdown-item>
          <el-dropdown-item>新增</el-dropdown-item>
          <el-dropdown-item><el-link @click="logout">退出</el-link></el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <span>{{userData.userInfo.userName}}</span>
    </el-header>

    <el-container>
      <!--菜单-->
      <el-aside class="app_menu" width="200px">
        <el-menu :router='true'>
          <menupage :menusData="userData.resources"></menupage>
        </el-menu>
      </el-aside>
      <el-main class="app_main">
        <div class="app_breadcrumb">
          <breadcrumb :tag="tag"></breadcrumb>
        </div>
        <router-view class="router_tow"></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  import menupage from './widget/menu.vue'
  import Breadcrumb from "./widget/breadcrumb";
  import {findMenus} from '../assets/js/menus'
  export default {
    components: {menupage,Breadcrumb},
    data () {
      return {
        userData: JSON.parse(window.sessionStorage.getItem("userinfo")),
        tag:findMenus(this.$route.path)
      }
    },methods:{
      logout:function(){
        localStorage.clear()
        sessionStorage.clear();
        this.$router.replace({ path:'/login'})
      },
      // handleSelect:function (key, keyPath) {
      //
      // }
    },watch:{
      $route(){
        this.tag=findMenus(this.$route.path)
      }
    }
}
</script>
<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
    text-align: right;
    font-size: 12px;
  }
  .el-menu{
    height: 100%;
  }
  .el-aside {
    background-color: #FFF;
  }
  .router_tow{

  }
  .app_main {
    margin: 0px;
    padding: 0px;
  }
  .app_breadcrumb{
    padding: 10px;
    border-bottom:  #DCDFE6 1px solid;
  }
</style>

