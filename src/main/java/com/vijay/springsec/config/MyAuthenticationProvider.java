package com.vijay.springsec.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.vijay.springsec.entity.Role;
import com.vijay.springsec.entity.Users;
import com.vijay.springsec.entity.repo.UsersRepo;
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private UsersRepo repo;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getClass().toString();
		Users user = repo.findByUsername(username);
		if(user==null) {
			throw new BadCredentialsException("Credentials not valid");
		}else {
			List<GrantedAuthority> authorities= new ArrayList<>();
			for (Role role : user.getRoles()){
				authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
	        }
			return new UsernamePasswordAuthenticationToken(username, password,authorities);
			
		}
		
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
