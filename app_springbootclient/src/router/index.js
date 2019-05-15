import Vue from 'vue'
import Router from 'vue-router'
import main from '@/components/main-page'

Vue.use(Router)

const router =  new Router({
  model:'history',//去点URL中的#
  routes: [
    {
      path: '/main',
      name: 'main',
      component: main
    }
  ]
})
export default router
