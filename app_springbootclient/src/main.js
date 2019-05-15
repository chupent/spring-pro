// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

//Api
import apivar from './assets/js/net/api'
Vue.prototype.$api=apivar.api;

//http请求封装
import {fetch,post,patch,put,url} from './assets/js/net/http'
Vue.prototype.$post=post;
Vue.prototype.$fetch=fetch;
Vue.prototype.$patch=patch;
Vue.prototype.$put=put;

//axios
import axios from 'axios'
Vue.prototype.$axios = axios;
Vue.prototype.url = 'wx' //全局URL

//import VueAxios from 'vue-axios'
//Vue.use(VueAxios,Axios);//全局方法 Vue.use() 使用插件

Vue.config.productionTip = false


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
