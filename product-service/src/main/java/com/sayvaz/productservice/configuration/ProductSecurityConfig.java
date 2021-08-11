package com.sayvaz.productservice.configuration;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;


@Configuration
@EnableWebSecurity
public class ProductSecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	DataSource dataSource;

	@Value("${auth.queries.find-user-query}")
	private String findUserQuery;

	@Value("${auth.queries.find-role-query}")
	private String findRoleQuery;
	
	@Value("${auth.queries.create-role-query}")
	private String createRoleQuery;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager() throws Exception {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(dataSource);
		jdbcUserDetailsManager.setUsersByUsernameQuery(findUserQuery);
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(findRoleQuery);
		jdbcUserDetailsManager.setCreateAuthoritySql(createRoleQuery);
		return jdbcUserDetailsManager;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder())
			.usersByUsernameQuery(findUserQuery)
			.authoritiesByUsernameQuery(findRoleQuery);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
			.antMatchers("/api/v1/products/**").authenticated()
			.antMatchers(HttpMethod.POST, "/api/v1/users/signup").permitAll().and().httpBasic();
	}
	
	

}
