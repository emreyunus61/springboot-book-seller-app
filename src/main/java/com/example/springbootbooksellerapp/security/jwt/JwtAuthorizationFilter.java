package com.example.springbootbooksellerapp.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends OncePerRequestFilter
{
    @Autowired //bu filtrernin bir spring nesnesi olmadığı için @Autowired  kullanıamıyoruz, spring security sınıfından bean yaratılarak yani bir spring nesnesi olduğu belirtmemiz gerek
    private IJwtProvider jwtProvider;


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getRequestURI().startsWith("api/internal");
    }

    @Override   //Kimlik doğrulama
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        Authentication authentication = jwtProvider.getAuthentication(request);

        if (authentication != null && jwtProvider.validateToken(request))
        {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}