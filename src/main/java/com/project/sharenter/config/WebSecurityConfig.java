package com.project.sharenter.config;

import com.project.sharenter.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
    private LoginSucessHandler sucessHandler;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()

                //Based on a user's role, defines the request permissions to specified URL paths.
                .antMatchers("/", "/login", "/register", "/api").permitAll()
                .antMatchers("/renter/**").hasAnyAuthority("ROLE_RENTER")
                .antMatchers("/sharer/**").hasAnyAuthority("ROLE_SHARER")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()

                //Redirect based on authentication success or failure
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .successHandler(sucessHandler)
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
//        http.headers().frameOptions().sameOrigin();

        return http.build();
    }

    //Ignore paths, like images or scripts, to avoid errors in the view
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/img/**", "/css/**", "/js/**");
    }

}
