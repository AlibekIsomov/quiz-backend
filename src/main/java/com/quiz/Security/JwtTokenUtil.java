package com.quiz.Security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil implements Serializable {

    private String secret;
    private int jwtExpirationInMs;
    private int jwtExpirationInMsRememberMe;

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Value("${jwt.jwtExpirationInMs}")
    public void setJwtExpirationInMs(String jwtExpirationInMs) {
        this.jwtExpirationInMs = Integer.parseInt(jwtExpirationInMs);
    }

    @Value("${jwt.jwtExpirationInMsRememberMe}")
    public void setJwtExpirationInMsRememberMe(String jwtExpirationInMsRememberME) {
        this.jwtExpirationInMsRememberMe = Integer.parseInt(jwtExpirationInMsRememberME);
    }

    // generate token for user
    public String generateToken(UserDetails userDetails, boolean rememberMe) {
        Map<String, Object> claims = new HashMap<>();
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();


// TODO ROLE bilan boshlanishni hal qilish zarur bu vaqtinchalik
        claims.put("roles", roles.stream().map(e -> {
            String r = e.getAuthority().toString();
            return r.substring(r.indexOf("_")+1);
        }).toArray());


        return doGenerateToken(claims, userDetails.getUsername(), rememberMe);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, boolean rememberMe) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (rememberMe ? jwtExpirationInMsRememberMe : jwtExpirationInMs))).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public boolean validateToken(String authToken) {
        try {

            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);

            return true;
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            System.out.println("Noto'g'ri");
            throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
        } catch (ExpiredJwtException ex) {
//            throw new ExpiredJwtException(header, claims, "Token has Expired", ex);
            System.out.println("Muddat eskirgan");
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    public List<SimpleGrantedAuthority> getRolesFromToken(String authToken) {

        List<SimpleGrantedAuthority> roles = null;

        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken).getBody();

        roles = ((List<String>) claims.get("roles", List.class))
                .stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return roles;
    }
}
