package com.vijay.springsec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecConfiguration {
	@Bean
	public SecurityFilterChain chain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.addFilterAfter(new MyCustomFilter(),BasicAuthenticationFilter.class)
		.authorizeHttpRequests()
		.requestMatchers("/test1").hasRole("ADMIN")
		.requestMatchers("/test2").hasRole("USER")
		.requestMatchers("/test","/signup").permitAll()
		.anyRequest().authenticated()
		.and().formLogin()
		.and().httpBasic()
		;
		return http.build();
	}
	/*
	 * Creating Bean UserDetailsService for spring context
	 */
//	@Bean
//	public UserDetailsService userDetailsService() {
		/*
		 * InMemoryUserDetailsManager
		 * To store users details in memory
		 */
//		UserDetails user1=User.withUsername("vijay").password(bCryptPasswordEncoder().encode("1234")).roles("ADMIN").build();
//		UserDetails user2=User.withUsername("vijayp").password(bCryptPasswordEncoder().encode("12345")).roles("user2").build();
//		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(user1,user2);
		
		/*
		 * Costom implemntation of UserDetailsManager
		 */
//		UserDetailsManager userDetailsManager=new MyUserDetailsManager();
//		return userDetailsManager;
//	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();

	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
