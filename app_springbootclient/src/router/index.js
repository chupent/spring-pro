import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

const router =  new Router({
  model:'history',
  routes: [
    {
      path: '/login',
      name: 'login',
      meta: {allowBack: false},//禁止后退标识
      component: () => import('@//components/login-page.vue')
    },
    {
      path: '/main',
      name: 'main',
      meta: {allowBack: false},//禁止后退标识
      component: () => import('@//components/main-page.vue')
    },
    {
      path: '*',
      name: '404',
      component: () => import('@//components/404-page.vue')
    }
  ]
})
export default router
