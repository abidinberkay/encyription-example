package com.icefire.icefirebackend.controller;

import com.icefire.icefirebackend.dto.EntryDto;
import com.icefire.icefirebackend.entity.Entry;
import com.icefire.icefirebackend.service.EntryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EntryController {

    @Autowired
    EntryService entryService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/getEntriesOfUser")
    public List<EntryDto> getUserEntries(@RequestParam("userId") Long userId) {
        return entryService.getEntriesByUserId(userId);
    }

    @PostMapping("/getDecryptedText")
    @ResponseBody
    public EntryDto getDecryptedText(@RequestBody EntryDto entryDto) {
        return entryService.getDecryptedText(entryDto);
    }

    @PostMapping("/saveEntry")
    public EntryDto saveEntry(@RequestBody EntryDto entryDto) {
        try {
            Entry savedEntry = entryService.saveEntry(modelMapper.map(entryDto, Entry.class));
            entryDto.setId(savedEntry.getId()).setText(savedEntry.getText());
            return entryDto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
