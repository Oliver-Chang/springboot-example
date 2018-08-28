package com.oliver.ElectronicCommerce.authorization;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.oliver.ElectronicCommerce.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;


@Component
public class JwtTokenUtil implements Serializable {

    @Value("${jwt.expiration}")
    private Integer expiredMin;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    private final String CREATED_TIME = "createdTime";


    public String createToken(User user) {
        Algorithm ALGORITHM = null;
        try {
            ALGORITHM = Algorithm.HMAC512(secret);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Date now = new Date(System.currentTimeMillis());
        Date EXPIRES_AT = new Date(now.getTime() + expiredMin * 60 * 1000);
        String username = user.getUsername();
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(username)
                .withClaim(CREATED_TIME, now)
                .withExpiresAt(EXPIRES_AT)
                .sign(ALGORITHM);
    }

    public Boolean verifyToken(String token) {
        DecodedJWT decodedJWT = getDecodedJWT(token);
        if (decodedJWT == null) {
            return false;
        }
        return true;
    }

    public String getUsername(String token) {
        DecodedJWT decodedJWT = getDecodedJWT(token);
        if (decodedJWT == null) {
            return null;
        }
        String username = decodedJWT.getSubject();
        return username;
    }

    public String getIssuer(String token) {
        DecodedJWT decodedJWT = getDecodedJWT(token);
        if (decodedJWT == null) {
            return null;
        }
        String issuer = decodedJWT.getIssuer();
        return issuer;
    }

    public Date getCreatedTime(String token) {
        DecodedJWT decodedJWT = getDecodedJWT(token);
        if (decodedJWT == null) {
            return null;
        }
        Claim claim = decodedJWT.getClaim(CREATED_TIME);
        Date date = claim.asDate();
        return date;
    }

    public Date getExpireTime(String token) {
        DecodedJWT decodedJWT = getDecodedJWT(token);
        if (decodedJWT == null) {
            return null;
        }
        Date expiresAt = decodedJWT.getExpiresAt();
        return expiresAt;
    }


    private DecodedJWT getDecodedJWT(String token) {
        DecodedJWT decodedJWT = null;

        if (token == null) {
            return null;
        }
        Algorithm ALGORITHM = null;
        try {
            ALGORITHM = Algorithm.HMAC512(secret);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        JWTVerifier verifier = JWT.require(ALGORITHM)
                .build();
        try {
            decodedJWT = verifier.verify(token);
        } catch (TokenExpiredException e) {
            decodedJWT = null;
        } catch (JWTVerificationException e) {
            decodedJWT = null;
        } finally {
            return decodedJWT;
        }
    }
}
