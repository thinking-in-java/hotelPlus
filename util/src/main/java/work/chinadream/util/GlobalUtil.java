package work.chinadream.util;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

/**            公用的配置文件处理类
 * Author:      Mr.Yi Quan
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/20 16:57
 */
public class GlobalUtil {
    private static Logger LOGGER = Logger.getLogger(work.chinadream.util.GlobalUtil.class);

    /**
     * 文件配置路径
     */
    public static final String PATHCONFIG = "global.properties";

    /**
     * 获得配置的值
     * 
     * @param key 标示
     * @return 值
     */
    public static String getValue(String key) {
        try {
            Properties properties = new Properties();
            InputStream in = work.chinadream.util.GlobalUtil.class.getResourceAsStream(PATHCONFIG);
            properties.load(in);
            in.close();
            return properties.getProperty(key);
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
        return null;
    }
}
