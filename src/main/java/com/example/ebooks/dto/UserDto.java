package com.example.ebooks.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.ebooks.entities.User;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class UserDto {
    private Long id;
    @NotBlank(message = "O nome não pode ser vazio")
    private String name;
    @NotBlank(message = "O email não pode ser vazio")
    private String email;
    @NotBlank(message = "O telefone não pode ser vazio")
    private String cellPhone;
    @NotBlank(message = "A senha não pode ser vazia")
    private String password;
    private List<String> roles = new ArrayList<>();
    // private Set<RoleDto> roles = new HashSet<>();
    private Set<EbookDto> ebooks = new HashSet<>();

    public UserDto(Long id, String name, String email, String cellPhone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellPhone = cellPhone;
        this.password = password;
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.cellPhone = user.getCellPhone();
        this.password = user.getPassword();
        user.getRoles().forEach(x -> roles.add(x.getAuthority()));

        // user.getRoles().forEach(x -> roles.add(new RoleDto(x)));
        user.getEbooks().forEach(x -> ebooks.add(new EbookDto(x)));
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

    public void setPassword(String password) {
        this.password = password;
    }

}
