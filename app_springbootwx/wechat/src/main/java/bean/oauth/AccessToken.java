package bean.oauth;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName Token
 * @Description TODO 普通AccessToken
 * @createdate 2019/4/6 星期六 01:28
 */
public class AccessToken {
    /**
     * token凭证<网页授权回值与普通的有区别>
     */
    private String access_token;
    /**
     * access_token接口调用凭证超时时间，单位（秒）
     */
    private long expires_in;
    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }
}
