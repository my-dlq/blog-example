package club.mydlq.encrypt;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 签名
 */
public class SignExample {

    /**
     * RSA 私钥签名
     *
     * @param data             签名数据
     * @param privateKeyString 私钥字符串
     */
    public static String rsaSign(String data, String privateKeyString) {
        // 将私钥字符串转换成私钥对象
        PrivateKey privateKey = SecureUtil.generatePrivateKey("RSA", Base64.decode(privateKeyString));
        // 设置签名对象以及加密算法
        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
        // 设置私钥，然后执行签名
        sign.setPrivateKey(privateKey);
        byte[] bytes = sign.sign(data.getBytes());
        // 将签名转换为 Base64 字符串，然后返回
        return Base64.encode(bytes);
    }

    /**
     * RSA 公钥验签
     *
     * @param data            签名数据
     * @param publicKeyString 公钥
     * @param signString      签名
     * @return 是否验证成功
     */
    public static boolean rsaSignVerify(String data, String publicKeyString, String signString) {
        // 将公钥字符串转换成公钥对象
        PublicKey publicKey = SecureUtil.generatePublicKey("RSA", Base64.decode(publicKeyString));
        // 设置签名对象以及加密算法
        Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
        // 将签名字符串转换成 byte 数组
        byte[] bytes = Base64.decode(signString);
        // 设置公钥，然后执行验签
        sign.setPublicKey(publicKey);
        return sign.verify(data.getBytes(), bytes);
    }

    public static void main(String[] args) {
        /* 生成钥匙对 */
        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        // 将公钥私钥转换成 Base64 字符串
        String privateKeyString = Base64.encode(privateKey.getEncoded());
        String publicKeyString = Base64.encode(publicKey.getEncoded());
        /* 设置要加密的内容 */
        String data = "测试数据";
        /* RSA 私钥签名,公钥验签 */
        String sign = rsaSign(data, privateKeyString);
        boolean verify = rsaSignVerify(data, publicKeyString, sign);
        System.out.println("签名验证结果：" + verify);
    }

}
