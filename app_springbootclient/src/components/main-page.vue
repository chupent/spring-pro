<template>
  <el-container style="height: 100%;">
    <el-header>
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
      <el-aside width="200px">
        <el-menu>
          <menupage :menusData="userData.resources"></menupage>
        </el-menu>
      </el-aside>

      <el-main class="app_main">
        <!--面包屑-->
        <el-row class="breadcrumb">
          <el-breadcrumb separator-class="el-icon-arrow-right" class="nat">
            <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>活动管理</el-breadcrumb-item>
            <el-breadcrumb-item>活动列表</el-breadcrumb-item>
            <el-breadcrumb-item>活动详情</el-breadcrumb-item>
          </el-breadcrumb>
        </el-row>

        <el-row class="conent">
          <!--路由页面-->
          <el-table :data="tableData">
            <el-table-column prop="date" label="日期" width="140">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="address" label="地址">
            </el-table-column>
          </el-table>
        </el-row>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  import menupage from './menu-page.vue'
  export default {
    components: {
      menupage
    },
    data () {
      const item = {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      };
      return {
        userData: JSON.parse(window.sessionStorage.getItem("userinfo")),
        tableData: Array(10).fill(item)
      }
    },methods:{
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      logout:function(){
        localStorage.clear()
        sessionStorage.clear();
        this.$router.replace({ path:'/login'})
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
  .app_main{
    margin: 0px;
    padding: 0px;
  }
  .breadcrumb{
    padding: 10px;
    border-bottom: #DCDFE6 1px solid;
  }
  .conent{
    padding: 10px;
  }
</style>

