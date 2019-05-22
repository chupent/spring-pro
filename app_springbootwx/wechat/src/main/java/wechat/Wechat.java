package wechat;

import wechat.api.OauthApi;
import wechat.bean.oauth.AccessToken;
import wechat.kit.ConfKit;
import wechat.kit.Constant;
import wechat.kit.net.HttpKit;
import wechat.kit.net.ResposeBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName ee
 * @Description TODO
 * @createdate 2019/5/15 星期三 15:26
 */
public class Wechat {
    public static final OauthApi OAUTH_API = new OauthApi();
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
    private static ResposeBody<AccessToken> getAccessToken(String appid, String secret)  {
        Map<String,String> params = new HashMap<>();
        params.put("grant_type","client_credential");
        params.put("appid",appid);
        params.put("secret",secret);
        return HttpKit.get(Constant.ACCESS_TOKEN_URL,params,AccessToken.class);
    }
    /**
     * 获取一个新AccessAoken,忽略错误提示
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public  static String getNewAccessToken() {
        ResposeBody<AccessToken> body =  getAccessToken(ConfKit.getValue("AppId"),ConfKit.getValue("AppSecret"));
        if(null==body||null==body.getT()){
            return null;
        }
        Wechat.token = body.getT().getAccess_token();
        Wechat.expires=System.currentTimeMillis();
        return Wechat.token;
    }
    /**
     * 获取一个可用的AccessAoken
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public static String getAccessToken()  {
        long timeDiff = (System.currentTimeMillis() - Wechat.expires)/1000;
        if (null==Wechat.token||timeDiff>7200){
            return getNewAccessToken();
        }else {
            return Wechat.token;
        }
    }
}
