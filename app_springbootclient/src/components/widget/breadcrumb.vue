<!--面包屑组件-->
<template>
  <el-breadcrumb separator-class="el-icon-arrow-right" class="nat">
    <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item v-for="(item) in tag">{{item.resName}}</el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script>

  export default {
    name: "breadcrumb",
    props: ['tag'],
    data() {
      return {}
    }
  }
</script>
<style scoped>
</style>
