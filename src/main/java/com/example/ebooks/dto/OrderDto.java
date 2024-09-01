package com.example.ebooks.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private List<EbookDto> ebooks = new ArrayList<>();

    public OrderDto(Order order) {
        this.id = order.getId();
        this.date = order.getDate();
        this.status = order.getStatus().toString();
        this.user = new SimpleUserDto(order.getUser());
        order.getEbooks().stream().map(x -> ebooks.add(new EbookDto(x))).toList();
        this.total = order.subTotal();
    }
}
