//package com.ecommerce.user_service.security;
//
//import io.jsonwebtoken.Claims;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//
//import com.ecommerce.common.security.JwtUtil;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.util.List;
//
//@Component
//public class RoleHeaderAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        
//        String authHeader = request.getHeader("Authorization");
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            try {
//                Claims claims = jwtUtil.validateToken(token);  // Validate JWT token
//
//                String role = claims.get("role", String.class);  // Extract role
//                String user = claims.getSubject();  // Extract user identifier
//
//                // Set Authentication in SecurityContext
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                        user, null, List.of(new SimpleGrantedAuthority("ROLE_" + role))  // Add role with "ROLE_" prefix
//                );
//
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } catch (Exception e) {
//                response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                response.getWriter().write("Invalid Token");
//                return;  // Stop the chain if token is invalid
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}