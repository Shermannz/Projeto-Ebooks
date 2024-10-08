package com.example.ebooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ebooks.dto.EbookDto;
import com.example.ebooks.dto.OrderDto;
import com.example.ebooks.entities.Ebook;
import com.example.ebooks.entities.Order;
import com.example.ebooks.entities.User;
import com.example.ebooks.entities.enums.Status;
import com.example.ebooks.repositories.EbookRepository;
import com.example.ebooks.repositories.OrderRepository;
import com.example.ebooks.repositories.UserRepository;
import com.example.ebooks.services.exceptions.CustomExceptions.EntityNotFoundEbooks;
import com.example.ebooks.services.exceptions.CustomExceptions.NotAuthorizedCustom;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;
    @Autowired
    private EbookRepository ebookRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public OrderDto findById(Long id) {
        return new OrderDto(
                repository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundEbooks()));
    }

    @Transactional(readOnly = true)
    public List<OrderDto> findAll() {
        List<Order> order = repository.findAll();
        return order.stream().map(OrderDto::new).toList();
    }

    @Transactional
    public OrderDto insert(OrderDto dto) {
        Order order = new Order();
        order.setDate(dto.getDate());

        User user = userRepository.getReferenceById(dto.getUser().getId());
        order.setUser(user);

        for (EbookDto ebookDto : dto.getEbooks()) {
            Ebook ebook = ebookRepository.findById(ebookDto.getId())
                    .orElseThrow(() -> new EntityNotFoundEbooks());

            order.getEbooks().add(ebook);

        }
        return new OrderDto(repository.save(order));
    }

    @Transactional
    public OrderDto updateToPaid(Long id) {
        Order order = repository.findByIdCustom(id)
                .orElseThrow(() -> new EntityNotFoundEbooks());
        order.setStatus(Status.PAGO);

        if (order.getStatus().equals(Status.PAGO)
                && !order.getEbooks().isEmpty()
                && order.getUser().getBalance()
                        .compareTo(order.subTotal()) >= 0) {

            order.getUser().withdraw(order.subTotal());

            for (Ebook ebook : order.getEbooks()) {
                order.getUser().getEbooks().add(ebook);
            }
            for (Ebook ebook : order.getEbooks()) {
                User user = userRepository.findByName(ebook.getAuthor());
                if (ebook.getAuthor().equalsIgnoreCase(user.getName())) {
                    user.deposit(ebook.getPrice());
                }
            }
        } else {
            throw new NotAuthorizedCustom();
        }
        return new OrderDto(repository.save(order));

    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
