package com.example.ebooks.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    private String status = "Pendente";

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "tb_order_ebook", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "ebook_id"))
    private List<Ebook> ebooks = new ArrayList<>();

    public Order(Long id, LocalDateTime date, String status, User user) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.user = user;
    }

    public void pago() {
        if (status.equalsIgnoreCase("Pago") && !ebooks.isEmpty()) {
            for (Ebook ebook : ebooks) {
                user.getEbooks().add(ebook);
            }
        }
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
