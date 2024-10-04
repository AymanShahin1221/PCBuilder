package com.app.entity.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "\"User\"")
public class User
{

    @Id
    private UUID userId;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String password;

    public User()
    {
    }

    public User(UUID userId, String username, String email, String password)
    {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
