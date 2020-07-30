package com.icefire.icefirebackend.service;

import com.icefire.icefirebackend.dto.EntryDto;
import com.icefire.icefirebackend.entity.Entry;
import com.icefire.icefirebackend.repository.EntryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jasypt.util.text.BasicTextEncryptor;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class EntryService {

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    UserService userService;

    @Transactional
    public Entry saveEntry(Entry entry) {
        String key = userService.getUserKeyByUserId(entry.getUserId());
        entry.setText(encyptor(key, entry.getText()));
        return entryRepository.save(entry);
    }

    public List<EntryDto> getEntriesByUserId(Long userId) {
        return mapEntryListToDto(entryRepository.findByUserId(userId));
    }

    public EntryDto getDecryptedText(EntryDto entryDto) {
        String key = userService.getUserKeyByUserId(entryDto.getUserId());
        entryDto.setText(decryptor(key, entryDto.getText()));
        return entryDto;
    }

    private List<EntryDto> mapEntryListToDto(List<Entry> entryList) {
        List<EntryDto> dtoList = new ArrayList<>();
        entryList.stream().forEach(t -> dtoList.add(modelMapper.map(t, EntryDto.class)));
        return dtoList;
    }

    private String decryptor(String key, String inputText) {
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);
        return basicTextEncryptor.decrypt(inputText);
    }

    private String encyptor(String key, String inputText) {
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);
        return basicTextEncryptor.encrypt(inputText);
    }
}
