package com.ra.security.jwt;

import com.ra.security.UserPriciple;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    @Value("${expired}")
    private Long EXPIRED_TIME;

    @Value("${secret_key}")
    private String SECRET_KEY;

    private Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    // tao token
    public String generateToken(UserPriciple userPriciple) {
        Date dateExpired = new Date(new Date().getTime() + EXPIRED_TIME);
        return Jwts.builder().setSubject(userPriciple.getUsername()).signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .setExpiration(dateExpired).compact();
    }

    // xac thuc token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }

    //lay thong tin user
    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}
