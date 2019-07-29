/**
 * Seesion数据存储
 */

/**
 * 从session中获取Authorization
 * @returns {*}
 */
export function getAuthorization() {
  let data = getLoginInfo("userinfo")
  return null!=data?data.Authorization:null;
}
/**
 * 获取用户信息
 * @returns {null}
 */
export function getUserInfo() {
  let data = getLoginInfo("userinfo")
  return null!=data?data.userInfo:null;
}
/**
 * 获取菜单权限
 * @returns {null}
 */
export function getResources() {
  let data = getLoginInfo("userinfo")
  return null!=data?data.resources:null;
}
/**
 * 从session中获取用户登录信息
 * @returns {*}
 */
export function getLoginInfo() {
  return getDataFromSession("userinfo");
}
/**
 * 从session中获取信息
 * @param key
 * @returns {*}
 */
export function getDataFromSession(key) {
  let string = sessionStorage.getItem(key);
  let data = JSON.parse(string);
  if (null!=data){
    return data;
  }
  return null
}

/**
 * 设置用户登录信息到session
 * @param data
 */
export function setUserInfo(userinfoData) {
  setUserInfo("userinfo",JSON.stringify(userinfoData))
}
/**
 * 存储信息到session
 * @param key key
 * @param data 对象
 */
export function setDataToSession(key,data) {
  sessionStorage.setItem(key,JSON.stringify(data));
}

/**
 * 从session中移除数据
 * @param key
 */
export function delDataFromSession(key) {
  sessionStorage.removeItem(key);
}
/**
 * 移除登录信息
 */
export function delLoginInfo() {
  sessionStorage.removeItem("userinfo");
}
