package com.example.ebooks.controllers;

import com.example.ebooks.dto.EbookDto;
import com.example.ebooks.services.EbookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @PostMapping
    ResponseEntity<EbookDto> insert(@RequestBody @Valid EbookDto dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(service.insert(dto));
    }

    @PutMapping
    ResponseEntity<EbookDto> update(@PathVariable Long id, @RequestBody @Valid EbookDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<EbookDto> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
