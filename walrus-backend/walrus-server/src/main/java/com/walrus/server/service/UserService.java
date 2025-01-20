package com.walrus.server.service;

import com.walrus.pojo.entity.User;

public interface UserService {
    User findByUsername(String username);
    boolean validatePassword(String rawPassword, String encodedPassword);
    String encodePassword(String rawPassword);
} 