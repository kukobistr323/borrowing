package com.borrowing.user.service;

import com.borrowing.user.model.User;
import com.borrowing.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Vlad Kukoba
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByLogin(username).map(foundUser ->
                new org.springframework.security.core.userdetails.User(foundUser.getLogin(), foundUser.getPassword(), getAuthorities(foundUser)))
                .orElseThrow(() -> new UsernameNotFoundException("User with this login not found"));
    }

    public User loadUserByLogin(String login) {
        return userRepository.findUserByLogin(login)
                .orElse(null);
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAdmin(false);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    private List<GrantedAuthority> getAuthorities(User foundUser) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + (foundUser.getAdmin() ? "ADMIN" : "USER")));
    }
}
