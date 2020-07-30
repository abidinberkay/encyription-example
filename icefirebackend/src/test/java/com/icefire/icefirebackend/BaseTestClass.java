package com.icefire.icefirebackend;

import com.icefire.icefirebackend.entity.Entry;
import com.icefire.icefirebackend.entity.User;
import com.icefire.icefirebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = IcefirebackendApplication.class)
@SpringBootTest
public class BaseTestClass {

    @Autowired
    private UserService userService;

    public void setUpUser() {
        saveUser();
    }

    public Entry setUpEntry() {
        Entry entry = new Entry();
        entry.setText("Test text");
        entry.setUserId(1L);
        return entry;
    }

    public void saveUser() {
        User user = new User();
        user.setUserId(null);
        user.setUsername("John");
        user.setEmail("john@foo.com");
        user.setPassword("password");
        userService.saveUser(user);
    }

    public User setUpCustomUser() {
        User user = new User();
        user.setUserId(5L);
        user.setUsername("test1");
        user.setPassword("test1");
        user.setEmail("test1@test1.com");
        user.setSecretKey("test1");
        return user;
    }
}
