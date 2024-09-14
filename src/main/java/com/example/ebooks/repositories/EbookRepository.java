package com.example.ebooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ebooks.entities.Ebook;

@Repository
public interface EbookRepository extends JpaRepository<Ebook, Long> {
    Ebook findByName(String name);
}
