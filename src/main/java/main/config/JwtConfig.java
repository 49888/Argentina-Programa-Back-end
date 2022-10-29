package main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import main.config.FilterAuthentication.AuthenticationFilter;
import main.config.FilterAuthorization.AuthorizationFilter;
import main.services.JwtService;
import main.services.JwtUserDetailsService;


@Configuration
public class JwtConfig {
    
    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    JwtService jwtService;

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;
    

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //!Proteccion contra ataques csrf
        http.csrf(csrf -> csrf.disable());

        http.cors();
        
        http.headers().frameOptions().disable();



        //? Autorizar las rutas de los controllers
        http.authorizeRequests(auth -> {

            auth.antMatchers("/").permitAll();

            auth.antMatchers("/api/auth/test").permitAll();

            auth.antMatchers("/api/banner/get").permitAll();
            auth.antMatchers("/api/education/get").permitAll();
            auth.antMatchers("/api/experience/get").permitAll();
            auth.antMatchers("/api/skills/get").permitAll();
            auth.antMatchers("/api/projects/get").permitAll();

            auth.antMatchers("/api/auth/user").access("hasRole('USER') or hasRole('ADMIN')");

            auth.antMatchers("/api/auth/admin").hasRole("ADMIN");

            auth.anyRequest().authenticated();
        });


        //! Configuramos la session en stateless: El servidor no creara ninguna session
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                
        http.addFilter(authenticartionFilter());

        http.addFilter(new AuthorizationFilter(authenticationManager, jwtService, jwtUserDetailsService));

        http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        

        // http.authorizeHttpRequests((auth) -> {
        //     try {
        //         auth
        //             .antMatchers("/user").hasRole("USER")
        //             .antMatchers("/admin").hasRole("ADMIN")
        //             .anyRequest().permitAll()
        //             .and()
        //             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //             .and()
        //             .addFilter(authenticartionFilter())
        //             .addFilter(new JwtAuthorizationFilter(authenticationManager, authService, jwtUserDetailsService))
        //             .exceptionHandling()
        //             .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        //     } catch (Exception e) {
        //         throw new RuntimeException(e);
        //     }
        // });


        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    AuthenticationFilter authenticartionFilter(){

        AuthenticationFilter filter = new AuthenticationFilter();

        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);

        filter.setAuthenticationManager(authenticationManager);

        return filter;
    }
}