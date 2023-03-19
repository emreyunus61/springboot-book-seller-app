package com.example.springbootbooksellerapp.security;

import com.example.springbootbooksellerapp.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class InternalApiAuthenticationFilter extends OncePerRequestFilter { //SÜPER KULLANICI KATMANI

    private  final  String accesKey;

    public  InternalApiAuthenticationFilter(String accesKey){
        this.accesKey=accesKey;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String requestKey = SecurityUtils.extractAuthTokenFromRequest(request);

            if (requestKey==null || !requestKey.equals(accesKey)){ //internalsecurity key ile aynı değilse hata ver

                log.warn("Internal key endpoint requested without/wrong key uri : {}", request.getRequestURI());
                throw  new RuntimeException("UNAUTHORIZED");

            }

            UserPrincipal user = UserPrincipal.createSuperUser();

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);


        }
        catch (Exception ex)
        {
            log.error("Could not set user authentication in security context",ex);
        }
        filterChain.doFilter(request,response);
    }
}
