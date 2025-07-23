package com.example.demo.SecurityConfig;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.Model.FruitCategory;
import com.example.demo.Model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtils {
	
	@Value("${user.jwt.secret}")
	private String jwtSecret;

	@Value("${user.jwt.expiry}")
	private int jwtExpirationMs;
	
	
	//private User user;
	 
	private Key secretKey=Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public String generateJwtToken(User user) {
	    Map<String, Object> claims = new HashMap<>();
	    claims.put("userId", user.getUserId());  // Ensure you are setting userId correctly
	    claims.put("roles", user.getRoles());

	    return Jwts.builder()
	            .setClaims(claims)
	            .setSubject(user.getEmail())

				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(secretKey).compact();
    }
	

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(secretKey).build().parse(authToken);
			return true;
		} catch (MalformedJwtException e) {
			System.out.println(e.getMessage());
		} catch (ExpiredJwtException e) {
			System.out.println(e.getMessage());
		} catch (UnsupportedJwtException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		return false;
	}
	public String getRoleFromJwtToken(String token) {
        Claims claims = (Claims) Jwts.parserBuilder().setSigningKey(secretKey).build().parse(token).getBody();
        return claims.get("roles", String.class);
    }

}

