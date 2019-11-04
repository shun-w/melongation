package org.assignment.melongation.config;

import org.assignment.melongation.service.UserService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserService());
    }

    @Override
    public void configure(WebSecurity web) {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/admim/**").hasRole("admin")
                .antMatchers(new String[]{"/user/login", "/admin/login"}).permitAll()
//                .and()
//        .formLogin().loginPage("/login").successForwardUrl()
        ;
    }
}
