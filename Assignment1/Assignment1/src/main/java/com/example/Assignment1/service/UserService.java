package com.example.Assignment1.service;

import com.example.Assignment1.model.User;
import com.example.Assignment1.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo,PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public void saveUser(User u) {
        u.setPassword(encoder.encode(u.getPassword()));
        repo.save(u);
    }

    public User findByUsername(String name) {
        return repo.findByUsername(name);
    }
}