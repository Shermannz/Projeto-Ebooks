package com.example.ebooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ebooks.dto.EbookDto;
import com.example.ebooks.dto.UserDto;
import com.example.ebooks.entities.Ebook;
import com.example.ebooks.entities.User;
import com.example.ebooks.repositories.EbookRepository;
import com.example.ebooks.repositories.RoleRepository;
import com.example.ebooks.repositories.UserRepository;
import com.example.ebooks.services.exceptions.CustomExceptions.EntityNotFoundEbooks;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EbookRepository ebookRepository;

    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        return new UserDto(
                repository.findById(id).orElseThrow(() -> new EntityNotFoundEbooks()));
    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<User> user = repository.findAll();
        return user.stream().map(UserDto::new).toList();
    }

    @Transactional
    public UserDto insert(UserDto dto) {
        User user = new User();
        auxiliar(user, dto);

        return new UserDto(repository.save(user));
    }

    @Transactional
    public UserDto update(Long id, UserDto dto) {
        try {
            User user = repository.findById(id).get();
            auxiliar(user, dto);
            return new UserDto(repository.save(user));

        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundEbooks();
        }
    }

    private void auxiliar(User user, UserDto dto) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setBalance(dto.getBalance());
        user.setCellPhone(dto.getCellPhone());

        for (String string : dto.getRoles()) {
            user.getRoles().add(roleRepository.findByAuthority(string));
        }
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public EbookDto publishEBook(Long Userid, EbookDto ebookPublish) {
        User user = repository.findById(Userid).get();
        Ebook ebook = new Ebook();
        ebook.setName(ebookPublish.getName());
        ebook.setAuthor(user.getName());
        ebook.setDescription(ebookPublish.getDescription());
        ebook.setPrice(ebookPublish.getPrice());
        user.getEbooks().add(ebook);
        return new EbookDto(ebookRepository.save(ebook));
    }

    @Transactional
    public void removePublishedEbook(Long userId, Long ebookId) {
        User user = repository.findById(userId).get();
        Ebook ebook = ebookRepository.findById(ebookId).get();
        if (user.getEbooks().contains(ebook) && ebook.getAuthor().equalsIgnoreCase(user.getName())) {
            user.getEbooks().remove(ebook);
            ebookRepository.deleteById(ebook.getId());
        }
    }

}
