package com.example.ebooks.entities;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private BigDecimal balance;
    private String cellPhone;
    private String password;

    public User(Long id, String name, String email, BigDecimal balance, String cellPhone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.cellPhone = cellPhone;
        this.password = password;
    }

    @ManyToMany
    @JoinTable(name = "tb_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "tb_user_ebook", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "ebook_id"))
    private Set<Ebook> ebooks = new HashSet<>();

    public void withdraw(BigDecimal balance) {
        this.balance = this.balance.subtract(balance);
    }
}
