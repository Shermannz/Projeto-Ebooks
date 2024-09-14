package com.example.ebooks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/{ebookId}")
    ResponseEntity<EbookDto> findById(@PathVariable Long ebookId) {
        return ResponseEntity.ok(service.findById(ebookId));
<<<<<<< HEAD
    }

    @GetMapping
    ResponseEntity<List<EbookDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
=======
>>>>>>> b18bffc9e8ad3765b702c3252213518bfc740602
    }

    @PostMapping
    ResponseEntity<EbookDto> insert(@RequestBody EbookDto ebookDto) {
        return ResponseEntity.ok(service.insert(ebookDto));
    }

    @PutMapping("/{ebookId}")
    ResponseEntity<EbookDto> update(@PathVariable Long ebookId, @RequestBody EbookDto ebookDto) {
        return ResponseEntity.ok(service.update(ebookId, ebookDto));
    }

    @DeleteMapping("/{ebookId}")
    ResponseEntity<Void> delete(@PathVariable Long ebookId) {
        service.delete(ebookId);
        return ResponseEntity.noContent().build();
    }

}
