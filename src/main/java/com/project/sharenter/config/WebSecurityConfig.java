package com.project.sharenter.config;

import com.project.sharenter.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private CustomAuthSuccessHandler authSuccessHandler;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //Configures authentication
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        //Encrypts password
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    //Configures HTTP Security
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //CSRF is disabled because tags are not being used in the login form
        http.csrf().disable()

                //Based on a user's role, it defines the access permissions to specified URL paths
                .authorizeRequests()
                .antMatchers("/", "/login", "/register", "/about", "/how", "/actuator/**").permitAll()
                .antMatchers("/renter/**").hasAnyAuthority("ROLE_RENTER")
                .antMatchers("/sharer/**").hasAnyAuthority("ROLE_SHARER")
                .anyRequest().authenticated()

                //After login authentication redirect is based on the authentication success or failure
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .successHandler(authSuccessHandler)
                .usernameParameter("email")
                .passwordParameter("password")
                .and()

                //Logout
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .and()

                //Access denied for users without authorization
                .exceptionHandling()
                .accessDeniedPage("/access-denied");

        //Authentication
        http.authenticationProvider(authenticationProvider());

        return http.build();
    }

    //Debug level - ignore paths, like images or scripts, to avoid errors in the view when not authenticated
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/img/**", "/css/**", "/js/**");
    }

}
