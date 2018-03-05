package com.zriot.ebike.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESUtil {

    private static final String IV_STRING = "A-16-Byte-String";
    private static final String charset = "UTF-8";

    public static String aesEncryptString(String content, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        byte[] contentBytes = content.getBytes(charset);
        byte[] keyBytes = key.getBytes(charset);
        byte[] encryptedBytes = aesEncryptBytes(contentBytes, keyBytes);
        return Base64.encodeBase64String(encryptedBytes);
    }

    public static String aesDecryptString(String content, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        byte[] encryptedBytes = Base64.decodeBase64(content);
        byte[] keyBytes = key.getBytes(charset);
        byte[] decryptedBytes = aesDecryptBytes(encryptedBytes, keyBytes);
        return new String(decryptedBytes, charset);
    }

    public static byte[] aesEncryptBytes(byte[] contentBytes, byte[] keyBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return cipherOperation(contentBytes, keyBytes, Cipher.ENCRYPT_MODE);
    }

    public static byte[] aesDecryptBytes(byte[] contentBytes, byte[] keyBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return cipherOperation(contentBytes, keyBytes, Cipher.DECRYPT_MODE);
    }

    private static byte[] cipherOperation(byte[] contentBytes, byte[] keyBytes, int mode) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");

        byte[] initParam = IV_STRING.getBytes(charset);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(mode, secretKey, ivParameterSpec);

        return cipher.doFinal(contentBytes);
    }

