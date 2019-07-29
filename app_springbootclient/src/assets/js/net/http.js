/**
 * 基于axios的HTTP请求封装
 */
import axios from 'axios'
import { Loading ,Message} from 'element-ui';
import router from '../../../router'
const options = {
  fullscreen: true, //全屏设置
  lock: true,//同 v-loading 指令中的 lock 修饰符
  text: '拼命加载中', //文字
  spinner: 'el-icon-loading',//动画图标
  background: 'rgba(0,0,0, 0.7)' //背景
}
const CONST_WX  = '/wx' ;
const CONST_POST  = 'post' ;
const CONST_GET  = 'get' ;
const CONST_TIMEOUT  = 5000 ;
var loadingInstance ;

function openLogining(isopen) {
  if(undefined!=isopen&&null!=isopen&&isopen){
    loadingInstance = Loading.service(options);
    setTimeout(() => { //超时后自动关闭
      if(null!=loadingInstance&&undefined!=loadingInstance){
        loadingInstance.close();
      }
    }, CONST_TIMEOUT);
  }
}
function closeLogin(status,message) {
  if(null!=loadingInstance&&undefined!=loadingInstance){
    setTimeout(() => {
      loadingInstance.close();
      openMessage(status,message)
    }, status!=200?500:1000); //请求失败加载进度直接关闭，请求成功延迟1s
  }else{
      openMessage(status,message)
  }
}
function openMessage(status,message) {
  if(status!=200){ //请求失败提示
    if(status==403){
      message = '登陆失效,请重新登陆!'
      sessionStorage.clear()
      router.replace({ path:'/login'})//跳转登陆页
    }
    Message.error(message)
  }
}


//基本配置
const service = axios.create({
  baseURL: '',  // api的base_url
  timeout: CONST_TIMEOUT  // 请求超时时间
})
/**Request拦截器**/
service.interceptors.request.use(
  config=>{
    /**
     * 拦截器指定数据包类参数对应处理：
     * 1、application/x-www-urlencoded :  new URLSearchParams() 、 qs
     * 2、application/json : config.data = JSON.stringify(config.data);
     * 3、multipart/form-data
     * 4、text/xml
     */
    let userinfo = JSON.parse(sessionStorage.getItem("userinfo"))
    if(null!=userinfo){
      config.headers['Authorization']= userinfo.Authorization
    }
    if(config.method==CONST_POST && config.url=='/wx/login'){
      config.headers['Content-Type']= 'application/x-www-form-urlencoded;charset=UTF-8';
    } else if(config.method==CONST_POST){
      config.headers['Content-Type']= 'application/json;charset=UTF-8';
    }
    return config;
  },
  error=>{
    //错误请求提示
    closeLogin(400,'操作失败，请求地址有误！')
    return Promise.reject(err);
  })
/**Response**/
service.interceptors.response.use(response=>{//成功请求到数据
  let resCode = response.data.resCode;
  closeLogin(resCode,response.data.resMessage)
  if(resCode==200){
    return response.data.resData;
  }
},
error => {
  closeLogin(400,'操作失败！')
  return Promise.reject(err);
})




/**
 * Post application/json;charset=UTF-8格式请求
 * @param url
 * @param data
 * @returns {AxiosPromise}
 */
export const postJsonRequest = (url,data,isopen) => {
  openLogining(isopen)
  return service({url: CONST_WX+url,method: CONST_POST,data:JSON.stringify(data)})
}
/**
 * POST application/x-www-form-urlencoded;charset=UTF-8格式请求
 * @param url
 * @param data
 * @returns {AxiosPromise}
 */
export const postRequest = (url,data,isopen) =>{
  openLogining(isopen)
  return service({url: CONST_WX+url,method:CONST_POST,data})
}
/**
 *
 * @param url
 * @param data
 * @returns {AxiosPromise}
 */
export const getRequest = (url,data,isopen) =>{
  openLogining(isopen)
  return service({url: CONST_WX+url,method: CONST_GET,params:data})
}

export default service

// /**
//  * PATCH 封装
//  * @param url
//  * @param data
//  * @returns {Promise<any>}
//  */
// export function patch(url,data = {}){
//   return new Promise((resolve,reject) => {
//     axios.patch(url,data)
//       .then(response => {
//         resolve(response.data);
//       },err => {
//         reject(err)
//       })
//   })
// }

