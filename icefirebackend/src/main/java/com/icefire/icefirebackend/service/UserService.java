package com.icefire.icefirebackend.service;

import com.icefire.icefirebackend.dto.UserDto;
import com.icefire.icefirebackend.entity.User;
import com.icefire.icefirebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public User saveUser(User user) {
        user.setSecretKey(generateRandomKey());
        return userRepository.save(user);
    }

    public User getUserByUsername(UserDto userDto) {
        return userRepository.findByUsername(userDto.getUsername());
    }

    public User getUserByEmailAndPassword(UserDto userDto) {
        return userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
    }

    public String getUserKeyByUserId(Long id) {
        return userRepository.findById(id).get().getSecretKey();
    }

    private String generateRandomKey() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }
}
