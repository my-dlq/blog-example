package club.mydlq.encrypt;

import cn.hutool.crypto.digest.DigestUtil;

/**
 * 摘要加密
 */
public class DigesterEncryption {

    /**
     * MD2 加密
     * @param data 加密内容
     */
    public static void md2Encrypt(String data) {
        String md2String = DigestUtil.md5Hex(data);
        System.out.println("MD2加密后的字符串：" + md2String);
    }

    /**
     * MD5 加密
     * @param data 加密内容
     */
    public static void md5Encrypt(String data) {
        String md5String = DigestUtil.md5Hex(data);
        System.out.println("MD5加密后的字符串：" + md5String);
    }

    /**
     * SHA-1 加密
     * @param data 加密内容
     */
    public static void sha1Encrypt(String data) {
        String sha1String = DigestUtil.sha1Hex(data);
        System.out.println("SHA1加密后的字符串：" + sha1String);
    }

    /**
     * SHA-256 加密
     * @param data 加密内容
     */
    public static void sha256Encrypt(String data) {
        String sha256String = DigestUtil.sha256Hex(data);
        System.out.println("SHA256加密后的字符串：" + sha256String);
    }

    public static void main(String[] args) {
        /* 设置要加密的内容 */
        String data = "测试数据";
        /* MD2加密 */
        md2Encrypt(data);
        /* MD5加密 */
        md5Encrypt(data);
        /* SHA1加密 */
        sha1Encrypt(data);
        /* SHA256加密 */
        sha256Encrypt(data);
    }

}
