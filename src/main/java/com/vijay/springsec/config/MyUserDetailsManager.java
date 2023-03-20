package com.vijay.springsec.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import com.vijay.springsec.entity.Role;
import com.vijay.springsec.entity.Users;
import com.vijay.springsec.entity.repo.UsersRepo;

public class MyUserDetailsManager implements UserDetailsManager {
	@Autowired
	private UsersRepo repo;
	@Override
	public UserDetails loadUserByUsername(String username) {
		Users user = repo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("username not found: " + username + ".");
		}
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		}
		return new User(user.getUsername(), user.getPassword(), grantedAuthorities);

	}
	@Override
	public void createUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
