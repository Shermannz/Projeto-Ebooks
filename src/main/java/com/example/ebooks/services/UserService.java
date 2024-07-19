package com.example.ebooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ebooks.dto.EbookDto;
import com.example.ebooks.dto.UserDto;
import com.example.ebooks.entities.User;
import com.example.ebooks.repositories.EbookRepository;
import com.example.ebooks.repositories.RoleRepository;
import com.example.ebooks.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private EbookRepository ebookRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        return new UserDto(repository.findById(id).get());
    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<User> user = repository.findAllUsers();
        return user.stream().map(UserDto::new).toList();
    }

    @Transactional
    public UserDto insert(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setCellPhone(dto.getCellPhone());
        user.setCellPhone(dto.getCellPhone());

        for (EbookDto ebookDto : dto.getEbooks()) {
            user.getEbooks().add(ebookRepository.getReferenceById(ebookDto.getId()));
        }

        for (String string : dto.getRoles()) {
            user.getRoles().add(roleRepository.findByAuthority(string));
        }

        return new UserDto(repository.save(user));
    }

    @Transactional
    public UserDto update(Long id, UserDto dto) {
        User user = repository.findById(id).get();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setCellPhone(dto.getCellPhone());
        user.setCellPhone(dto.getCellPhone());

        for (String string : dto.getRoles()) {
            user.getRoles().add(roleRepository.findByAuthority(string));
        }
        return new UserDto(repository.save(user));
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
