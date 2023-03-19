package com.example.springbootbooksellerapp.security;

import com.example.springbootbooksellerapp.security.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration //Bean oluşturmak için bu anatasyona gerek vardır
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${authentication.internal-api-key}")
    private  String internalApiKey;

    @Autowired
    private CustomUserDetailsService userDetailsService;


    @Override
    //kimlik doğrulmasından sorumludur (AuthenticationManagerBuilder)
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }


    //authentication manager bean oluşturur
    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // farklı domainlerden erişilebilirli ayarlayan method
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors(); //CORS, HTTP header'larını kullanarak, bir origin'de (kök, köken) çalışan web uygulamasının veya websitesinin, farklı bir origin'de yer alan web uygulamasına veya websitesine olan erişim izni kontrolünü sağlayan yapıdır.
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // istek izinlerinin ayarlanması

        http.authorizeRequests()
                .antMatchers("/api/authentication/**").permitAll()
                .anyRequest().authenticated();


        //jwt filter
        http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    public  InternalApiAuthenticationFilter internalApiAuthenticationFilter(){
        return new InternalApiAuthenticationFilter(internalApiKey);
    }



    @Bean
    //JwtAuthorizationFilter NESNESİ OLUŞTURUP BEAN ANATASYONU İLE GEREKLİ SINIFTA SPRİNG ANATASIYONLARINI KULLANABİLDİK
   public JwtAuthorizationFilter jwtAuthorizationFilter(){
        return  new JwtAuthorizationFilter();
   }


    @Bean
    public PasswordEncoder passwordEncoder(){

        return  new BCryptPasswordEncoder();
    }


    //Cors tarafından bir isteğe izin verillmiyorsa engelenecektir


    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer()
        {
            @Override
            public void addCorsMappings(CorsRegistry registry)
            {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*");
            }
        };
    }

}