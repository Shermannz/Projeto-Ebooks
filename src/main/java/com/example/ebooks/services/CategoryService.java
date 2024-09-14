package com.example.ebooks.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ebooks.dto.CategoryDto;
import com.example.ebooks.entities.Category;
import com.example.ebooks.repositories.CategoryRepository;
import com.example.ebooks.services.exceptions.CustomExceptions.EntityNotFoundEbooks;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public CategoryDto findById(Long id) {
        return new CategoryDto(
                repository.findById(id).orElseThrow(() -> new EntityNotFoundEbooks()));
    }

    @Transactional(readOnly = true)
    public List<CategoryDto> findAll() {
        List<Category> user = repository.findAll();
        return user.stream().map(CategoryDto::new).toList();
    }

    @Transactional
    public CategoryDto insert(CategoryDto dto) {
        Category user = new Category();
        user.setName(dto.getName());

        return new CategoryDto(repository.save(user));
    }

    @Transactional
    public CategoryDto update(Long id, CategoryDto dto) {
        try {
            Category category = repository.findById(id).get();
            category.setName(dto.getName());
            return new CategoryDto(repository.save(category));

        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundEbooks();
        }
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
