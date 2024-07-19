package com.example.ebooks.dto;

import com.example.ebooks.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class SimpleUserDto {
    private Long id;
    private String name;
    private String email;
    private String cellPhone;

    public SimpleUserDto(Long id, String name, String email, String cellPhone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellPhone = cellPhone;
    }

    public SimpleUserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.cellPhone = user.getCellPhone();
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

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }
}
