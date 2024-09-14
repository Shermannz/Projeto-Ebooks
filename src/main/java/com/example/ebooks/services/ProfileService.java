package com.example.ebooks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ebooks.dto.SimpleUserDto;
import com.example.ebooks.dto.UserDto;
import com.example.ebooks.entities.User;
import com.example.ebooks.repositories.RoleRepository;
import com.example.ebooks.repositories.UserRepository;

@Service
public class ProfileService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public SimpleUserDto findMe() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new SimpleUserDto(repository.findByEmail(username));
    }

    @Transactional
    public UserDto update(UserDto dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = repository.findByEmail(username);
        auxiliary(user, dto);
        repository.save(user);
        return new UserDto(user);
    }

    private void auxiliary(User user, UserDto dto) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setBalance(dto.getBalance());
        user.setCellPhone(dto.getCellPhone());

        for (String string : dto.getRoles()) {
            user.getRoles().add(roleRepository.findByAuthority(string));
        }
    }

    @Transactional
    public void delete() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        repository.deleteById(repository.findByEmail(username).getId());
    }

}
