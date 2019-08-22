package com.senla.pdp.security;

import com.senla.pdp.api.service.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CredentialsService credentialsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        credentialsService.getCredentials().forEach(credentials -> {
            try {
                auth.inMemoryAuthentication().withUser(credentials.getMail()).password(credentials.getPassword())
                    .roles(credentials.getRole().getRole());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
