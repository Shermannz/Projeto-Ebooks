package com.example.ebooks.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.ebooks.entities.Ebook;
import com.example.ebooks.entities.Role;
import com.example.ebooks.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String cellPhone;
    private BigDecimal balance;
    private String password;
    private List<String> roles = new ArrayList<>();
    // private Set<RoleDto> roles = new HashSet<>();
    private List<EbookDto> ebooks = new ArrayList<>();

    public UserDto(Long id, String name, String email, BigDecimal balance, String cellPhone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.cellPhone = cellPhone;
        this.password = password;
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.balance = user.getBalance();
        this.cellPhone = user.getCellPhone();
        this.password = user.getPassword();
        
        
        for (Role role : user.getRoles()) {
            roles.add(role.getAuthority());
        }
    
        // Certifique-se de que o Set está corretamente sendo iterado e convertido para DTOs
        for (Ebook ebook : user.getEbooks()) {
            ebooks.add(new EbookDto(ebook));
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBalance(BigDecimal saldo) {
        this.balance = saldo;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
