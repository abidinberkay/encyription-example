package com.icefire.icefirebackend;

import com.icefire.icefirebackend.dto.UserDto;
import com.icefire.icefirebackend.entity.User;
import com.icefire.icefirebackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ContextConfiguration(classes = IcefirebackendApplication.class)
@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceTest extends BaseTestClass {

    @Autowired
    private UserService userService;

    @Transactional
    @Test
    void getUserByUserName() {
        setUpUser();
        UserDto userDto = new UserDto();
        userDto.setUsername("John");
        User actualUser = userService.getUserByUsername(userDto);
        assertEquals(actualUser.getUsername(), userDto.getUsername());
        assertNotEquals(actualUser.getEmail(), null);
        assertNotEquals(actualUser.getPassword(), null);
        assertNotEquals(actualUser.getSecretKey(), null);
    }

    @Transactional
    @Test
    void getUserByEmailAndPassword() {
        setUpUser();
        UserDto userDto = new UserDto();
        userDto.setEmail("john@foo.com");
        userDto.setPassword("password");
        User actualUser = userService.getUserByEmailAndPassword(userDto);
        assertEquals(actualUser.getPassword(), userDto.getPassword());
        assertEquals(actualUser.getEmail(), userDto.getEmail());
        assertNotEquals(actualUser.getSecretKey(), null);
        assertNotEquals(actualUser.getUserId(), null);
    }
}
