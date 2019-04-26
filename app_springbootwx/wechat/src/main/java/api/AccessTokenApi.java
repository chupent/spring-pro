package api;

import bean.oauth.AccessToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kit.ConfKit;
import kit.Constant;
import kit.HttpKit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


/**
 * @author chupengtang
 * @version 1.0
 * @ClassName Wechat
 * @Description TODO 获取AccessToken
 * @createdate 2019/4/5 星期五 22:39
 */
public class AccessTokenApi {
    public static String token;
    public static long expires;
    /**
     * 获取AccessToken
     * @param appid 用户唯一凭证 AppId
     * @param secret 用户唯一凭证密钥 AppSecret
     * @return Json字符串
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public  String getAccessToken(String appid,String secret) throws InterruptedException, ExecutionException, IOException {
        Map<String,String> params = new HashMap<>();
        params.put("grant_type","client_credential");
        params.put("appid",appid);
        params.put("secret",secret);
        String body = HttpKit.get(Constant.ACCESS_TOKEN_URL,params);
        return body;
    }
    /**
     * 获取一个新AccessAoken,忽略错误提示
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public  String getNewAccessToken() throws InterruptedException, ExecutionException, IOException {
        String body = getAccessToken(ConfKit.getValue("AppId"),ConfKit.getValue("AppSecret"));
        if(null!=body&&body.contains("errcode")){
            return null;
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        AccessTokenApi.token = gson.fromJson(body,AccessToken.class).getAccess_token();
        AccessTokenApi.expires=System.currentTimeMillis();
        return AccessTokenApi.token;
    }
    /**
     * 获取一个可用的AccessAoken
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public  String getAccessToken() throws InterruptedException, ExecutionException, IOException {
        long timeDiff = (System.currentTimeMillis() - AccessTokenApi.expires)/1000;
        if (null==AccessTokenApi.token||timeDiff>7200){
            return getNewAccessToken();
        }else {
            return AccessTokenApi.token;
        }
    }
}
