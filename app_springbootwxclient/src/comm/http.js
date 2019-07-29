/**
 * 基于axios的HTTP请求封装
 */
import axios from 'axios'
import {MessageBox,Indicator} from 'mint-ui'

//基本配置
axios.defaults.timeout = 5000;
axios.defaults.baseURL ='';

/**拦截器**/
axios.interceptors.request.use(
  config=>{
    /**
     * 拦截器指定数据包类参数对应处理：
     * 1、application/x-www-urlencoded :  new URLSearchParams() 、 qs
     * 2、application/json : config.data = JSON.stringify(config.data);
     * 3、multipart/form-data
     * 4、text/xml
     */
    config.headers = {
      'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'
    }
    return config;
  },
  error=>{
    return Promise.reject(err);
  })
/**
 * 打开加载进度
 */
function loading() {
  Indicator.open({
    text:'加载中...',
    spinnerType:'fading-circle'
  })
}
/**
 * 处理结果
 */
function disposeResult(resData,showMsg) {
  if(resData==null){
    return;
  }
  console.log(showMsg)
  if(showMsg&&resData.status==400){
    MessageBox.alert('message',resData.message)
  }
  if(resData.status==403){
    MessageBox.alert('message',resData.data)
  }
}
/**
 * GET 异步封装
 * @param url
 * @param param
 * @returns {Promise<any>}
 */
export function get(url,params={},showMsg) {
  return new Promise((resolve,reject) => {
    loading()
    axios.get(url,{
      params:params
    }).then(response => {
      let resData = response.data
      resolve(resData)
      disposeResult(resData,showMsg)
      Indicator.close()
    }).catch(err => {
      reject(err)
      Indicator.close()
      if(showMsg){
        MessageBox.alert('message',err)
      }
    })
  })
}
/**
 *  POST 异步封装
 * @param url
 * @param data
 * @returns {Promise<any>}
 */
function post(url,data = {}){
  return new Promise((resolve,reject) => {
    loading()
    axios.post(url,{
      params:params
    }).then(response => {
      let resData = response.data
      resolve(resData)
      disposeResult(resData,showMsg)
      Indicator.close()
    }).catch(err => {
      reject(err)
      Indicator.close()
      if(showMsg){
        MessageBox.alert('message',err)
      }
    })
  })
}
/**
 * PATCH 封装
 * @param url
 * @param data
 * @returns {Promise<any>}
 */
function patch(url,data = {}){
  return new Promise((resolve,reject) => {
    loading()
    axios.patch(url,{
      params:params
    }).then(response => {
      let resData = response.data
      resolve(resData)
      disposeResult(resData,showMsg)
      Indicator.close()
    }).catch(err => {
      reject(err)
      Indicator.close()
      if(showMsg){
        MessageBox.alert('message',err)
      }
    })
  })
}
/**
 * PUT封装
 * @param url
 * @param data
 * @returns {Promise<any>}
 */
function put(url,data = {}){
  return new Promise((resolve,reject) => {
    loading()
    axios.put(url,{
      params:params
    }).then(response => {
      let resData = response.data
      resolve(resData)
      disposeResult(resData,showMsg)
      Indicator.close()
    }).catch(err => {
      reject(err)
      Indicator.close()
      if(showMsg){
        MessageBox.alert('message',err)
      }
    })
  })
}