/*    public static void main(String[] args) {
        String str="M1MH4nKwkS%2BNyLZT%2FOtOr9PUknPUpTqceLUi9DHymZUSMiLrP4bht1bhBszKJ5nDdLW8djoHGLfVzmB09EOQU%2Fz17wFo7xQNWIbVc%2BH%2FoVb%2Fw82t%2FE02uoBlD33cYy28AVwzuBgVeYj11QSsWLd2CkLvxN2b%2FQxv%2BkVDN6phHe77zU3HZ6X5yCAZlOSR6sqiMJqvrN9Hgp9I0FKQ0hAVJBrU3LkWlhWG%2F%2FN4IO19dJF1Io33hstScA9jUbCIDeBBJGLApp%2BSNQm4v0ODNIHA4g4WjEIxsnD%2F25jukgW9tcW6i3xdBBr%2BWWlqE8CyJQqBwCWXXjh6kZaXvMP%2Bu6ftwXmmjmrVS99FxLY8i5kDhfaoIRKoDGFlLTtLyfh6%2FR5D4B7FRc6IyRaWe14jTBbHmS47TVZyOvPFrNtYqzEZVS3d82MMSa5zLudZcDad2ftLSKKhZoF1K3V%2BzdO%2FcW1r7Wd2aBbdmrMEaETp13cYzBcBR350ejEehP6orwmCg%2FJKZiAwyPl%2Bus6rfYZb7QWAtEYy1wa%2BEm%2BuQ4iB45z8j77cVA3PfCXlcX2B%2FjzfMAsbOih%2Bop0i5693wNIKgsbci%2BcY78TavSGndNdkj7W%2B4iYa7Vsheq0Kxo%2F%2BrMswiYUENpsV7ABMY61%2BA6fOrewvBykA7lJGaC1b7OFjNj08h3spl2azLA94wwRM8GoN6Q81ZJdr6mkQnkBWbgXXj%2FM%2Fza0O5aFU3slbNwgzpvw8UdPFgVdSdMsaBlJN4o68N54lPHRH80g7QEIy3yeS9%2BJhXXCttsJVMubgHUnag%2FLyiX0%3D";
        //String str="M1MH4nKwkS%2BNyLZT%2FOtOr9PUknPUpTqceLUi9DHymZUSMiLrP4bht1bhBszKJ5nDdLW8djoHGLfVzmB09EOQU%2Fz17wFo7xQNWIbVc%2BH%2FoVb%2Fw82t%2FE02uoBlD33cYy28AVwzuBgVeYj11QSsWLd2CkLvxN2b%2FQxv%2BkVDN6phHe77zU3HZ6X5yCAZlOSR6sqiMJqvrN9Hgp9I0FKQ0hAVJBrU3LkWlhWG%2F%2FN4IO19dJF1Io33hstScA9jUbCIDeBBJGLApp%2BSNQm4v0ODNIHA4g4WjEIxsnD%2F25jukgW9tcW6i3xdBBr%2BWWlqE8CyJQqBwCWXXjh6kZaXvMP%2Bu6ftwXmmjmrVS99FxLY8i5kDhfaoIRKoDGFlLTtLyfh6%2FR5D4B7FRc6IyRaWe14jTBbHmS47TVZyOvPFrNtYqzEZVS3d82MMSa5zLudZcDad2ftLSKKhZoF1K3V%2BzdO%2FcW1r7Wd2aBbdmrMEaETp13cYzBcBR350ejEehP6orwmCg%2FJKZiAwyPl%2Bus6rfYZb7QWAtEYy1wa%2BEm%2BuQ4iB45z8j77cVA3PfCXlcX2B%2FjzfMAsbOih%2Bop0i5693wNIKgsbci%2BcY78TavSGndNdkj7W%2B4iYa7Vsheq0Kxo%2F%2BrMswiYUENpsV7ABMY61%2BA6fOrewvBykA7lJGaC1b7OFjNj08h3spl2azLA94wwRM8GoN6Q81ZJdr6mkQnkBWbgXXj%2FM%2Fza0O5aFU3slbNwgzpvw8UdPFgVdSdMsaBlJN4o68N54lPHRH80g7QEIy3yeS9%2BJhXXCttsJVMubgHUnag%2FLyiX0%3D";
        String content= null;
        try {
            content = URLDecoder.decode(str,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //String content="M1MH4nKwkS+NyLZT/OtOr9PUknPUpTqceLUi9DHymZUSMiLrP4bht1bhBszKJ5nDdLW8djoHGLfVzmB09EOQU/z17wFo7xQNWIbVc+H/oVb/w82t/E02uoBlD33cYy28AVwzuBgVeYj11QSsWLd2CkLvxN2b/Qxv+kVDN6phHe77zU3HZ6X5yCAZlOSR6sqiMJqvrN9Hgp9I0FKQ0hAVJBrU3LkWlhWG//N4IO19dJF1Io33hstScA9jUbCIDeBBJGLApp+SNQm4v0ODNIHA4g4WjEIxsnD/25jukgW9tcW6i3xdBBr+WWlqE8CyJQqBwCWXXjh6kZaXvMP+u6ftwXmmjmrVS99FxLY8i5kDhfaoIRKoDGFlLTtLyfh6/R5D4B7FRc6IyRaWe14jTBbHmS47TVZyOvPFrNtYqzEZVS3d82MMSa5zLudZcDad2ftLSKKhZoF1K3V+zdO/cW1r7Wd2aBbdmrMEaETp13cYzBcBR350ejEehP6orwmCg/JKZiAwyPl+us6rfYZb7QWAtEYy1wa+Em+uQ4iB45z8j77cVA3PfCXlcX2B/jzfMAsbOih+op0i5693wNIKgsbci+cY78TavSGndNdkj7W+4iYa7Vsheq0Kxo/+rMswiYUENpsV7ABMY61+A6fOrewvBykA7lJGaC1b7OFjNj08h3spl2azLA94wwRM8GoN6Q81ZJdr6mkQnkBWbgXXj/M/za0O5aFU3slbNwgzpvw8UdPFgVdSdMsaBlJN4o68N54lPHRH80g7QEIy3yeS9+JhXXCttsJVMubgHUnag/LyiX0=";
        String key="mMwWfT6MqC6XNJMs";
        try {
            String s=aesDecryptString(content,key);
            System.out.println(s);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }*/
}
