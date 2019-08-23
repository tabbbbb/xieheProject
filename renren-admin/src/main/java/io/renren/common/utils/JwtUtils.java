package io.renren.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtUtils {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "jwtdemo";
    private static final String ISS = "echisan";

    // 过期时间
    private static final long EXPIRATION = 360000l;



    /**
     * 加密 jwt token
     * @param id
     * @return
     */
    public static String encode(String id) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        String token = JWT.create()
                //设置过期时间为一个小时
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION))
                //设置负载
                .withJWTId(id)
                .sign(algorithm);
        return token;
    }

    /**
     *  解密 jwt toke
     * @param token
     * @return
     */
    public static String decode(String token) {
        if (token == null || token.length() == 0) {
            throw new RuntimeException("token为空:" + token);
        }
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getId();
    }

}
