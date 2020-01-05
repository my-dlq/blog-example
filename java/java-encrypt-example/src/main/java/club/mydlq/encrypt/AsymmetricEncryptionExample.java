package club.mydlq.encrypt;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 非对称加密
 */
public class AsymmetricEncryptionExample {

    /**
     * RSA 公钥加密,私钥解密
     *
     * @param data             加密数据
     * @param privateKeyString 私钥字符串
     * @param publicKeyString  公钥字符串
     * @throws UnsupportedEncodingException
     */
    public static void rsaEncrypt1(String data, String privateKeyString, String publicKeyString) throws UnsupportedEncodingException {
        // 将公、私钥字符串转换成公、私钥对象
        PrivateKey privateKey = SecureUtil.generatePrivateKey("RSA", Base64.decode(privateKeyString));
        PublicKey publicKey = SecureUtil.generatePublicKey("RSA", Base64.decode(publicKeyString));
        // 获取字符串byte数组
        byte[] bytes = data.getBytes("UTF-8");
        // 创建 RSA 对象
        RSA rsa = new RSA();
        // 设置公钥,然后执行公钥加密
        rsa.setPublicKey(publicKey);
        byte[] encrypt = rsa.encrypt(bytes, KeyType.PublicKey);
        // 设置私钥，然后执行私钥解密
        rsa.setPrivateKey(privateKey);
        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);
        // 输出解密的内容
        System.out.println("公钥加密,私钥解密，解密内容：" + new String(decrypt));
    }

    /**
     * RSA 私钥加密,公钥解密
     *
     * @param data             加密数据
     * @param privateKeyString 私钥字符串
     * @param publicKeyString  公钥字符串
     * @throws UnsupportedEncodingException
     */
    public static void rsaEncrypt2(String data, String privateKeyString, String publicKeyString) throws UnsupportedEncodingException {
        // 将公、私钥字符串转换成公、私钥对象
        PrivateKey privateKey = SecureUtil.generatePrivateKey("RSA", Base64.decode(privateKeyString));
        PublicKey publicKey = SecureUtil.generatePublicKey("RSA", Base64.decode(publicKeyString));
        // 获取字符串byte数组
        byte[] bytes = data.getBytes("UTF-8");
        // 创建 RSA 对象
        RSA rsa = new RSA();
        // 设置私钥，然后执行私钥加密
        rsa.setPrivateKey(privateKey);
        byte[] encrypt = rsa.encrypt(bytes, KeyType.PrivateKey);
        // 设置公钥，然后执行公钥解密
        rsa.setPublicKey(publicKey);
        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PublicKey);
        // 输出解密的内容
        System.out.println("私钥加密,公钥解密，解密内容：" + new String(decrypt));
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        /* 生成钥匙对 */
        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        // 将公钥私钥转换成 Base64 字符串
        String privateKeyString = Base64.encode(privateKey.getEncoded());
        String publicKeyString = Base64.encode(publicKey.getEncoded());
        /* 设置要加密的内容 */
        String data = "测试数据";
        /* RSA 公钥加密,私钥解密 */
        rsaEncrypt1(data, privateKeyString, publicKeyString);
        /* RSA 私钥加密,公钥解密 */
        rsaEncrypt2(data, privateKeyString, publicKeyString);
    }

}
