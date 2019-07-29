<template>
  <div class="app_content">
    <el-form :inline="true" :model="model" class="demo-form-inline">
      <el-form-item label="账号">
        <el-input v-model="model.param.account" placeholder="账号" />
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="model.param.username" placeholder="姓名" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>
    <pagetable ref="PageTable" :model="model"></pagetable>
  </div>
</template>

<script>
  import pagetable from '../widget/pagetable'
  export default {
    name: "menu_system",
    components:{pagetable},
    data() {
      return {
        model:{
          url:'/api/user/getUsers',
          loading:false,
          param:{
            page: 0,
            size: 2,
            username: "",
            account: ""
          }
        }
      }
    },methods:{
      onSubmit:function () {
        this.$refs.PageTable.showUsers()//调用子组件函数，进行网络请求数据异步刷新页面
      }
    }
  }
</script>

<style scoped>
  .app_content {
    margin: 10px;
    height: 89%;
    padding: 10px;
    border: #DCDFE6 1px solid;
  }
</style>
