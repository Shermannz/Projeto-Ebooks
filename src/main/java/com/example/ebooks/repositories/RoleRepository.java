package com.example.ebooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ebooks.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByAuthority(String authority);
}
