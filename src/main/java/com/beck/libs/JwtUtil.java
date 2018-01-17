package com.beck.libs;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    public static String createToken() {
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

    @Data
    public static class Header{
        /***
         * 类型
         */
        private String typ;

        /***
         * 加密算法
         */
        private String alg;

        private Header() {
        }

        public Header(String typ, String alg) {
            this.typ = typ;
            this.alg = alg;
        }
    }

    @Data
    private static class Payload {

        /***
         * Token签发者
         */
        private Integer iss;

        /***
         * Token创建时间
         */
        private long iat;

        /***
         * Token的过期时间
         */
        private long exp;

        /***
         * jwt所面向的用户
         */
        private Integer sub;

        /***
         * 接收用户的ip地址
         */
        private String ip;
    }




}
