package com.example.ebooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ebooks.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
