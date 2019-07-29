<template>
  <div class="widget_conent">

    <el-row class="app_table">
      <el-table :data="tableData.content"
                v-loading="isloading"
                element-loading-text="拼命加载中"
                element-loading-spinner="el-icon-loading"
                element-loading-background="rgba(211,211,211, 0.7)"
                >
        <el-table-column v-for="(arr) in keys" v-bind:prop="arr" v-bind:label="arr"/>
        <el-table-column align="right" label="操作">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">修改</el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>

    <el-row class="app_pagination">
      <el-pagination
        background
        layout="prev, pager, next"
        @current-change="handleCurrentChange"
        :total="this.tableData!=undefined?this.tableData.totalElements:0"
        :page-size="this.tableData!=undefined?this.tableData.pageSize:0"
        :page-count="this.tableData!=undefined?this.tableData.totalPages:0"
        :hide-on-single-page="true"></el-pagination>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "pagetable",
    props:["model"],//表单参数
    data() {
      return {
        tableData: {},
        keys:[],
        isloading:false
      }
    },
    created() {
      this.showUsers()
    },
    methods: {
      showUsers() {
        if(this.isloading){
          this.isloading =this.model.loading
        }
        this.$postJsonRequest(this.model.url, this.model.param,!this.isloading).then(res => {
          if(undefined!=res&&null!=res){
            var obj = res.content[0];
            this.keys=[]
            for(var st in obj) {
              this.keys.push(st)
            }
            this.tableData = res
          }
          this.closeLogin()
        }).catch(err => {
          this.closeLogin()
        })
      },
      handleCurrentChange(val) {
        this.model.param.page=(val-1)
        this.showUsers()
      },
      closeLogin(){
        if(this.isloading){
          setTimeout(() => {
            this.loading = false
          }, 500);
        }
      },
      handleEdit(index,row){
        console.log(index)
        console.log(row)
      },
      handleDelete(index,row){
        console.log(index)
        console.log(row)
      }
    }
  }
</script>

<style scoped>
  .app_table {
    margin: 10px;
  }
  .app_pagination {
    margin-top: 20px;
  }
</style>

