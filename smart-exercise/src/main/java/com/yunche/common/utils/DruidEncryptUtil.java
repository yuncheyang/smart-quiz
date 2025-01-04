package com.yunche.common.utils;

import com.alibaba.druid.filter.config.ConfigTools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/*
数据库加密
 */
public class DruidEncryptUtil {

    private static String publicKey;

    private static String privateKey;

    private static  String RootPublicKey;

    private static  String RootPrivateKey;

    static {
        try {
//            ConfigTools.genKeyPair 可以帮助我们生成一个加密对
            String[] keyPair = ConfigTools.genKeyPair(512);
            privateKey = keyPair[0];
            System.out.println("PassWordPrivateKey" + ":" + privateKey);
            publicKey = keyPair[1];
            System.out.println("PassWordPublicKey" + ":" + publicKey);

            String[] keyPair1 = ConfigTools.genKeyPair(512);
            RootPrivateKey = keyPair1[0];
            System.out.println("UserNamePrivateKey"+ ":" + RootPrivateKey);
            RootPublicKey = keyPair1[1];
            System.out.println("UserNamePublicKey" + ":" + RootPublicKey);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    public static String PassWordEncrypt(String plainText) throws Exception {
        String encrypt = ConfigTools.encrypt(privateKey, plainText);
        System.out.println("PassWordEncrypt" + ":" + encrypt);
        return encrypt;
    }
    public static String UserNameEncrypt(String plainText) throws Exception{
        String RootEncrypt = ConfigTools.encrypt(RootPrivateKey, plainText);
        System.out.println("UserNameEncrypt"+ ":" + RootEncrypt);
        return RootEncrypt;
    }

    public static String decrypt(String encryptText) throws Exception {
        String decrypt = ConfigTools.decrypt(publicKey, encryptText);
        String RootDecrypt = ConfigTools.decrypt(RootPrivateKey, encryptText);
        System.out.println("PassWordDecrypt" + ":" +  decrypt);
        System.out.println("UserNameDecrypt" + ":" +  RootDecrypt);
        return decrypt;
    }

    public static void main(String[] args) throws Exception {
        String encrypt = PassWordEncrypt("Wing1Q2W#E");
        String root = UserNameEncrypt("root");
    }
}
