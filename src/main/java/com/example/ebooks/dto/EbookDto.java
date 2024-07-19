package com.example.ebooks.dto;

import java.math.BigDecimal;

import com.example.ebooks.entities.Ebook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EbookDto {
    private Long id;
    private String name;
    private String author;
    private String description;
    private BigDecimal price;

    public EbookDto(Ebook ebook) {
        this.id = ebook.getId();
        this.name = ebook.getName();
        this.author = ebook.getAuthor();
        this.description = ebook.getDescription();
        this.price = ebook.getPrice();
    }

}
