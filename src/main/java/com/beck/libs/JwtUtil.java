package com.beck.libs;

import com.alibaba.fastjson.JSON;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    public static String jwtEncryption() {
        Map header = new HashMap();
        header.put("typ", "JWT");
        header.put("alg", "SHA-256");
        String jwtHeader = EncryptUtil.base64Encoder(JSON.toJSONString(header));

        Map body = new HashMap();
        body.put("iss", "beck");
        body.put("iat", new Date().getTime());
        String jwtBody = EncryptUtil.base64Encoder(JSON.toJSONString(body));

        String jwtSignature = EncryptUtil.SHA256Encoder(jwtHeader + '.' + jwtBody);
        String jwtToken = jwtHeader + '.' + jwtBody + '.' + jwtSignature;

        return jwtToken;
    }




}
