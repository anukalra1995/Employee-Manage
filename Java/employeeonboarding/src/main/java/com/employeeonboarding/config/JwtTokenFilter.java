package com.employeeonboarding.config;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

public class JwtTokenFilter extends OncePerRequestFilter {

	private JwtTokenProvider tokenProvider;

	public JwtTokenFilter(JwtTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Logger log = LoggerFactory.getLogger(JwtTokenFilter.class);
		String token = request.getHeader("Authorization");
		if (token != null) {
			try {
				Claims claims = tokenProvider.getClaimsFromToken(token);
				if (!claims.getExpiration().before(new Date())) {
					Authentication authentication = tokenProvider.getAuthentication(claims.getSubject());
					if (authentication.isAuthenticated()) {
						SecurityContextHolder.getContext().setAuthentication(authentication);
					}
				}
			} catch (RuntimeException e) {
				try {
					SecurityContextHolder.clearContext();
					response.setContentType("application/json");
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.getWriter().println("exception expired or invalid JWT token " + e.getMessage());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				return;
			}
		} else {
			log.info("first time so creating token using UserResourceImpl - authenticate method");
		}
		filterChain.doFilter(request, response);
		
	}

}
