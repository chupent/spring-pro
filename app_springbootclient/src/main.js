import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from "element-ui"
import 'element-ui/lib/theme-chalk/index.css'
import store from './assets/js/store/store'
import {postRequest,postJsonRequest,getRequest} from './assets/js/net/http'

Vue.prototype.$postRequest = postRequest;
Vue.prototype.$postJsonRequest = postJsonRequest;
Vue.prototype.$getRequest = getRequest;


Vue.config.productionTip = false
Vue.use(ElementUI)

//动态路由防止用户刷新丢失
if (sessionStorage.getItem('userinfo')) {
  let routes = JSON.parse(sessionStorage.getItem('userinfo'))
  store.dispatch("add_Routes", routes.resources)
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
    // store.dispatch('updateAppSetting', {
    //   allowBack: allowBack
    // })
  }
})

new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>'
})
