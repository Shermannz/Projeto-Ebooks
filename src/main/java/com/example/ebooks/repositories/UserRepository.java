package com.example.ebooks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ebooks.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query("SELECT obj FROM User obj " +
            "JOIN FETCH obj.ebooks")
    List<User> findAllUsers();

    User findByName(String name);

}
