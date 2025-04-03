package com.katatest.producttrial.util;

import com.katatest.producttrial.model.User;
import com.katatest.producttrial.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class UserUtil {
    private static UserRepository userRepository;

    public UserUtil(UserRepository userRepository) {
        UserUtil.userRepository = userRepository;
    }

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String  userMail = authentication.getName();
        Optional<User> user = userRepository.findByEmail(userMail);
        return user.orElse(null);
    }

    public static String getCurrentUserId() {
        return getCurrentUser().getId();
    }
}
