package com.example.ebooks.dto;

import java.math.BigDecimal;

import com.example.ebooks.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class SimpleUserDto {
    private Long id;
    private String name;
    private String email;
    private String cellPhone;
    private BigDecimal balance;

    public SimpleUserDto(Long id, String name, String email, String cellPhone, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellPhone = cellPhone;
        this.balance = balance;
    }

    public SimpleUserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.cellPhone = user.getCellPhone();
        this.balance = user.getBalance();
    }

}
