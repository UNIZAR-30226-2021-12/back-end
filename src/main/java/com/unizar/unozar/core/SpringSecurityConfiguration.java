package com.unizar.unozar.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{
  
  @Autowired
  private CustomPlayerDetailsService playerDetailsService;
  
  @Autowired
  private CustomJWTAuthenticationFilter customJwtAuthenticationFilter;
  
  @Autowired
  private jwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
  
  private static final String[] WHITELIST = {
      // Unozar code
      "/player/createPlayer",
      "/player/authentication",
      "/player/refreshToken",
      // -- Swagger UI v2
      "/v2/api-docs",
      "/swagger-resources",
      "/swagger-resources/**",
      "/configuration/ui",
      "/configuration/security",
      "/swagger-ui.html",
      "/webjars/**",
      // -- Swagger UI v3 (OpenAPI)
      "/v3/api-docs/**",
      "/swagger-ui/**"
  };
  
  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception{
    auth.userDetailsService(playerDetailsService).passwordEncoder(
        passwordEncoder());
  }
  
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception{
    return super.authenticationManagerBean();
  }
  
  @Override
  public void configure(HttpSecurity http) throws Exception{
    http.csrf().disable().authorizeRequests()
    .antMatchers(WHITELIST).permitAll()
    .anyRequest().authenticated().and().exceptionHandling()
    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
    .and().sessionManagement()
    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    .and().addFilterBefore(customJwtAuthenticationFilter,
        UsernamePasswordAuthenticationFilter.class);
  }



}
