package kit;

import java.io.IOException;
import java.util.Properties;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName ConfigKit
 * @Description TODO 获取配置工具包
 * @createdate 2019/4/5 星期五 13:08
 */
public class ConfKit {
    private static Properties properties = new Properties();
    //获取资源目录下wechat.properties
    static {
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("wechat.properties"));
        } catch (IOException e) {
            try {
                properties.load(ConfKit.class.getResourceAsStream("wechat.properties"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    /**
     * 根据key获取属性文件中的值
     * @param key
     * @return
     */
    public static String getValue(String key){
        return properties.getProperty(key);
    }
    public static void setProps(Properties properties){
        properties = properties;
    }
}
