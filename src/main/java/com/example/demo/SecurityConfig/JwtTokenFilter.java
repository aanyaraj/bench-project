package com.example.demo.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.Service.UserLoginService;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JwtTokenFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Override
	  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	      throws ServletException, IOException, java.io.IOException {
		
	    try {
	    	
	      String jwt = parseJwt(request);
	      
	      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
	    	  
	        String userEmail = jwtUtils.getUserNameFromJwtToken(jwt);
	        UserDetails userDetails = userLoginService.loadUserByUsername(userEmail);
	        
	        UsernamePasswordAuthenticationToken authentication =
	            new UsernamePasswordAuthenticationToken(
	                userDetails,
	                null,
	                userDetails.getAuthorities());
	        
	        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        
	      }
	    } catch (Exception e) {
	      logger.error("Cannot set user authentication: {}", e);
	    }

	    filterChain.doFilter(request, response);
	    
	  }

	  private String parseJwt(HttpServletRequest request) {
		  
	    String headerAuth = request.getHeader("Authorization");

	    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
	    	
	    	return headerAuth.substring(7);
	    	
	    }

	    return null;
	    
	  }

}
