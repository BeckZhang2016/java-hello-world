package com.beck.libs;

import com.alibaba.fastjson.JSON;
import com.beck.enums.ExceptionEnum;
import lombok.Data;

import java.util.Date;

public class JwtUtil {

    public static String createToken(String ip) {
        Header header = new Header("JWT", "SHA-256");
        String jwtHeader = EncryptUtil.base64Encoder(JSON.toJSONString(header));
        Payload payload = new Payload();
        payload.setIss("beck");
        payload.setIat(new Date().getTime());
        payload.setIp(ip);
        String jwtBody = EncryptUtil.base64Encoder(JSON.toJSONString(payload));

        String jwtSignature = EncryptUtil.SHA256Encoder(jwtHeader + '.' + jwtBody);
        String jwtToken = jwtHeader + '.' + jwtBody + '.' + jwtSignature;

        return jwtToken;
    }

    public static ExceptionEnum verifyToken(String token, String ip) {
        String[] tokens = token.split(".");
        //todo wait continue...
        if (tokens.length != 3) {
            return ExceptionEnum.TOKEN_ERROR;
        }
        String header = tokens[0], payload = tokens[1], signature = tokens[3];

        return ExceptionEnum.TOKEN_ERROR;
    }


    @Data
    public static class Header {
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
        private String iss;

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
