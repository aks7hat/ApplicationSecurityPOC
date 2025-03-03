package com.ApplicationSecurity.ApplicationSecurity.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ApplicationSecurity.ApplicationSecurity.entity.User;

/**
 * User Repository class for JPA
 * @author akshataggarwal
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
}
