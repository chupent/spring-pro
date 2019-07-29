// 懒加载组件
function lazy(name) {
  return () => import(`@//components/${name}.vue`)
}
export {lazy}
