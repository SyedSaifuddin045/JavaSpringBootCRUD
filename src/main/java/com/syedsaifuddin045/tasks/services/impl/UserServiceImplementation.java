package com.syedsaifuddin045.tasks.services.impl;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.syedsaifuddin045.tasks.domain.dto.UserDTO;
import com.syedsaifuddin045.tasks.domain.entities.User;
import com.syedsaifuddin045.tasks.mappers.UserMapper;
import com.syedsaifuddin045.tasks.repositories.UserRepository;
import com.syedsaifuddin045.tasks.services.UserService;
import com.syedsaifuddin045.tasks.util.JwtUtil;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImplementation(UserRepository userRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserDTO register(UserDTO userDTO) {
        Optional<User> existing = userRepository.findByEmail(userDTO.email());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("User already exists with this email.");
        }

        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        User saved = userRepository.save(user);
        return userMapper.toDTO(saved);
    }

    @Override
    public String login(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(userDTO.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getEmail()); // or user.getId()
    }

    @Override
    public UserDTO getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User user) {
            return userMapper.toDTO(user);
        }

        throw new IllegalStateException("No authenticated user found");
    }
}
