package com.quiz.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class    SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private JWTFilter jwtFilter;

    @Autowired
    UserProvider userProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userProvider).passwordEncoder(passwordEncoder());

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();

    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();
        http
                .addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/api/account/authenticate").permitAll()
                .antMatchers("/api/account/register").permitAll()
//                .antMatchers("/api/question").hasAnyAuthority("ADMIN","MANAGER")
//                .antMatchers("/api/exam").hasAnyAuthority("ADMIN","MANAGER")
//                .antMatchers("/api/blog").hasAnyAuthority("ADMIN","MANAGER")
//                .antMatchers("/api/user").hasAnyAuthority("ADMIN")
//
//                .antMatchers("/api/question/all").permitAll()
//                .antMatchers("/api/question/get/{id}").permitAll()
//
//                .antMatchers("/api/questions/all").permitAll()
//                .antMatchers("/api/questions/get/{id}").permitAll()
//
//                .antMatchers("/api/exam/all").permitAll()
//                .antMatchers("/api/exam/get/{id}").permitAll()
//
//                .antMatchers("/api/user/all").authenticated()
//                .antMatchers("/api/user/get/{id}").authenticated()
//
//                .antMatchers("/api/blog/all").permitAll()
//                .antMatchers("/api/blog/get/{id}").permitAll()
//
//                .antMatchers("/api/level/all").permitAll()
//                .antMatchers("/api/level/get/{id}").permitAll()
//
//                .antMatchers("/api/overall/all").permitAll()
//                .antMatchers("/api/overall/get/{id}").permitAll()
                .antMatchers("/api/fayl/download/{id}").permitAll()
//
//                .antMatchers("/swagger-ui/**").permitAll()
//                .antMatchers("/swagger-ui.html").permitAll()

                .antMatchers("/**").hasAnyAuthority("ADMIN, MANAGER")
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter,
                UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/app/**/*.{js,html}")
                .antMatchers("/i18n/**")
                .antMatchers("/content/**")
                .antMatchers("/h2-console/**")
                .antMatchers("/swagger-ui/**")
                .antMatchers("/test/**")
                .antMatchers("/*.*")//
                .antMatchers("/v3/api-docs/**")
                .antMatchers("/swagger-ui/**")
                .antMatchers("/swagger-ui.html")
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedOrigin("http://localhost:5500");
        config.addAllowedOrigin("http://localhost:5501");
        config.addAllowedOrigin("http://localhost:5502");
        config.addAllowedOrigin("http://159.65.116.253:5200");
        config.addAllowedOrigin("https://edu-mutaallim.uz");
        config.addAllowedOrigin("http://64.226.120.239");
        config.addAllowedOrigin("https://swagger.edu-mutaallim.uz");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);

    }

}

