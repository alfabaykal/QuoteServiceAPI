package com.alfabaykal.QuoteServiceAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "user_name")
    private String name;

    @NotNull
    @Size(max = 255)
    @Column(name = "user_email", unique = true)
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registered_at")
    private Date registered;
}
