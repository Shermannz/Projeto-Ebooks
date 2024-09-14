package com.example.ebooks.entities;

import java.util.HashSet;
import java.util.Set;

<<<<<<< HEAD
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

=======
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
>>>>>>> b18bffc9e8ad3765b702c3252213518bfc740602
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_role")
@Setter
@NoArgsConstructor
public class Role implements GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    public Role(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Long getId() {
        return id;
    }
    @Override
    public String getAuthority() {
        return authority;
    }

    public Set<User> getUsers() {
        return users;
    }


<<<<<<< HEAD

=======
>>>>>>> b18bffc9e8ad3765b702c3252213518bfc740602
}
