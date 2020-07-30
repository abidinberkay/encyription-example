package com.icefire.icefirebackend.dto;

public class EntryDto {

    private Long id;
    private String text;
    private Long userId;

    public EntryDto() {
    }

    public EntryDto(Long id, String text, Long userId) {
        this.id = id;
        this.text = text;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public EntryDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public EntryDto setText(String text) {
        this.text = text;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public EntryDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}

