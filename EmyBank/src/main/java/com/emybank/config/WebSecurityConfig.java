package com.emybank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsService accountDetailServiceImpl;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(accountDetailServiceImpl).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .csrf().disable();
		
		http.authorizeRequests().antMatchers("/logout", "/login").permitAll();
   
        // Trang chỉ dành cho MEMBER
		http.authorizeRequests().antMatchers("/member/**").access("hasRole('ROLE_USER')");
		
      http.formLogin()
      .loginPage("/login").permitAll()
      .defaultSuccessUrl("/member/account").failureUrl("/login?e=error")
       .and().logout()
       .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout=true").and()
        .exceptionHandling().accessDeniedPage("/login?e=deny");
        
//        http.formLogin().loginPage("/login").defaultSuccessUrl("/member/account").failureUrl("/login?e=error").permitAll().and().logout().permitAll().
//		and().exceptionHandling().accessDeniedPage("/login?e=deny");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(16); 
	}
	
	public static void main(String[] args) {
//		String rawPass = "Kocopass123";
//		String encode = new BCryptPasswordEncoder().encode(rawPass);
//		System.out.println(encode);
//		//
//		boolean check = new BCryptPasswordEncoder().matches(rawPass, encode);
		
	}
}
