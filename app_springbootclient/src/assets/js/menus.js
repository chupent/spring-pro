export function findMenus(path) {
  let str  = sessionStorage.getItem('userinfo');
  if(null!=str){
    let userinfo = JSON.parse(str);
    let resources= userinfo.resources;
    var array = []
    //第一次查找节点
    for(var i =0 ;i<resources.length;i++){
      var res = resources[i]
      var tmparray = []
      var item = {
        resName:res.resName, resUrl:res.resUrl
      }
      tmparray.push(item)
      if(null!=res.childResource){
        var  bl = findItem(path,res.childResource,tmparray) //第二次查找子节点
        if(bl){
          array=tmparray;
          break
        }
      }
      if(path=='/'+res.resUrl){
        array=tmparray;
      }
    }
    return array
  }
}

function findItem(path,childResource,tmparray) {
  var stop = false
  for(var i =0 ;i<childResource.length;i++){
    var res = childResource[i]
    var item = {resName:res.resName, resUrl:res.resUrl}
    var tmp = [] //临时item，如果匹配不到节点它将舍弃
    tmp.push(item)

    if(null!=res.childResource){
      var bl = findItem(path,res.childResource,tmparray)
      if(bl){ //找到节点退出
        tmparray.push(tmp[0])
      }
    }else if(path=='/'+res.resUrl){//找到节点停止循环
      stop = true
      tmparray.push(item)
      break
    }
  }
  return stop
}

