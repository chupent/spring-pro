/**
 * 基于axios的HTTP请求封装
 */
import axios from 'axios'

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
 * GET 封装
 * @param url
 * @param param
 * @returns {Promise<any>}
 */
export function fetch(url,params={}) {
  return new Promise((resolve,reject) => {
    axios.get(url,{
      params:params
    }).then(response => {
      resolve(response.data);
    }).catch(err => {
      reject(err)
    })
  })
}
/**
 *  POST 封装
 * @param url
 * @param data
 * @returns {Promise<any>}
 */
export function post(url,data = {}){
  return new Promise((resolve,reject) => {
    axios.post(url,data)
      .then(response => {
        resolve(response.data);
      },err => {
        reject(err)
      })
  })
}
/**
 * PATCH 封装
 * @param url
 * @param data
 * @returns {Promise<any>}
 */
export function patch(url,data = {}){
  return new Promise((resolve,reject) => {
    axios.patch(url,data)
      .then(response => {
        resolve(response.data);
      },err => {
        reject(err)
      })
  })
}
/**
 *  PUT封装
 * @param url
 * @param data
 * @returns {Promise<any>}
 */
export function put(url,data = {}){
  return new Promise((resolve,reject) => {
    axios.put(url,data)
      .then(response => {
        resolve(response.data);
      },err => {
        reject(err)
      })
  })
}
