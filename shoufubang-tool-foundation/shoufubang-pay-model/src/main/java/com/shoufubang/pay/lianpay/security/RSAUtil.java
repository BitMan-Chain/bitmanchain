package com.shoufubang.pay.lianpay.security;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import com.shoufubang.pay.lianpay.util.Base64;


/**
 * RSA签名公共类
 * @author kristain
 */
public class RSAUtil{

    private static RSAUtil instance;

    private RSAUtil()
    {

    }

    public static RSAUtil getInstance()
    {
        if (null == instance)
            return new RSAUtil();
        return instance;
    }

    /**
     * 签名处理
     * @param prikeyvalue：私钥文件
     * @param sign_str：签名源内容
     * @return
     */
    public static String sign(String prikeyvalue, String sign_str)
    {
        try
        {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                    Base64.getBytesBASE64(prikeyvalue));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey myprikey = keyf.generatePrivate(priPKCS8);
            // 用私钥对信息生成数字签名
            java.security.Signature signet = java.security.Signature
                    .getInstance("MD5withRSA");
            signet.initSign(myprikey);
            signet.update(sign_str.getBytes("UTF-8"));
            byte[] signed = signet.sign(); // 对信息的数字签名
            return new String(
                    org.apache.commons.codec.binary.Base64.encodeBase64(signed));
        } catch (java.lang.Exception e)
        {
        }
        return null;
    }

    /**
     * 签名验证
     * @param pubkeyvalue：公钥
     * @param oid_str：源串
     * @param signed_str：签名结果串
     * @return
     */
    public static boolean checksign(String pubkeyvalue, String oid_str,
            String signed_str)
    {
        try
        {
            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(
                    Base64.getBytesBASE64(pubkeyvalue));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
            byte[] signed = Base64.getBytesBASE64(signed_str);// 这是SignatureData输出的数字签名
            java.security.Signature signetcheck = java.security.Signature
                    .getInstance("MD5withRSA");
            signetcheck.initVerify(pubKey);
            signetcheck.update(oid_str.getBytes("UTF-8"));
            return signetcheck.verify(signed);
        } catch (java.lang.Exception e)
        {
        }
        return false;
    }
}
