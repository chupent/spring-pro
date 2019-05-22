package wechat.api;

import wechat.bean.oauth.OauthAccessToken;
import wechat.bean.WechatUserInfo;
import wechat.kit.*;
import wechat.kit.net.HttpKit;
import wechat.kit.net.ResposeBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


/**
 * @author chupengtang
 * @version 1.0
 * @ClassName OauthApi
 * @Description TODO 微信网页授权
 * 两种scope发起网页授权:
 *  snsapi_base：静默授权并自动跳转页面，用了去openid，用户无法感知
 *  snsapi_userinfo：授权需要用户手动同意，授权后可获取用户信息(只需一次，即之后操作为静默授权)
 *
 * 1 第一步：用户同意授权，获取code
 * 2 第二步：通过code换取网页授权access_token
 * 3 第三步：刷新access_token（如果需要）
 * 4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
 *
 * @createdate 2019/4/6 星期六 01:53
 */
public class OauthApi {
    /**
     * 用户同意授权,获取code
     * @return code
     */
    public static String getCode() throws UnsupportedEncodingException {
        StringBuilder builder = new StringBuilder();
        builder.append("?appid=").append(ConfKit.getValue("AppId"))
               .append("&redirect_uri=").append(ConfKit.getValue("DomainName")).append(ConfKit.getValue("RedirectUri"))
               .append("&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
        return Constant.OAUTH_CODE_URI + SignatrueKit.buildSign(builder.toString(),false);
    }

    /**
     * 获取网页授权AccessToken
     * @param code 用户收取同意后获取
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public  ResposeBody<OauthAccessToken> getAccessToken(String code) {
        Map<String,String> params = new HashMap<>();
        params.put("appid",ConfKit.getValue("AppId"));
        params.put("secret",ConfKit.getValue("AppSecret"));
        params.put("code",code);
        params.put("grant_type","authorization_code");

        ResposeBody<OauthAccessToken> resposeBody = HttpKit.get(Constant.OAUTH_ACCESS_TOKEN_URL,params,OauthAccessToken.class);
        return resposeBody;
    }

    /**
     * 刷新access_token，access_token拥有较短的有效期（7200s），当access_token超时后，可以使用refresh_token进行刷新，
     * refresh_token有效期为30天，当refresh_token失效之后，需要用户重新授权。
     * @param refreshToken
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public ResposeBody<OauthAccessToken> getRefreshToken(String refreshToken)  {
        Map<String,String> params = new HashMap<>();
        params.put("appid",ConfKit.getValue("AppId"));
        params.put("refresh_token",refreshToken);
        params.put("grant_type","refresh_token");
        ResposeBody<OauthAccessToken> resposeBody = HttpKit.get(Constant.OAUTH_REFRESH_TOKEN_URL,params,OauthAccessToken.class);
        return resposeBody;
    }

    /**
     * 检验授权凭证（access_token）是否有效
     * @param accessToken
     * @param openid
     * @return true 为有效
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public boolean checkAccessToken(String accessToken,String openid)  {
        Map<String,String> params = new HashMap<>();
        params.put("access_token",accessToken);
        params.put("openid","openid");
        ResposeBody resposeBody =  HttpKit.get(Constant.OAUTH_CHECK_URL,params);
        if(null!=resposeBody&&resposeBody.getErrcode()==0&&"ok".equals(resposeBody.getErrmsg())) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 获取微信用户信息
     * @param accessToken 网页授权的access_token
     * @param openid 用户唯一标识
     * @param lang 响应语言选择
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws IOException
     */
    public ResposeBody<WechatUserInfo> getUserInfo(String accessToken,String openid,Lang lang)  {
        Map<String,String> params = new HashMap<>();
        params.put("access_token",accessToken);
        params.put("openid",openid);
        params.put("lang",lang.getName());
        ResposeBody<WechatUserInfo> resposeBody = HttpKit.get(Constant.OAUTH_USERINFO_URL,params,WechatUserInfo.class);
        return resposeBody;
    }
}
