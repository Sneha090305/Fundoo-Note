package com.fundoo.notes.service;

import com.fundoo.notes.dto.RegisterRequest;
import com.fundoo.notes.dto.LoginRequest;

public interface UserService {

    String register(RegisterRequest request);

    String login(LoginRequest request);
}