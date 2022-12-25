package org.example.Configuration;

import org.example.Services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                // public urls
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/video/**").permitAll()
                .antMatchers("/page/**").permitAll()
                .antMatchers("/components/title-bar").permitAll()
                .antMatchers("/media/title_image.png").permitAll()

                // cookies cannot be set with xmlHttpRequests, so these components must be public even though they are
                // just used for authenticated users
                .antMatchers("/components/video-upload-success").permitAll()
                .antMatchers("/components/video-upload-fail").permitAll()

                // admin urls
                .antMatchers("/private-video/**").hasAuthority("admin")
                .antMatchers("/upload-video").hasAuthority("admin").anyRequest()
                .authenticated().and().csrf().disable().formLogin()

                // login page
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/upload-video")
                .usernameParameter("user_name")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}
