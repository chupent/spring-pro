<template>
  <div id="app">
    {{mes}}
    <router-view/>
  </div>
</template>

<script>
import {get} from './comm/http'
export default {
  name: 'App',
  data(){
    return {
      mes:''
    }
  },created(){
    this.userinfo()
  },
  methods:{
    userinfo:function(){
      get('/wx/app/user/userinfo',{},true).then(res=>{
        console.log(res)
      })
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
