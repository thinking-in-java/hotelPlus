package work.chinadream.util;

import java.io.FileWriter;
import java.io.IOException;

/**             支付宝配置类
 * Author:      James Ben
 * Email:       thinking_in_java@126.com
 * ToDo:        Sail against the current
 * Create Time: 2020/7/22 17:06
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102100729169";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCUzym1748X3XnkzbDOgsiD/41YGoQ2WAPLzVKlpKkhg1Qrs6qZduZ7HzJy48PFeByaXRxqDH3ipXET0L607MmikOj7CICWuO5NjCCi6KIek2jwJsTb8137KsfFRn75Bs0e4O9wwzD1HUyJ3ZcTlyp/JLkKW1KnU1irhFEcatgc7FDmRNJ9AgIuJQfvMuM61gsaoBAhZ7K3mmqtHdnkeTvEa6wlDlj2gZNS4ifCCTSUKOpdC1s2StMXL+7RLD6NFSQuHrQFnSCdO5B7odLR4++5ktSPty7Hrv/lljwnufb9UuoqHbiBITqh4rlFTS0FmwbrR821jJDS3uSLnrIhNPj5AgMBAAECggEAaf/DS2iweiR2PohSSFgaswPnT/nbccZyNcs08u7+1vNwkoyxmg17Efrf+bzKaDAt17s5kUUCqt6EVMQeBEyjOJyWiLK+vh5PCYgme0TQroz8dAx5r9+Ec3Q+xwR3T7YLiNJbXOx9RCyxp4bjpb3JNLYY5Qq2LqCNCef/ywlElhyc/i9P1Pm5yoLvI4KJV+K+/Cz9295I5lVLOQS3djtvx7lqQd6wRqxTi+3DlzUDaPsKEtfN4vucezsy+mJlyodOAJ+jP8XvbCF7JSCWLJJiSP2Mtk7ThYgfkTE7W04GhjUB7R3mivLQ5RzCA8p1UHBkUEMuv5h8vOurj7t9U96lPQKBgQDNNn8dV1PH+82r/ZHgk+iPU6jvDjrwHuqTD/UH8XecUB46n7cMQ+Gu7bDuZJN9AcHpm7VwubfQQ2oXgfD6nHmGzO+uHf5KF51m3MVPkYhs5hgjXFhzMUkRXo1pYAZKlY+tEoVMTFJJq9cM376yJIYfuaajwnOHUi5nt3goO8FUzwKBgQC5oyWdFS1BVzoM2UFYqyZS4SaNiL8ihsw09RTbQWoIxQt6ne4+6NWFwSovfpFTGQsjW83G2T3/q2AdkfY0/c/0ZZmg9PHwNpHHVi491MdfYdyGVEJHPcgwmZkkkswudS23fWL1IcnhbQjuSt6sp/xp8L4Yp6lTLNyj9t+noKFXtwKBgQCCTQEnk0SKbfNpKsfOrnvzTe6tY/6+axMNMAuo8IS/Rwv6s2EaM5wAY8J9jHUt4cjL2We63OG/Jp8dsA9gQazUkBn8F+VYDGiQYMwUOtdCNlPLWB59fiTkF4J5D1TPVOn2vhTPnoJ9btRMw2ElYhWb3VjXEGgk22TFzHRKL68RdwKBgAQsH49NR4wdaseZJnfYuO7J8rThqvIat4JFM+fNq5Vw4FYsMIunbg0oj0sr/BYzDOYJjTF39oXFk0521mLQhZkHo5FTkBCHGXWygS2Ql76UE4HNaQPy5on5GdERn5c3uQRTxCKgVMpFShsZAbI9wFWdPJiPRCYDbx4cUWCSIq43AoGBALBwaK1im31ZTMv0H6esgGyVm98d39WPQMlWNTIyC2Yh81bKak47ceKEUDZSKgDt4wUrsZVR2WzsKY6OyiTw6RJ3tqPRAO0iSjmOLHQ8BOcMqV6I8c0Oc8n4SKt6wbkWdwLj2c2y02mvTSPUXWMpwkvl2gwiGQNVxUdHI8OwRM/5";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhTlIK4HHhz7mOvNF68+6pw+OtowVnL8q751aPXGTVK6wpY2kCbc3efwA6HAmDIEGjKe32UWNa9gMHzdOuMz3RL2v7vdBVH/+I9dTuV2J0JbifDIzANXIGYpftbDPd90KnYsFhVgc+XFxyCDdIRPrhcJRhQGby1VcZJm8FvLqFkNCiQy3rAic9Wi4AZGPXQ8Ul3/uZWbRFcV60tqIY9tOyYiayc4+rjl3KUTS+H4PUo6z2F1P/F3+D+p4OlKB1vi/ewiLbGGo1RHd8ySyj4CCbkdK3k/5NPKiBkbUM86cV6P+NA7HwlOQfNUM5D3gLPQvv8q0//TgktCnxu5fo/5RKQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://www.chinadream.work/orderPay/success";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://www.chinadream.work/home/account/acc_order";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

  // 支付宝网关
  public static String log_path = "G:\\pay\\log\\";

//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
