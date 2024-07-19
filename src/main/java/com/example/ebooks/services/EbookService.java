package com.example.ebooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ebooks.dto.EbookDto;
import com.example.ebooks.entities.Ebook;
import com.example.ebooks.repositories.EbookRepository;

@Service
public class EbookService {

    @Autowired
    private EbookRepository repository;

    @Transactional(readOnly = true)
    public EbookDto findById(Long id) {
        return new EbookDto(repository.findById(id).orElseThrow(() -> new RuntimeException("oi")));
    }

    @Transactional(readOnly = true)
    public List<EbookDto> findAll() {
        List<Ebook> ebooks = repository.findAll();
        return ebooks.stream().map(EbookDto::new).toList();
    }

    @Transactional
    public EbookDto insert(EbookDto dto) {
        Ebook ebook = new Ebook();
        ebook.setName(dto.getName());
        ebook.setPrice(dto.getPrice());
        ebook.setAuthor(dto.getAuthor());
        return new EbookDto(repository.save(ebook));
    }

    @Transactional
    public EbookDto update(Long id, EbookDto dto) {
        Ebook ebook = repository.findById(id).get();
        ebook.setName(dto.getName());
        ebook.setPrice(dto.getPrice());
        ebook.setAuthor(dto.getAuthor());
        return new EbookDto(repository.save(ebook));
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
