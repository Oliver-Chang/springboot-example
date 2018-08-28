package com.oliver.ElectronicCommerce.authorization;


import com.oliver.ElectronicCommerce.user.User;
import com.oliver.ElectronicCommerce.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain chain) throws IOException, ServletException {
        String header = httpServletRequest.getHeader(tokenHeader);
        if (header == null || !header.startsWith(tokenHead)) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        final String authToken = header.substring(tokenHead.length()).replace(" ", "");
        if (authToken == null) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        System.out.println("authtoken");
        System.out.println(authToken);
        if (!jwtTokenUtil.verifyToken(authToken)) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String username = jwtTokenUtil.getUsername(authToken);
        if (username == null) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        String issuer = jwtTokenUtil.getIssuer(authToken);
        if (issuer == null) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        Date createdTime = jwtTokenUtil.getCreatedTime(authToken);
        if (createdTime == null) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        System.out.println("username");
        System.out.println(username);
        System.out.println("issuer");
        System.out.println(issuer);
        System.out.println("create time");
        System.out.println(createdTime);

        User userDetails;
        try {
            userDetails = userService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        if (userDetails == null) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        if (userDetails.getLastRestPassword().getTime() > createdTime.getTime()) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getId(), userDetails.getPassword(), userDetails.getAuthorities());
        authenticationToken.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(httpServletRequest, httpServletResponse);
    }

}