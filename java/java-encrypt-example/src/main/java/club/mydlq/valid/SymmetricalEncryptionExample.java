package club.mydlq.valid;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import java.io.UnsupportedEncodingException;

/**
 * 对称加密
 */
public class SymmetricalEncryptionExample {

    /**
     * AES 加密、解密
     *
     * @param data 加密数据
     */
    public static void aesEncrypt(String data) {
        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        // 创建 AES 对象
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        // 加密
        byte[] encrypt = aes.encrypt(data);
        // 解密
        byte[] decrypt = aes.decrypt(encrypt);
        System.out.println("AES 解密的数据：" + new String(decrypt));
    }

    /**
     * DES 加密、解密
     *
     * @param data 加密数据
     */
    public static void desEncrypt(String data) {
        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded();
        // 创建 DES 对象
        SymmetricCrypto des = new SymmetricCrypto(SymmetricAlgorithm.DES, key);
        // 加密
        byte[] encrypt = des.encrypt(data);
        // 解密
        byte[] decrypt = des.decrypt(encrypt);
        System.out.println("DES 解密的数据：" + new String(decrypt));
    }

    /**
     * DESede 加密、解密
     *
     * @param data 加密数据
     */
    public static void desedeEncrypt(String data) {
        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DESede.getValue()).getEncoded();
        // 创建 DESede 对象
        SymmetricCrypto desede = new SymmetricCrypto(SymmetricAlgorithm.DESede, key);
        // 加密
        byte[] encrypt = desede.encrypt(data);
        // 解密
        byte[] decrypt = desede.decrypt(encrypt);
        System.out.println("DESede 解密的数据：" + new String(decrypt));
    }

    /**
     * Blowfish 加密、解密
     *
     * @param data 加密数据
     */
    public static void blowfishEncrypt(String data) {
        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.Blowfish.getValue()).getEncoded();
        // 创建 Blowfish 对象
        SymmetricCrypto blowfish = new SymmetricCrypto(SymmetricAlgorithm.Blowfish, key);
        // 加密
        byte[] encrypt = blowfish.encrypt(data);
        // 解密
        byte[] decrypt = blowfish.decrypt(encrypt);
        System.out.println("Blowfish 解密的数据：" + new String(decrypt));
    }

    /**
     * RC2 加密、解密
     *
     * @param data 加密数据
     */
    public static void rc2Encrypt(String data) {
        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.RC2.getValue()).getEncoded();
        // 创建 RC2 对象
        SymmetricCrypto rc2 = new SymmetricCrypto(SymmetricAlgorithm.RC2, key);
        // 加密
        byte[] encrypt = rc2.encrypt(data);
        // 解密
        byte[] decrypt = rc2.decrypt(encrypt);
        System.out.println("RC2 解密的数据：" + new String(decrypt));
    }

    /**
     * ARCFOUR 加密、解密
     *
     * @param data 加密数据
     */
    public static void arcfourEncrypt(String data) {
        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.ARCFOUR.getValue()).getEncoded();
        // 创建 ARCFOUR 对象
        SymmetricCrypto arcfour = new SymmetricCrypto(SymmetricAlgorithm.ARCFOUR, key);
        // 加密
        byte[] encrypt = arcfour.encrypt(data);
        // 解密
        byte[] decrypt = arcfour.decrypt(encrypt);
        System.out.println("ARCFOUR 解密的数据：" + new String(decrypt));
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        /* 设置要加密的内容 */
        String data = "测试数据";
        /* AES加密 */
        aesEncrypt(data);
        /* DES加密 */
        desEncrypt(data);
        /* RC2加密 */
        rc2Encrypt(data);
        /* Blowfish加密 */
        blowfishEncrypt(data);
        /* DESede加密 */
        desedeEncrypt(data);
        /* ARCFOUR加密 */
        arcfourEncrypt(data);
    }

}
