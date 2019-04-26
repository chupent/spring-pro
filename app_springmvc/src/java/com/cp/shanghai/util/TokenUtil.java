package com.cp.shanghai.util;

import java.util.UUID;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName TokenUtil
 * @Description TODO
 * @createdate 2018/9/27 星期四 13:44
 */
public class TokenUtil {
    public static String createToken() {
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        return token.replace("-", "");
    }
}
