package com.ApplicationSecurity.ApplicationSecurity.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ApplicationSecurity.ApplicationSecurity.Dto.UserDTO;
import com.ApplicationSecurity.ApplicationSecurity.Utils.JwtUtil;
import com.ApplicationSecurity.ApplicationSecurity.entity.Role;
import com.ApplicationSecurity.ApplicationSecurity.entity.User;
import com.ApplicationSecurity.ApplicationSecurity.repo.UserRepository;

/**
 * Service Layer class for Registration and Login.
 * @author akshataggarwal
 *
 */
@Service
public class AuthService {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
	
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final int LOCK_DURATION_MINUTES = 15;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User registerUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFailedLoginAttempts(0);
        user.setEnabled(true);
        user.setLocked(false);
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public String authenticateUser(UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(userDTO.getEmail());

        if (optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        if (user.isLocked() && user.getLastFailedLoginAttempt() != null) {
            if (user.getLastFailedLoginAttempt().plusMinutes(LOCK_DURATION_MINUTES).isBefore(LocalDateTime.now())) {
                user.setLocked(false);
                user.setFailedLoginAttempts(0);
                userRepository.save(user);
            } else {
                return "Account is locked. Try again later.";
            }
        }


        if (passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
        	logger.info("User {} logged in successfully", userDTO.getEmail());
            user.setFailedLoginAttempts(0);
            userRepository.save(user);
            return jwtUtil.generateToken(user.getEmail());
        } else {
            int attempts = user.getFailedLoginAttempts() + 1;
            user.setFailedLoginAttempts(attempts);
            user.setLastFailedLoginAttempt(LocalDateTime.now());
            logger.warn("Failed login attempt for {}", userDTO.getEmail());
            if (attempts >= MAX_FAILED_ATTEMPTS) {
                user.setLocked(true);
            }

            userRepository.save(user);
            return "Invalid credentials. Attempt " + attempts + " of " + MAX_FAILED_ATTEMPTS;
        }
    }
}