import {ADD_ROUTES} from './mutations_type'
import Menufilter from './menufilter'
import router from '../../../router/index'
const addRoutes = {
  state: {
    routeData: []
  },
  mutations: {
    [ADD_ROUTES](state, addrouter) {
      let parant={
        path: '/main',
        name: 'main',
        redirect:'/menu_system',
        meta: {allowBack: false},//禁止后退标识
        component: () => import('@//components/main-page.vue'),
        children:[
          {
            path: '/menu_system',
            name: 'menu_system',
            component: () => import('@//components/view/menu_system.vue'),
          }
        ]
      }
      Menufilter(parant.children, addrouter) // 将后台的路由数据components转化成组件

      let routes = []
      routes.push(parant)
      router.addRoutes(routes)  // 添加路由
      router.replace('/main')
      // setTimeout(function(){
      //
      // }, 1000);

    }
  },
  actions: {
    add_Routes({commit}, addrouter) {
      commit(ADD_ROUTES, addrouter)
    }
  }
}
export default addRoutes
