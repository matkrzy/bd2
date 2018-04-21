package com.photos.api.configs;

import com.photos.api.services.CustomUserDetailsService;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.photos.api.configs.SecurityConstants.*;

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

        String header = request.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken userAuth = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(userAuth);
        chain.doFilter(request,response);

    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {

        String token = request.getHeader(HEADER_STRING);
        if (token == null) return null;
        String email = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody().getSubject();

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        return email != null ? new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()) : null;
    }
}
