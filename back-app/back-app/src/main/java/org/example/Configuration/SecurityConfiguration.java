package org.example.Configuration;

import org.example.Services.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableWebSecurity
public class SecurityConfiguration implements WebMvcConfigurer {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, MyUserDetailsService userDetailsService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                // public urls
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/video/**").permitAll()
                .antMatchers("/category/**").permitAll()
                .antMatchers("/index-layout").permitAll()
                .antMatchers("/components/title-bar").permitAll()
                .antMatchers("/media/title_image.png").permitAll()
                .antMatchers("/favicon.ico").permitAll()

                // cookies cannot be set with xmlHttpRequests, so these components must be public even though they are
                // just used for authenticated users
                .antMatchers("/components/video-upload-success").permitAll()
                .antMatchers("/components/video-upload-fail").permitAll()

                // admin urls
                .antMatchers("/private-video/**").hasAuthority("admin")
                .antMatchers("/private-category/**").hasAuthority("admin")
                .antMatchers("/private-plan/**").hasAuthority("admin")
                .antMatchers("/admin").hasAuthority("admin").anyRequest()
                .authenticated().and().csrf().disable().formLogin()

                // login page
                .loginPage("/login")
                .failureUrl("http://videopulse.xyz/login?error=true")
                .defaultSuccessUrl("http://videopulse.xyz/")
                .usernameParameter("user_name")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and().exceptionHandling()
                .accessDeniedPage("/access-denied");

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}