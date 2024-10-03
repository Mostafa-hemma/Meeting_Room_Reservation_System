package com.backend.fullProject.jwtconfig;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.backend.fullProject.service.BlackListedService;
import com.backend.fullProject.service.UserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService customUserDetailsService;
    @Autowired
	private BlackListedService blackListedService;

	
    
//    @Autowired
//    public JwtRequestFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService,BlackListedService blackListedService) {
//        this.jwtUtil = jwtUtil;
//        this.customUserDetailsService = userDetailsService;
//        this.blackListedService=blackListedService;
//    }

    
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        	jwt = authorizationHeader.substring(7);

            try {
                username = jwtUtil.extractUsername(jwt);
                if (blackListedService.isTokenBlacklisted(jwt)) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\":\"This token is invalid. It is blacklisted.\"}");
                    return;
                }
            }

            catch (Exception e) {
            	e.printStackTrace();
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"JWT token has expired.\"}");
                return;
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            
        }
        chain.doFilter(request, response);
    }
}