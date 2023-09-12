package cn.liuzhengan.kjdl.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtUtils {

    private static final String ISSUER = "kjdl";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(ISSUER);
    private static final Integer EXPIRES_IN = 666;

    public static String generateToken(Integer userId) {
        long currentTime = System.currentTimeMillis();
        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(new Date(currentTime))// 签发时间
                .withExpiresAt(new Date(currentTime + EXPIRES_IN * 1000 * 60))// 过期时间
                .withClaim("userId", userId)//自定义参数
                .sign(ALGORITHM);
    }

    public static Integer verifyToken(String token) throws RuntimeException {
        JWTVerifier verifier = JWT.require(ALGORITHM).withIssuer(ISSUER).build();
        DecodedJWT decodedJWT;
        try {
            decodedJWT = verifier.verify(token);
        } catch (TokenExpiredException e) {
            throw new RuntimeException("token过期！");
        }
        return decodedJWT.getClaim("userId").asInt();
    }

}