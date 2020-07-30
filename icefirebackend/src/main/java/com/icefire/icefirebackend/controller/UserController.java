package com.icefire.icefirebackend.controller;

import com.icefire.icefirebackend.dto.UserDto;
import com.icefire.icefirebackend.entity.User;
import com.icefire.icefirebackend.exception.UserAlreadyExistException;
import com.icefire.icefirebackend.exception.UserNotFoundException;
import com.icefire.icefirebackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registerUser")
    public UserDto registerUser(@RequestBody UserDto userDto) throws UserAlreadyExistException {
        if (!isNull(userService.getUserByUsername(userDto))) {
            throw new UserAlreadyExistException("User with username " + userDto.getUsername() + "already exist");
        }
        User userObj;
        userObj = userService.saveUser(modelMapper.map(userDto, User.class));
        return modelMapper.map(userObj, UserDto.class);
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<UserDto> loginUser(@RequestBody UserDto userDto) throws UserNotFoundException {
        User userObj;
        if (isNull(userDto.getEmail()) || isNull(userDto.getPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            userObj = userService.getUserByEmailAndPassword(userDto);
            if (isNull(userObj)) {
                throw new UserNotFoundException("User is not authorized");
            }
            return ResponseEntity.ok(modelMapper.map(userObj, UserDto.class));
        }
    }
}
