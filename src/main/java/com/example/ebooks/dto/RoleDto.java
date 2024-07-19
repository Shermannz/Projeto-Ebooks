package com.example.ebooks.dto;

import com.example.ebooks.entities.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDto {
    private Long id;
    private String authority;

    public RoleDto(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public RoleDto(Role role) {
        this.id = role.getId();
        this.authority = role.getAuthority();
    }

}
