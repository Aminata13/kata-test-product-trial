package com.katatest.producttrial.service.impl;

import com.katatest.producttrial.dto.LoginUserDto;
import com.katatest.producttrial.dto.RegisterUserDto;
import com.katatest.producttrial.model.User;
import com.katatest.producttrial.repository.UserRepository;
import com.katatest.producttrial.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User signup(RegisterUserDto input) {
        Optional<User> userExist = userRepository.findByEmail(input.getEmail());
        if (userExist.isPresent()) {
            throw  new IllegalArgumentException("A user with this mail address already exists");
        }
        User user = new User(input.getFullName(), input.getEmail(), input.getUsername(), passwordEncoder.encode(input.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
