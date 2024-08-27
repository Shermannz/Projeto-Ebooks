package com.example.ebooks.dto;

import java.math.BigDecimal;

import com.example.ebooks.entities.Ebook;
import com.example.ebooks.entities.User;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EbookDto {
    private Long id;
    @NotBlank(message = "O nome não pode ser vazio")
    private String name;
    @NotBlank(message = "O Autor não pode ser vazio")
    private String author;
    @NotBlank(message = "A descrição não pode ser vazia")
    private String description;
    private Long sold = 0L;
    @NotBlank(message = "O preço não pode ser vazio")
    private BigDecimal price;

    public EbookDto(Ebook ebook) {
        this.id = ebook.getId();
        this.name = ebook.getName();
        this.author = ebook.getAuthor();
        this.description = ebook.getDescription();
        for (User user : ebook.getUsers()) {
            if (!user.getName().equalsIgnoreCase(author)) {
                this.sold += 1;
            }
        }
        this.price = ebook.getPrice();
    }

}
