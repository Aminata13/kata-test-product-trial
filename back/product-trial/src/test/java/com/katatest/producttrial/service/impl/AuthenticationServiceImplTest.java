package com.katatest.producttrial.service.impl;

import com.katatest.producttrial.dto.LoginUserDto;
import com.katatest.producttrial.dto.RegisterUserDto;
import com.katatest.producttrial.model.User;
import com.katatest.producttrial.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AuthenticationServiceImplTest {

    private AuthenticationServiceImpl authenticationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        this.authenticationService = new AuthenticationServiceImpl(userRepository, passwordEncoder, authenticationManager);
    }

    @Test
    void signup() {
        RegisterUserDto mockRegisterUserDto = Mockito.mock(RegisterUserDto.class);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(Mockito.mock(User.class));

        User result = authenticationService.signup(mockRegisterUserDto);
        assertNotNull(result);
        assertEquals(mockRegisterUserDto.getFullName(), result.getFullName());
        assertEquals(mockRegisterUserDto.getEmail(), result.getEmail());
        assertEquals(mockRegisterUserDto.getUsername(), result.getUsername());
        assertEquals(mockRegisterUserDto.getPassword(), result.getPassword());
    }

    @Test
    void signupWithWrongEmail() {
        Mockito.when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(Mockito.mock(User.class)));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {authenticationService.signup(new RegisterUserDto());});
        assertEquals("A user with this mail address already exists", exception.getMessage());
    }

    @Test
    void authenticate() {
        LoginUserDto mockedUserDto = new LoginUserDto();
        mockedUserDto.setEmail("email@email.com");
        mockedUserDto.setPassword("password");

        Mockito.when(userRepository.findByEmail("email@email.com")).thenReturn(Optional.of(Mockito.mock(User.class)));

        authenticationService.authenticate(mockedUserDto);
    }
}