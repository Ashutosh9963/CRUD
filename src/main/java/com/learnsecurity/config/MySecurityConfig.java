package com.learnsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.learnsecurity.services.CustomUserDetailService;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)  // this is to enable methosd level accessibility
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
//			.csrf().disable()
			.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.and()
			.authorizeRequests() // need to authorize request
//			.antMatchers("/home", "/login", "/register ").permitAll()
//			.antMatchers("/public/**").permitAll()
			.antMatchers("/public/**").hasRole("USER")
			.antMatchers("/users/**").hasRole("ADMIN")
			.antMatchers("/signin").permitAll()
			.anyRequest() // any request 
			.authenticated()  // authenticate
			.and()
//			.httpBasic(); // basic mechanism // there is no logout option and pop up type dialog box
			.formLogin() // this is form type and we can logout 
			.loginPage("/signin")
			.loginProcessingUrl("/doLogin")
			.defaultSuccessUrl("/users/");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
		
		
		
//		auth.inMemoryAuthentication().withUser("ashutosh").password(this.passwordEncoder().encode("12345")).roles("USER");
//		auth.inMemoryAuthentication().withUser("nayak").password(this.passwordEncoder().encode("123456")).roles("ADMIN");
	}
	
	// ROLE  - Hogh level overview - NORMAL : READ 
	// Aithority - permission ->  READ
	// ADMIN - READ, WRITE, UPDATE 
	
	@Bean  // by this we can use, inject, autowire in other classes
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10); // strength
	}
	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		
//		http
//			.authorizeHttpRequests((authz)-> authz
//					.anyRequest().authenticated())
//	}
	

}
