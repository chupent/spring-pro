import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from "element-ui"
import 'element-ui/lib/theme-chalk/index.css'
import apivar from './assets/js/net/api'
import {fetch,post} from './assets/js/net/http'
import store from './store/store'


Vue.config.productionTip = false
Vue.prototype.$api=apivar.api;
Vue.prototype.$post=post;
Vue.prototype.$fetch=fetch;
Vue.config.productionTip = false
Vue.use(ElementUI)

//动态路由防止用户刷新丢失
if (sessionStorage.getItem('userinfo')) {
  let routes = JSON.parse(sessionStorage.getItem('routes'))
  store.dispatch("add_Routes", routes)
}
//路由全局判断是否登录
router.beforeEach((to, from , next) => {
  if (!sessionStorage.getItem("userinfo") && to.path !== '/login') {
    next({
      path: '/login',
      //query: {redirect: to.fullPath}
    })
  } else {
    next()
    //某些特殊页不能后退
    let allowBack = true    //    给个默认值true
    if (to.meta.allowBack !== undefined) {
      allowBack = to.meta.allowBack
    }
    if (!allowBack) {
      history.pushState(null, null, location.href)
    }
    store.dispatch('updateAppSetting', {
      allowBack: allowBack
    })
  }
})

new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>'
})
