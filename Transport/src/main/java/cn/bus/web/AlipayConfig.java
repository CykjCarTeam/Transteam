package cn.bus.web;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101200671070";//沙箱appid账号

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCfqWboXYeWoD7RHz1Y+mDy8ljLiPNPCGyWOHPVY2nlFtieHr478k6ENHj8+vCZ9q4Wh75vY6D1BZ96LLWozwG492IUBAqAHB1aIhf7L54qHJeKpwwn/ZoZpcvqB5H1+oIxObjNfo3ODraXVwDEU9eARQlwPi2SMe6pujQZU51+fYcJzQze4p0TcneE0mB25TeGiZie+5Ns4RTShj+2frc9EX3m+ndRhYUQHipqhqiu5gr7xe/EQVPvHliEXFJMA3pvPPKaBVnAONES6gH4i19RdRhCqAoGgCiLPY1mYPkXywSU61FReXKuhg0jYayicbGCFXdT9bYN5vVyNRcM+zApAgMBAAECggEAMQYX5gNJ7bMYdqer85U1jYNiRmSvqLXCecGtt3jF/CSusKZp7QgymZ9vAuaeV/gJbjuR+qvRsUpftGjkAohFVCEJiXHQM5PuY02SpXryiqWq1lUCg4j39yYUXbCjXa7kqO67UsWoDvwvjnqVbrL7uZP5GPDOl1QjnxPgiWJGOp8eEsEGZ1ih6zPGkEfQI17m8Prm4ckFUs69l7XZyMuK+MHgxzN4956HhegGywXvcAKccptVawIG4ysPuUbwOeaG6XOk3ALhg4iJj6ir6PqpbFUFsy86Sm9WCr0H9/dzaVTU2rTJcjp1QVqpGU+olZQ/MMqEVKsZ15UWLEBljhARbQKBgQDYq9oObaHDOI+QJDHnwzt+CW5UXf1zWkJ2Xv+HDCEzLdFwy7McZA1jmcVc8gEVC20YJWG7qqujJoaJbiH65xB/cXglYZANLAZC29kxYg1RF9JNNL4YA5xUhP9UjMtY0sCWDlI2f7XHcN3A09SfnIhMjF9t0f45gs+AG1cHAxq4ewKBgQC8pHb0abV3z33VGHbXLxoKgJtjpHciH4TGpXQaZghVezwC91/udfEmqPJyeK2K6YFQTS1il/hClc7ySJxBHt47zxXmHxTjDzDZf/8f0syLMOp0Vxcgg3x7uMD+Jtni3vIN8rMOIrbQU8GVS7zPSAdh6zgnXQEpSI7HY/ArDcMCqwKBgEzyKsw47tuPeJxt3jM6SCRoYQAhKOMQSM4UypZBHoE4Ykq2bHNGrOKUN1zcrLm2hxWuINkusSmNgvXHYNXjvR4lQ8+yqB+lZMvVm3pIIgaMeSjl9CQ/FAOH/80c+xIeNjqT92pXTEz5ribOk5HjWxwkNbegfbIa3GnYS723woOFAoGAREjYUnLtYFg4eWmoow4GxCR3myMOiMiV/H4pv+CNRbwdJgm7rQr+Zvsi//LVBwKtb47wTc8q65dk2OkVvrDYyxeUGr/HhMWb7i9Z/4AD0KK9S622zmsqJXLkUVto5anEPu5KiGRifpjC6zMGPpYZLgXCRuVwpSwBmwV/C2ao348CgYAr2J1CBYTMge1PVELkE2QErBa+dV3Y8LbkG0QnVe9l5w7EPURkp7gaJyE7WOwQjeGxaJBUBe2nXdhNNALl+kWtZIaGJSC8djkWDWnCdhdautwEjzdi0iDVd0/w8/EQbRlFgoAZ89ndwzc6AsfCezDJK8VsMm9teiViiWdCT0YUQQ==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq1CmN+SGa2bASNySun92fSOTL2F83YUMQfylGGBRMPzwHflvGaa1mNHyGeRXY6elmiWtMNmdprzUgGLHrHkKe0CpVUOssspolcVH+4AHVW7GKMe/dlqdpQ5/kbASO7D4FIH+03/54QpaHf+lAiAtgBhSjtRdBvOjHnmF1gRNdR79NPIuOoBRN8RXQPq1xnumcPYKTQpj+CUJzgCpiD4NyCd6DObV1GDGIQRpguiV7e2NXnf5GcaswYE00w/j8aTyXz3fIsY5cbMkbiXjAa2k3RwkRJ/+wtM+Gjylfis2oRpIUmQKbtQpqu2u8Y/+hRebt9eF+mGh313m0bXJeBrSywIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/aid_war_exploded/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/aid_war_exploded/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


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

