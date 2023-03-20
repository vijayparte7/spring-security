package com.vijay.springsec.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import com.vijay.springsec.entity.Role;
import com.vijay.springsec.entity.Users;
import com.vijay.springsec.entity.repo.UsersRepo;

import ch.qos.logback.core.net.SyslogOutputStream;
import jakarta.servlet.http.HttpSession;

public class MyJDBCImpl implements UserDetails, UserDetailsManager {
	private static final long serialVersionUID = 1659695622548516027L;
	@Autowired
	private UsersRepo repo;
	Users user;

	public UserDetails loadUserByUsername(String username) {
		user = repo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("username not found: " + username + ".");
		}
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }
		return new User(user.getUsername(), user.getPassword(), grantedAuthorities);

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : user.getRoles()) {
			System.out.println(role.getAuthority());
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
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
