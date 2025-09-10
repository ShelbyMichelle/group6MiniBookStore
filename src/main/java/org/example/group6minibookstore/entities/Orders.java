package org.example.group6minibookstore.entities;

import jakarta.persistence.*;
import org.example.group6minibookstore.entities.Book;
import org.example.group6minibookstore.entities.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderDate;
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name="order_books",
            joinColumns=@JoinColumn(name="order_id"),
            inverseJoinColumns=@JoinColumn(name="book_id")
    )
    private List<Book> books;
}
