package bssm.doorlock.global.security;

import bssm.doorlock.global.auth.AuthDetailsService;
import bssm.doorlock.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final AuthDetailsService authDetailsService;

    @Value("${token.access-token.name}")
    private String ACCESS_TOKEN_NAME;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        authentication(req);
        filterChain.doFilter(req, res);
    }

    private void authentication(HttpServletRequest req) {
        String token = req.getHeader(ACCESS_TOKEN_NAME);
        UserDetails userDetails = authDetailsService.loadUserByUsername(String.valueOf(jwtProvider.getUserCode(token)));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}

