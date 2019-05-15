package wechat.kit;

import wechat.bean.WechatError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName CallBackApi
 * @Description TODO
 * @createdate 2019/4/6 星期六 00:07
 */
public abstract class CallBackApi<T> {
    private Gson gson ;

    public CallBackApi() {
        this.gson =  new GsonBuilder().setPrettyPrinting().create();
    }
    public CallBackApi formJosn(String json,Class<T> t){
        if(json.contains("errcode")){
            this.fail(gson.fromJson(json, WechatError.class));
        }else {
            this.succeed(gson.fromJson(json, t));
        }
        return this;
    }
    public void fail(WechatError error){}
    public abstract void succeed(T t);
}
