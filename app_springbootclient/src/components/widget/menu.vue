<!--菜单组件-->
<template>
  <div class="widget_content">
    <label v-for="(menu,i) in menusData">
      <el-menu-item v-if="menu.childResource==null" :key="menu.resId" :data="menu" :index="tag==null?(i+1)+'':tag+'-'+(i+1)" :route="'/'+menu.resUrl">
        <i v-if="menu.resIcon!=null" :class="menu.resIcon"></i>
        <span slot="title" >{{menu.resName}}</span>
      </el-menu-item>
      <el-submenu v-if="menu.childResource" :key="menu.resId" :data="menu" :index="tag==null?(i+1)+'':tag+'-'+(i+1)">
        <template slot="title">
          <i v-if="menu.resIcon!=null" :class="menu.resIcon"></i>
          <span slot="title">{{menu.resName}}</span>
        </template>
        <menupage :menus-data="menu.childResource" :tag="tag==null?(i+1):tag+'-'+(i+1)"></menupage>
      </el-submenu>
    </label>
  </div>
</template>

<script>
  export default {
    name:"menupage",
    props: ['menusData','tag'],
    data() {
      return {}
    },
    methods: {}
  }
</script>

<style scoped>

</style>
