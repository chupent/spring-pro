<template>
  <div id="app">
    <transition :name="direction">
      <keep-alive>
        　<router-view class="Router"></router-view>
      </keep-alive>
    </transition>
  </div>
</template>

<script>
export default {
  name: 'App',
  data(){
    return {
      direction: "slide-right"
    }
  },created(){

  },mounted(){
    //某些也面禁止后退
    window.onpopstate = () => {
      if (!this.allowBack) {    //    这个allowBack 是存在vuex里面的变量
        history.go(1)
      }
    }
  },methods:{
    // getuserinfo:function () {
    //   this.$fetch(this.$api.getuserinfo).then((res)=>{
    //     console.log(res)
    //     if(res!=undefined){
    //       if(200==res.status){
    //         this.localStorage.userinfo=JSON.stringify(res.data) //请求成功，缓存用户信息
    //         this.$router.push({ path:'/main'})
    //       } else if(400==res.status){//请求失败，弹框提示
    //         window.localStorage.setItem("message",res.message)
    //         this.$router.push({ path:'/main'})
    //       } else if(403==res.status){//微信授权，弹框提示
    //         console.log(res.message)
    //       }
    //     }
    //   }).catch((err)=>{
    //     console.log(err)
    //     //错误弹框提示
    //   })
    // }
  },watch:{
    $router(to, from){
      const toDepth = to.path.split("/").length;
      const fromDepth = from.path.split("/").length;
      if (to.path == '/'){
        this.direction = "slide-right";
      }else if (from.path == '/') {
        this.direction = "slide-left";
      }else {
        this.direction = toDepth < fromDepth ? "slide-right" : "slide-left";
      }
    }
  }
}
</script>

<style>
body{
  padding: 0px;
  margin: 0px;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 0px;
  padding: 0px;
}
.Router {
  position: absolute;
  width:100%;
  transition: transform 0.3s ease-out;
}
.slide-left-enter{
  transform: translate(100%, 0);
}
.slide-left-leave-active{
  transform: translate(-50%, 0);
}
.slide-right-enter {
  transform: translate(-50%, 0);
}
.slide-right-leave-active{
  transform: translate(100%, 0);
}
</style>
