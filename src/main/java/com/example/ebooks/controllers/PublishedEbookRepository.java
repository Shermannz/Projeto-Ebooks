package com.example.ebooks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ebooks.dto.EbookDto;
import com.example.ebooks.services.PublishedEbookService;

@RestController
@RequestMapping("/publish")
public class PublishedEbookRepository {
    @Autowired
    private PublishedEbookService service;

    // @GetMapping("/{id}")
    // ResponseEntity<UserDto> findById(@PathVariable Long id) {
    // return ResponseEntity.ok(service.findById(id));
    // }

    // @GetMapping
    // ResponseEntity<List<UserDto>> findAll() {
    // return ResponseEntity.ok(service.findAll());
    // }

    @PostMapping("/{id}")
    ResponseEntity<EbookDto> insert(@PathVariable Long id, @RequestBody EbookDto ebookDto) {
        return ResponseEntity.ok(service.insert(id, ebookDto));
    }

    @PutMapping("/{userId}/{ebookId}")
    ResponseEntity<EbookDto> update(@PathVariable Long userId, @PathVariable Long ebookId,
            @RequestBody EbookDto ebookDto) {
        return ResponseEntity.ok(service.update(userId, ebookId, ebookDto));
    }

    @DeleteMapping("/{userId}/{ebookId}")
    ResponseEntity<Void> delete(@PathVariable Long userId, @PathVariable Long ebookId) {
        service.delete(userId, ebookId);
        return ResponseEntity.noContent().build();
    }

}
