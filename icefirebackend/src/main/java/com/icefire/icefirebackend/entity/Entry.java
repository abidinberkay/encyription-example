package com.icefire.icefirebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Entry {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id
    Long id;
    private String text;
    private Long userId;

}
