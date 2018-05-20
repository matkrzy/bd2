package com.photos.api.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.photos.api.security.SecurityConstants.JWT;
import static com.photos.api.security.SecurityConstants.getLoggedUserEmail;

/**
 * @author Micha Królewski on 2018-04-21.
 * @version x
 */


public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService) {
        super(authenticationManager);
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken userAuth = getAuthenticationToken(cookies);
        SecurityContextHolder.getContext().setAuthentication(userAuth);

        // generating new token
        // response.addHeader(HEADER_STRING, TOKEN_PREFIX + generateToken(userAuth));
        chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(Cookie[] cookies) {

        String email = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(JWT)) {
                email = getLoggedUserEmail(cookie);
                System.out.println(email.toUpperCase());
                break;
            }
        }

        if (email == "") return null;
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        return userDetails != null ? new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()) : null;
    }
}
