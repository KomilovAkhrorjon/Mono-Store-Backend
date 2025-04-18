package com.qiroldev.monomarket.jwt;

import com.qiroldev.monomarket.accounting.user.enums.UserRoles;
import com.qiroldev.monomarket.accounting.user.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {

  @Value("${jwt.secret}")
  String rawKey;

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {

    final Claims claims = extractAllClaims(token);

    return claimResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSecretKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Key getSecretKey() {
    byte[] keyBytes = Decoders.BASE64.decode(rawKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }


  public String generateToken(UserEntity userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  public String generateToken(
      Map<String, Object> extraClaims,
      UserEntity userDetails
  ) {
    return Jwts.builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .claim("authorities",
            UserRoles.valueOf(userDetails.getRole().toString()).getGrantedAuthorities())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 60))
        .signWith(getSecretKey(), SignatureAlgorithm.HS512)
        .compact();
  }


  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);

    return Objects.equals(username, userDetails.getUsername()) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {

    return extractClaim(token, Claims::getExpiration);
  }
}
