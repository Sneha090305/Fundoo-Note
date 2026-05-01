package com.fundoo.notes.service;

import com.fundoo.notes.dto.LoginRequest;
import com.fundoo.notes.dto.RegisterRequest;
import com.fundoo.notes.entity.User;
import com.fundoo.notes.repository.UserRepository;
import com.fundoo.notes.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final JwtUtil jwtUtil;

    private final RabbitTemplate rabbitTemplate;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String register(RegisterRequest request) {

        // Duplicate check
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));

        repository.save(user);

        // PRODUCER CALL (UC12 + UC13 core)
        rabbitTemplate.convertAndSend("user_queue", "User registered: " + user.getEmail());

        return "User Registered Successfully";
    }

    @Override
    public String login(LoginRequest request) {

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}