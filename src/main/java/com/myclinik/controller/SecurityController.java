package com.myclinik.controller;

import java.beans.BeanProperty;

import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityController extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		// .antMatchers("/admin/**").hasRole("ADMIN")
		// .antMatchers("/users/**").hasRole("ADMIN")
		.antMatchers("/clients/**", "/appointments/**", "/treatments/**").hasAnyRole("OPS", "ADMIN")
		.antMatchers("/statistics/**").hasAnyRole("CONT", "ADMIN")
		.antMatchers("/css/**", "/assets/**", "/layouts/**", "/login*", "/").permitAll()
		.anyRequest().authenticated().and()

		.formLogin()
		.loginPage("/login")
		// .defaultSuccessUrl("/home", true)
		.failureUrl("/login?error")
		.permitAll()
		.and()

		.logout()
		.permitAll()
		.and()

		.httpBasic()
		;
	}

	@Autowired
	private DataSource ds;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.authenticationProvider(authProvider);
		// auth.inMemoryAuthentication()
		//     .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
		//     .and()
		//     .withUser("ops").password(passwordEncoder().encode("ops")).roles("OPS")
		//     .and()
		//     .withUser("cont").password(passwordEncoder().encode("cont")).roles("CONT");

		auth.jdbcAuthentication()
		.dataSource(ds);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
