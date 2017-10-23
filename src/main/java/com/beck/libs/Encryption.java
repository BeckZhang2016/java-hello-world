package com.beck.libs;

import com.alibaba.fastjson.JSON;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Encryption {


  public static String getKeySha(String inputStr) {
    String encodeStr = "";
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
      messageDigest.update(inputStr.getBytes("UTF-8"));
      encodeStr = byte2Hex(messageDigest.digest());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return encodeStr;
  }

  public static String base64Encoder(String inputStr) {
    BASE64Encoder encoder = new BASE64Encoder();
    String encode = encoder.encode(inputStr.getBytes());
    return encode;
  }

  public static String base64Decoder(String inputStr) {
    BASE64Decoder decoder = new BASE64Decoder();
    String decode = null;
    try {
      decode = new String(decoder.decodeBuffer(inputStr));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return decode;
  }

  public static String jwtEncryption() {
    Map header = new HashMap();
    header.put("typ", "JWT");
    header.put("alg", "SHA-256");
    String jwtHeader = base64Encoder(JSON.toJSONString(header));

    Map body = new HashMap();
    body.put("iss", "beck");
    body.put("sub", "user");
    body.put("aud", "user");
    body.put("iat", new Date().getTime());
    String jwtBody = base64Encoder(JSON.toJSONString(body));

    String jwtSignature = getKeySha(jwtHeader + '.' + jwtBody);
    String jwtToken = jwtHeader + '.' + jwtBody + '.' + jwtSignature;

    return jwtToken;
  }

  private static String byte2Hex(byte[] bytes) {
    StringBuffer stringBuffer = new StringBuffer();
    String temp = null;
    for (int i = 0; i < bytes.length; i++) {
      temp = Integer.toHexString(bytes[i] & 0xFF);
      if (temp.length() == 1) {
        //1得到一位的进行补0操作
        stringBuffer.append("0");
      }
      stringBuffer.append(temp);
    }
    return stringBuffer.toString();
  }


}
