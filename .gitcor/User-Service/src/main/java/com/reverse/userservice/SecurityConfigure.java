package com.reverse.userservice;

import lombok.Generated;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

    @Override
    @Generated
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**");
        web.ignoring().antMatchers("/actuator/**");
    }
}
