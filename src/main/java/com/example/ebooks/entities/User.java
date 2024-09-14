package com.example.ebooks.entities;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

<<<<<<< HEAD
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

=======
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
>>>>>>> b18bffc9e8ad3765b702c3252213518bfc740602
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor
<<<<<<< HEAD
public class User implements UserDetails {
=======
public class User implements UserDetails{
>>>>>>> b18bffc9e8ad3765b702c3252213518bfc740602
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "tb_user_ebook", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "ebook_id"))
    private Set<Ebook> ebooks = new HashSet<>();

    public void withdraw(BigDecimal balance) {
        this.balance = this.balance.subtract(balance);
    }

    public void deposit(BigDecimal balance) {
        this.balance = this.balance.add(balance);
    }

    // Tive problemas com o @Data no User.
    // Ao por ele a lista de ebooks e roles não aparecem.

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
<<<<<<< HEAD
        return roles;
=======
        return getRoles();
>>>>>>> b18bffc9e8ad3765b702c3252213518bfc740602
    }

    @Override
    public String getUsername() {
<<<<<<< HEAD
        return email;
=======
        return getEmail();
>>>>>>> b18bffc9e8ad3765b702c3252213518bfc740602
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
