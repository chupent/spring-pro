<template>
  <div class="hello">
    {{msg}}
  </div>
</template>

<script>
  export default {
    data () {
      return {
        msg: localStorage.getItem('message')
      }
    },methods:{
        getdata:function(event){
          this.$fetch(this.$api.testapi,{
            param:'15721539459'
          }).then((res)=>{
            console.log(res.result)
          })
        },
      postData:function () {
        var params = new URLSearchParams();
        params.append('account', '15721539459');
        params.append('password', 'admin');
        console.log(params)

        this.$post(this.$api.login,params).then((res)=>{
          console.log(res)
        })
      }
    }
}
</script>

