package com.katatest.producttrial.service;

import com.katatest.producttrial.dto.LoginUserDto;
import com.katatest.producttrial.dto.RegisterUserDto;
import com.katatest.producttrial.model.User;

public interface AuthenticationService {
    public User signup(RegisterUserDto input);
    public User authenticate(LoginUserDto input);
}
