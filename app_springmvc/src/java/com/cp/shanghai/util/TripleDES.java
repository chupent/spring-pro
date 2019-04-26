package com.cp.shanghai.util;

import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by windwant on 2016/12/13.
 */
@SuppressWarnings("restriction")
public class TripleDES {
    static {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
    }

    private static final String MCRYPT_TRIPLEDES = "DESede";
    private static final String TRANSFORMATION = "DESede/CBC/PKCS5Padding";

    public static byte[] decrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MCRYPT_TRIPLEDES);
        SecretKey sec = keyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        IvParameterSpec IvParameters = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, sec, IvParameters);
        return cipher.doFinal(data);
    }

    public static byte[] encrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey sec = keyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        IvParameterSpec IvParameters = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, sec, IvParameters);
        return cipher.doFinal(data);
    }

    public static byte[] generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance(MCRYPT_TRIPLEDES);
        return keygen.generateKey().getEncoded();
    }

    public static byte[] randomIVBytes() {
        Random ran = new Random();
        byte[] bytes = new byte[8];
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = (byte) ran.nextInt(Byte.MAX_VALUE + 1);
        }
        return bytes;
    }

    /**
     * 加密
     *
     * @param plainText
     * @return
     */
    public static String encodeBase64String(String plainText) {
        final byte[] secretBytes = Base64.decodeBase64("iuRM6UnVHv6CfZ/lNCDTdo1nNELIKA4k");
        final byte[] ivbytes = Base64.decodeBase64("s7oSTJHXK/U=");
        try {
            return Base64.encodeBase64String(TripleDES.encrypt(plainText.getBytes(), secretBytes, ivbytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String args[]) throws Exception {
        String plainText = "Diebold12345";
        final byte[] secretBytes = Base64.decodeBase64("iuRM6UnVHv6CfZ/lNCDTdo1nNELIKA4k");
        final byte[] ivbytes = Base64.decodeBase64("s7oSTJHXK/U=");
        System.out.println("plain text: " + plainText);
        byte[] encrypt = TripleDES.encrypt(plainText.getBytes(), secretBytes, ivbytes);
        System.out.println("cipher text: " + Base64.encodeBase64String(encrypt));
        System.out.println("decrypt text: " + new String(TripleDES.decrypt(encrypt, secretBytes, ivbytes), "UTF-8"));
    }
}