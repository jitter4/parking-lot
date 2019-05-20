package com.example.parkinglot.utils;

import com.example.parkinglot.config.auth.SecProps;
import com.example.parkinglot.constants.enums.SessionTimeoutType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class JwtUtils {

    @Autowired
    private SecProps securityProperties;

    @Autowired
    private  PasswordEncoder passwordEncoder;


    private static String _UT_ID = "_UT#";
    private static String _UT_ID_Key = "UKID";
    private String jti_Secret_key = "Mwl#nztky@713";


    public String generateAccessToken(String authenticationUser, String role, String utid, String jti,
                                      Date expiration, String secret, String remoteAddr) {

        Map<String, Object> tokenHeaderClaim = new HashMap<>();
        tokenHeaderClaim.put(JwtUtils._UT_ID_Key, utid);
        tokenHeaderClaim.put("role", role);
        if (StringUtils.isNotBlank(remoteAddr)) {
            tokenHeaderClaim.put("remoteAddr", remoteAddr);
        }

        return Jwts.builder()
                .setHeaderParam("alg", SignatureAlgorithm.HS512)
                .setHeaderParam("typ", "JWT")
                .addClaims(tokenHeaderClaim)
                .setSubject(authenticationUser)
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .setId(jti)
                .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }

    public Claims getClaimsFromAccessToken(String token, String secret) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    public Date generateExpirationDate(Calendar currentTimeMillis) {

        Integer sessionTimeout    = this.securityProperties.getSessionTimeout();
        String sessionTimeoutType = this.securityProperties.getSessionTimeoutType();

        switch(SessionTimeoutType.valueOf(sessionTimeoutType)) {

            case YEAR:
                currentTimeMillis.add(Calendar.YEAR, sessionTimeout);
                break;
            case MONTH:
                currentTimeMillis.add(Calendar.MONTH, sessionTimeout);
                break;
            case WEEK:
                currentTimeMillis.add(Calendar.WEEK_OF_MONTH, sessionTimeout);
                break;
            case DAY:
                currentTimeMillis.add(Calendar.DAY_OF_MONTH, sessionTimeout);
                break;
            case HOUR:
                currentTimeMillis.add(Calendar.HOUR, sessionTimeout);
                break;
            case MINUTE:
                currentTimeMillis.add(Calendar.MINUTE, sessionTimeout);
                break;
            case SECOND:
                currentTimeMillis.add(Calendar.SECOND, sessionTimeout);
                break;
        }

        return currentTimeMillis.getTime();
    }

    public boolean validateExpirationdate(Claims claims) {
        Date expDate = claims.getExpiration();
        return expDate.after(new Date());
    }

    public int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt((this.securityProperties.getSecret().size()-1 - 0) + 1) + 0;
    }

    public String prepareUTID(int key) {
        return TextCodec.BASE64.encode(JwtUtils._UT_ID + key);
    }

    public String getUTID(String data) {
        String decodeData = TextCodec.BASE64.decodeToString(data);
        String utid[] = decodeData.split("#");
        return utid.length > 0 ? utid[1] : null;
    }

    public String generateJti() {
        return this.passwordEncoder.encode(jti_Secret_key);
    }

    public boolean validateJti(String jti) {
        return this.passwordEncoder.matches(jti, jti_Secret_key);
    }

}

