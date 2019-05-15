package wechat.kit;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SignatrueKit
 * @Description TODO 构建签名设置
 * @createdate 2019/4/6 星期六 02:21
 */
public class SignatrueKit {
    /**
     * 构建签名
     * @param param 参数列表
     * @param isEncode 是否使用URLEncoder
     * @return
     */
    public static String buildSign(String param,boolean isEncode) throws UnsupportedEncodingException {
        if(isEncode) return URLEncoder.encode(param,"UTF-8");
        return param;
    }
}
