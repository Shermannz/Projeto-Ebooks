package com.example.ebooks.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ebooks.dto.SimpleUserDto;
import com.example.ebooks.dto.UserDto;
import com.example.ebooks.entities.User;
import com.example.ebooks.repositories.RoleRepository;
import com.example.ebooks.repositories.UserRepository;
import com.example.ebooks.services.exceptions.CustomExceptions.EntityNotFoundEbooks;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    public UserDto findById(Long id) {
        return new UserDto(
                repository.findById(id).orElseThrow(() -> new EntityNotFoundEbooks()));
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    public List<SimpleUserDto> findAll() {
        List<User> user = repository.findAll();
        return user.stream().map(SimpleUserDto::new).toList();
    }

    @Transactional
    public UserDto insert(UserDto dto) {
        User user = new User();
        auxiliary(user, dto);

        return new UserDto(repository.save(user));
    }

    @Transactional
    public UserDto update(Long id, UserDto dto) {
        try {
            User user = repository.findById(id).get();
            auxiliary(user, dto);
            return new UserDto(repository.save(user));

        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundEbooks();
        }
    }

    private void auxiliary(User user, UserDto dto) {
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
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

}
