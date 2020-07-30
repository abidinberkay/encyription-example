package com.icefire.icefirebackend;

import com.icefire.icefirebackend.dto.EntryDto;
import com.icefire.icefirebackend.entity.Entry;
import com.icefire.icefirebackend.entity.User;
import com.icefire.icefirebackend.repository.UserRepository;
import com.icefire.icefirebackend.service.EntryService;
import com.icefire.icefirebackend.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.commons.lang3.RandomStringUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = IcefirebackendApplication.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class EntryServiceTest extends BaseTestClass {

    @Autowired
    private EntryService entryService;

    @Autowired
    private UserService userService;

    @Transactional
    @Test
    public void saveEntry() {
        setUpUser();
        Entry entry = setUpEntry();
        Entry entryToSave = new Entry();
        entryToSave.setText(entry.getText());
        entryToSave.setUserId(entry.getUserId());
        entryToSave = entryService.saveEntry(entryToSave);
        assertNotEquals(entry.getText(), entryToSave.getText());
        assertEquals(entry.getUserId(), entryToSave.getUserId());
    }

    @Transactional
    @Test
    public void getEntriesByUserId() {
        User user = setUpCustomUser();
        user = userService.saveUser(user);
        int numberOfEntries = Integer.parseInt(RandomStringUtils.randomNumeric(1, 1));
        for (int x = 0; x < numberOfEntries; x++) {
            entryService.saveEntry(new Entry(null, "text" + x, user.getUserId()));
        }
        List<EntryDto> entryDtos = entryService.getEntriesByUserId(user.getUserId());
        assertEquals(entryDtos.size(), numberOfEntries);
    }
}
