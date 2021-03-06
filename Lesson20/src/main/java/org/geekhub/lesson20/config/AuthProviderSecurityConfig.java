package org.geekhub.lesson20.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
public class AuthProviderSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
        .and()
                .formLogin()
                    .loginProcessingUrl("/auth")
                    .usernameParameter("username")
                    .passwordParameter("password")
                .permitAll()
        .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                .permitAll();
    }

    @Autowired
    public void configureGlobal(CustomAuthenticationProvider authenticationProvider, AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }
}
