package com.example.ebooks.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ebooks.dto.EbookDto;
import com.example.ebooks.entities.Ebook;
import com.example.ebooks.entities.User;
import com.example.ebooks.repositories.EbookRepository;
import com.example.ebooks.repositories.UserRepository;
import com.example.ebooks.services.exceptions.CustomExceptions.EntityNotFoundEbooks;
import com.example.ebooks.services.exceptions.CustomExceptions.NotAuthorizedCustom;

@Service
public class PublishedEbookService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EbookRepository ebookRepository;

    @Transactional(readOnly = true)
    public EbookDto findById(Long ebookId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = repository.findByEmail(username);
        Ebook ebook = ebookRepository.findById(ebookId)
                .orElseThrow(() -> new EntityNotFoundEbooks("Livro não encontrado"));
    
        if (user.getName().equals(ebook.getAuthor())) {
            return new EbookDto(ebook);
        } else {
            throw new NotAuthorizedCustom();
        }
    }

    @Transactional(readOnly = true)
    public List<EbookDto> findAll() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = repository.findByEmail(username);
        List<EbookDto> dto = new ArrayList<>();

        for (Ebook ebook : user.getEbooks()) {
            if (user.getName().equals(ebook.getAuthor())) {
                dto.add(new EbookDto(ebook));
            }
        }
        return dto;
    }

    @Transactional
    public EbookDto insert(EbookDto dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = repository.findByEmail(username);

        Ebook ebook = new Ebook();
        auxiliary(ebook, dto);
        ebook.setAuthor(user.getName());
        user.getEbooks().add(ebook);
        return new EbookDto(ebookRepository.save(ebook));
    }

    @Transactional
    public EbookDto update(Long ebookId, EbookDto dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = repository.findByEmail(username);
        Ebook ebook = ebookRepository.findById(ebookId)
                .orElseThrow(() -> new EntityNotFoundEbooks("Livro nao encontrado"));

        if (user.getEbooks().contains(ebook)) {
            auxiliary(ebook, dto);
            return new EbookDto(ebookRepository.save(ebook));
        } else
            throw new RuntimeException();

    }

    public void auxiliary(Ebook ebook, EbookDto dto) {
        ebook.setName(dto.getName());
        ebook.setDescription(dto.getDescription());
        ebook.setPrice(dto.getPrice());
    }

    @Transactional
    public void delete(Long ebookId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = repository.findByEmail(username);
        Ebook ebook = ebookRepository.findById(ebookId)
                .orElseThrow(() -> new EntityNotFoundEbooks("Livro nao encontrado"));

        if (!user.getEbooks().contains(ebook) && !ebook.getAuthor().equalsIgnoreCase(user.getName())) {
            throw new EntityNotFoundEbooks();
        } else {
            user.getEbooks().remove(ebook);
            ebookRepository.deleteById(ebook.getId());
        }
    }

}
