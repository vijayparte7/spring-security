package com.vijay.springsec.config;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.vijay.springsec.entity.Role;
import com.vijay.springsec.entity.Users;
import com.vijay.springsec.entity.repo.UsersRepo;
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private UsersRepo repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) {
		System.out.println("Inside MyAuthenticationProvider");
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		Users user = repo.findByUsername(username);
		String db_password = user.getPassword();
		boolean matches = passwordEncoder.matches(password, db_password);
		if (matches) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			for (Role role : user.getRoles()) {
				authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
			}
			return new UsernamePasswordAuthenticationToken(username, password, authorities);
		} else
			System.out.println("Invalid credentials");
		throw new BadCredentialsException("Invalid credentials");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
