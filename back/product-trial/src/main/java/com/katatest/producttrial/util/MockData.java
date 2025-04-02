package com.katatest.producttrial.util;

import com.katatest.producttrial.model.Product;
import com.katatest.producttrial.model.User;
import com.katatest.producttrial.repository.ProductRepository;
import com.katatest.producttrial.repository.UserRepository;
import com.katatest.producttrial.util.enums.InventoryStatus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MockData implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public MockData(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create in the database the admin user and two products only at first startup
        if (!productRepository.existsByCode("COD-01")) {
            String password = "password";
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(password);
            userRepository.save(new User("Admin Admin", "admin@admin.com", "admin", encodedPassword));

            productRepository.save(new Product("COD-01", "Shirt", "Cotton shirt", "image", "Clothes", 1000, 10, "REF-01", 7, InventoryStatus.INSTOCK, 4));
            productRepository.save(new Product("COD-02", "Dress", "Cotton dress", "image", "Clothes", 1000, 10, "REF-02", 8, InventoryStatus.LOWSTOCK, 4));
        }
    }
}
