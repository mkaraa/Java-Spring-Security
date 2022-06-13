package com.sp.security.Java.Spring.Security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()   //TODO: next
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*")
                .permitAll()
                .antMatchers("/api/**").hasRole(ApplicationUserRoles.STUDENT.name())
                .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ApplicationUserRoles.ADMIN.name(),ApplicationUserRoles.ADMINTRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("linda")
                .password(passwordEncoder().encode("password"))
                .roles(ApplicationUserRoles.ADMIN.name());

        auth.inMemoryAuthentication()
                .withUser("smith")
                .password(passwordEncoder().encode("password"))
                .roles(ApplicationUserRoles.STUDENT.name());

        auth.inMemoryAuthentication()
                .withUser("tom")
                .password(passwordEncoder().encode("password"))
                .roles(ApplicationUserRoles.ADMINTRAINEE.name());
    }

    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails userMete = User.builder()
                .username("smith")
                .password(passwordEncoder().encode("password"))
                .roles(ApplicationUserRoles.STUDENT.name())
                .build();

        UserDetails userLinda = User.builder()
                .username("linda")
                .password(passwordEncoder().encode("password"))
                .roles(ApplicationUserRoles.ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(
                userMete,
                userLinda
        );
    }
}
