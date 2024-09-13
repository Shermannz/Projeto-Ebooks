package com.example.ebooks.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ebooks.dto.EbookDto;
import com.example.ebooks.entities.Ebook;
import com.example.ebooks.entities.User;
import com.example.ebooks.entities.projections.UserDetailsProjection;
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
        String email = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsProjection) {
            UserDetailsProjection userDetails = (UserDetailsProjection) authentication.getPrincipal();
            email = userDetails.getUsername();
            System.out.println("Email: " + email); // Log para verificar o email
        }
        User user = repository.findByEmail(email);
        System.out.println("User: " + user); // Log para verificar o usuário
        if (user == null) {
            throw new EntityNotFoundEbooks("Usuário não encontrado");
        }
        Ebook ebook = ebookRepository.findById(ebookId)
                .orElseThrow(() -> new EntityNotFoundEbooks("Livro não encontrado"));
    
        if (user.getName().equals(ebook.getAuthor())) {
            return new EbookDto(ebook);
        } else {
            throw new NotAuthorizedCustom();
        }
    }

    @Transactional(readOnly = true)
    public List<EbookDto> findAll(Long userId) {
        User user = repository.findById(userId).get();
        List<EbookDto> dto = new ArrayList<>();

        for (Ebook ebook : user.getEbooks()) {
            if (user.getName().equals(ebook.getAuthor())) {
                dto.add(new EbookDto(ebook));
            }
        }
        return dto;
    }

    @Transactional
    public EbookDto insert(Long userid, EbookDto dto) {
        User user = repository.findById(userid)
                .orElseThrow(() -> new EntityNotFoundEbooks());

        Ebook ebook = new Ebook();
        auxiliary(ebook, dto);
        ebook.setAuthor(user.getName());
        user.getEbooks().add(ebook);
        return new EbookDto(ebookRepository.save(ebook));
    }

    @Transactional
    public EbookDto update(Long userId, Long ebookId, EbookDto dto) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundEbooks());
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
    public void delete(Long userId, Long ebookId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundEbooks());
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
