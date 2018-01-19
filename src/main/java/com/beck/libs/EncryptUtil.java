package com.beck.libs;

import org.apache.commons.codec.binary.Hex;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
    /**
     * base64 编码
     *
     * @param inputStr
     * @return
     */
    public static String base64Encoder(String inputStr) {
        byte[] b = null;
        String encode;
        try {
            b = inputStr.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        encode = new BASE64Encoder().encode(b);
        encode = encode.replaceAll("\n", "").replaceAll("\r", "");
        return encode;
    }

    /**
     * base64 解码
     *
     * @param inputStr
     * @return String
     */
    public static String base64Decoder(String inputStr) {
        BASE64Decoder decoder = new BASE64Decoder();
        String decode = null;
        try {
            decode = new String(decoder.decodeBuffer(inputStr), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return decode;
    }

    public static String SHA256Encoder(String str) {
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }
}
