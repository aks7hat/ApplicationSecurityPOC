package com.ApplicationSecurity.ApplicationSecurity.Utils;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

/**
 * JWT Util for generating the JWT token.
 * @author akshataggarwal
 *
 */
@Component
public class JwtUtil {
	
    private final String SECRET_KEY = "0886a75db45db2eefcfe9f5bcc8386a28723beb4656c90ecaa0e26acdd36fba371fd9e4c1a9bb64914d6c10ce5e180670ceca96d165fbc503fae7df4ba5681e77b29ec2e035c61dbac88dcbe9ceefed3d277866f332baa0787051e689c7c3e31edad76f71ac33888362a13256fde2b7fc0f2a352359803221326c4efb4fa73b65aceee42331ccb3fd33df22cdabc768d48514fdbab44c7b7def164e70f7e5b41559bb5295ed8f66b249c247a6ac1bce0f2be862de87565e2bf0a4a2c3244567e06dd86b7ce14f822397b5620ac65b4d12517cbc7781f1aea950ddeee8749735b804617c667e7066e67a9edc75c7efd24e6fd3a4da32013cb2ae6c3c08f5b6b14";
    private final long EXPIRATION_TIME = 86400000; // 1 day
	
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractEmail(String token) {
        return (Jwts.parser()
                .setSigningKey(SECRET_KEY))
        		.build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
