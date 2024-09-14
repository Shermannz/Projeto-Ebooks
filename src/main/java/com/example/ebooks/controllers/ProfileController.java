package com.example.ebooks.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ebooks.dto.SimpleUserDto;
import com.example.ebooks.dto.UserDto;
import com.example.ebooks.services.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService service;

    @GetMapping
    ResponseEntity<SimpleUserDto> findMe() {
        return ResponseEntity.ok(service.findMe());
    }

    @PutMapping
    ResponseEntity<UserDto> update(@RequestBody @Valid UserDto dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping
    ResponseEntity<Void> delete() {
        service.delete();
        return ResponseEntity.noContent().build();
    }

}
