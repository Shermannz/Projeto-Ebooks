package com.example.ebooks.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.ebooks.entities.enums.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_order")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date = LocalDateTime.now();
    private Status status = Status.PENDENTE;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "tb_order_ebook", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "ebook_id"))
    private Set<Ebook> ebooks = new HashSet<>();

    public Order(Long id, LocalDateTime date, Status status, User user) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.user = user;
    }


    public BigDecimal subTotal() {
        BigDecimal total = BigDecimal.ZERO;
        if (!ebooks.isEmpty()) {
            for (BigDecimal st : ebooks.stream().map(Ebook::getPrice).toList()) {
                total = total.add(st);
            }
        }
        return total.setScale(2, RoundingMode.CEILING);
    }
}
