package com.example.ebooks.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.example.ebooks.entities.Order;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private LocalDateTime date;
    private String status;
    private BigDecimal total;

    private SimpleUserDto user;

    private Set<EbookDto> ebooks = new HashSet<>();

    public OrderDto(Order order) {
        this.id = order.getId();
        this.date = order.getDate();
        this.status = order.getStatus();
        this.user = new SimpleUserDto(order.getUser());
        order.getEbooks().stream().map(x -> ebooks.add(new EbookDto(x))).toList();
        this.total = order.subTotal();
    }
}
