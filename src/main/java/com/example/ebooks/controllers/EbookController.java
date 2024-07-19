package com.example.ebooks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ebooks.dto.EbookDto;
import com.example.ebooks.services.EbookService;

@RestController
@RequestMapping("/ebooks")
public class EbookController {

    @Autowired
    private EbookService service;

    @GetMapping("/{id}")
    public ResponseEntity<EbookDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<EbookDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
