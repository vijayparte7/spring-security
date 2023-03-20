package com.vijay.springsec.entity.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vijay.springsec.entity.Users;

public interface UsersRepo extends JpaRepository<Users, Long> {
	Users findByUsername(String username);
}
