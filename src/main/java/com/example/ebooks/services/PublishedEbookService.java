package com.example.ebooks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ebooks.dto.EbookDto;
import com.example.ebooks.entities.Ebook;
import com.example.ebooks.entities.User;
import com.example.ebooks.repositories.EbookRepository;
import com.example.ebooks.repositories.UserRepository;
import com.example.ebooks.services.exceptions.CustomExceptions.EntityNotFoundEbooks;

@Service
public class PublishedEbookService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EbookRepository ebookRepository;

    @Transactional
    public EbookDto insert(Long Userid, EbookDto dto) {
        User user = repository.findById(Userid).orElseThrow(() -> new EntityNotFoundEbooks());
        Ebook ebook = new Ebook();
        ebook.setName(dto.getName());
        ebook.setAuthor(user.getName());
        ebook.setDescription(dto.getDescription());
        ebook.setPrice(dto.getPrice());
        user.getEbooks().add(ebook);
        return new EbookDto(ebookRepository.save(ebook));
    }

    @Transactional
    public EbookDto update(Long userId, Long ebookId, EbookDto dto) {
        User user = repository.findById(userId).orElseThrow(() -> new EntityNotFoundEbooks());
        Ebook ebook = ebookRepository.findById(ebookId).orElseThrow(() -> new EntityNotFoundEbooks());
        if (user.getEbooks().contains(ebook)) {
            ebook.setName(dto.getName());
            ebook.setDescription(dto.getDescription());
            ebook.setPrice(dto.getPrice());
            return new EbookDto(ebookRepository.save(ebook));
        } else
            throw new RuntimeException();
    }

    @Transactional
    public void delete(Long userId, Long ebookId) {
        User user = repository.findById(userId).orElseThrow(() -> new EntityNotFoundEbooks());
        Ebook ebook = ebookRepository.findById(ebookId).orElseThrow(() -> new EntityNotFoundEbooks());
        if (user.getEbooks().contains(ebook) && ebook.getAuthor().equalsIgnoreCase(user.getName())) {
            user.getEbooks().remove(ebook);
            ebookRepository.deleteById(ebook.getId());
        }
    }

}
