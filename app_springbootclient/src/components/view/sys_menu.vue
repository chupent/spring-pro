<template>
  <div class="app_content">
    {{keys}}
  </div>
</template>

<script>
  export default {
    name: "sys_menu",
    data(){
      return {
        keys:[]
      }
    },
    created() {
      this.test()
    },
    methods: {
      test(){
        var obj = {
          "name": "zhangsan",
          "age": 30
        };
        for (let key in obj) {
          this.keys.push(key)
        }
        console.log( this.keys);
      }
    }
  }
</script>

<style scoped>

</style>
