package com.ApplicationSecurity.ApplicationSecurity.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Database class Model for User Table
 * @author akshataggarwal
 *
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    
    private boolean locked = false;
    private boolean enabled = true;
    private int failedLoginAttempts = 0;
    private LocalDateTime lastFailedLoginAttempt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public int getFailedLoginAttempts() {
		return failedLoginAttempts;
	}
	public void setFailedLoginAttempts(int failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}
	public LocalDateTime getLastFailedLoginAttempt() {
		return lastFailedLoginAttempt;
	}
	public void setLastFailedLoginAttempt(LocalDateTime lastFailedLoginAttempt) {
		this.lastFailedLoginAttempt = lastFailedLoginAttempt;
	}
	
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + ", locked="
				+ locked + ", enabled=" + enabled + ", failedLoginAttempts=" + failedLoginAttempts
				+ ", lastFailedLoginAttempt=" + lastFailedLoginAttempt + "]";
	}
	
	
	public User(Long id, String email, String password, Role role, boolean locked, boolean enabled,
			int failedLoginAttempts, LocalDateTime lastFailedLoginAttempt) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
		this.locked = locked;
		this.enabled = enabled;
		this.failedLoginAttempts = failedLoginAttempts;
		this.lastFailedLoginAttempt = lastFailedLoginAttempt;
	}
	
	public User() {
		super();
	}
    
    
}
