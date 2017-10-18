package com.beck.libs;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
  private static String byte2Hex(byte[] bytes){
    StringBuffer stringBuffer = new StringBuffer();
    String temp = null;
    for (int i=0;i<bytes.length;i++){
      temp = Integer.toHexString(bytes[i] & 0xFF);
      if (temp.length()==1){
        //1得到一位的进行补0操作
        stringBuffer.append("0");
      }
      stringBuffer.append(temp);
    }
    return stringBuffer.toString();
  }


}