package club.mydlq.encrypt;

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
    public static void aesEncrypt(String data, String secret) {
        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), secret.getBytes()).getEncoded();
        // 创建 AES 对象
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        // 加密
        String encrypt = aes.encryptHex(data);
        // 解密
        String decrypt = aes.decryptStr(encrypt);
        System.out.println("AES 加密后的串：" + encrypt + "AES 解密的数据：" + decrypt);
    }

    /**
     * DES 加密、解密
     *
     * @param data 加密数据
     */
    public static void desEncrypt(String data, String secret) {
        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue(), secret.getBytes()).getEncoded();
        // 创建 DES 对象
        SymmetricCrypto des = new SymmetricCrypto(SymmetricAlgorithm.DES, key);
        // 加密
        String encrypt = des.encryptHex(data);
        // 解密
        String decrypt = des.decryptStr(encrypt);
        System.out.println("DES 加密后的串：" + encrypt + "DES 解密的数据：" + decrypt);
    }

    /**
     * DESede 加密、解密
     *
     * @param data 加密数据
     */
    public static void desedeEncrypt(String data, String secret) {
        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DESede.getValue(), secret.getBytes()).getEncoded();
        // 创建 DESede 对象
        SymmetricCrypto desede = new SymmetricCrypto(SymmetricAlgorithm.DESede, key);
        // 加密
        String encrypt = desede.encryptHex(data);
        // 解密
        String decrypt = desede.decryptStr(encrypt);
        System.out.println("DESede 加密后的串：" + encrypt + "DESede 解密的数据：" + decrypt);
    }

    /**
     * Blowfish 加密、解密
     *
     * @param data 加密数据
     */
    public static void blowfishEncrypt(String data, String secret) {
        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.Blowfish.getValue(), secret.getBytes()).getEncoded();
        // 创建 Blowfish 对象
        SymmetricCrypto blowfish = new SymmetricCrypto(SymmetricAlgorithm.Blowfish, key);
        // 加密
        String encrypt = blowfish.encryptHex(data);
        // 解密
        String decrypt = blowfish.decryptStr(encrypt);
        System.out.println("Blowfish 加密后的串：" + encrypt + "Blowfish 解密的数据：" + decrypt);
    }

    /**
     * RC2 加密、解密
     *
     * @param data 加密数据
     */
    public static void rc2Encrypt(String data, String secret) {
        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.RC2.getValue(), secret.getBytes()).getEncoded();
        // 创建 RC2 对象
        SymmetricCrypto rc2 = new SymmetricCrypto(SymmetricAlgorithm.RC2, key);
        // 加密
        String encrypt = rc2.encryptHex(data);
        // 解密
        String decrypt = rc2.decryptStr(encrypt);
        System.out.println("RC2 加密后的串：" + encrypt + "RC2 解密的数据：" + decrypt);
    }

    /**
     * ARCFOUR 加密、解密
     *
     * @param data 加密数据
     */
    public static void arcfourEncrypt(String data, String secret) {
        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.ARCFOUR.getValue(), secret.getBytes()).getEncoded();
        // 创建 ARCFOUR 对象
        SymmetricCrypto arcfour = new SymmetricCrypto(SymmetricAlgorithm.ARCFOUR, key);
        // 加密
        String encrypt = arcfour.encryptHex(data);
        // 解密
        String decrypt = arcfour.decryptStr(encrypt);
        System.out.println("ARCFOUR 加密后的串：" + encrypt + "ARCFOUR 解密的数据：" + decrypt);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        /* 设置要加密的内容 */
        String data = "测试数据";
        String secret1 = "abcdefghijklmnop";
        String secret2 = "abcdefghijklmnopqrstuvwx";
        /* AES加密 */
        aesEncrypt(data, secret1);
        /* DES加密 */
        desEncrypt(data, secret1);
        /* RC2加密 */
        rc2Encrypt(data, secret1);
        /* Blowfish加密 */
        blowfishEncrypt(data, secret1);
        /* ARCFOUR加密 */
        arcfourEncrypt(data, secret1);
        /* DESede加密 */
        desedeEncrypt(data, secret2);
    }

}
