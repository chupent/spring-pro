package kit;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName Constant
 * @Description TODO
 * @createdate 2019/4/6 星期六 00:44
 */
public class Constant {
    /**
     * 获取基础支持的URL(获取普通AccessToken)
     */
    public static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    /**
     * 用户同意授权，获取code的URL
     */
    public static String OAUTH_CODE_URI = "https://open.weixin.qq.com/connect/oauth2/authorize";
    /**
     * 网页授权AccessToken的URL
     */
    public static String OAUTH_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    /**
     * 刷新AccessToken的URL
     */
    public static String OAUTH_REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
    /**
     * 获取用户信息URL
     */
    public static String OAUTH_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";
    /**
     * 检验授权凭证（AccessToken）是否有效URL
     */
    public static String OAUTH_CHECK_URL = "https://api.weixin.qq.com/sns/auth";








}
