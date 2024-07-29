package com.example.ebooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ebooks.dto.UserDto;
import com.example.ebooks.entities.User;
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
        user.setCellPhone(dto.getCellPhone());
        user.setCellPhone(dto.getCellPhone());

        for (String string : dto.getRoles()) {
            user.getRoles().add(roleRepository.findByAuthority(string));
        }
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
