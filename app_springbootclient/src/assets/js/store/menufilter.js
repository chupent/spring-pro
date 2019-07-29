import {lazy} from './lazyLoading'
export default (routers,data) => {
  //要重新遍历一下，是因为，通常我们动态路由的时候，
  //是获取服务端数据，这个component属性是一个字符串转化成组件
  generaMenu(routers,data)
}
/**
 * 要重新遍历一下，通常我们动态路由的时候，是获取服务端数据，这个component属性是一个字符串转化成组件
 * @param routers
 * @param data
 */

function generaMenu(routers,data) {
  data.forEach((item)=>{
      var resurl=item.resUrl
      if(resurl!=null&&resurl.length>0){
        var names = resurl.split('/')
        var name  = names[(names.length-1)]
        var route={
          path: '/'+resurl,
          name: name,
          component: lazy(resurl),
          children:[]
        }
        routers.push(route)
      }
      var childResource = item.childResource;
      if(childResource!=null){
        generaMenu(routers,item.childResource)
      }
  })
}
