package dev.Yass.to_do_list.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
@Entity
@Table(name = "\"user\"") @Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @Column(nullable = false)
    private String role = "client";

    @PrePersist
    @PreUpdate
    private void ensureRoleIsClient() {
        if (!"admin".equals(this.role)) {
            this.role = "client";
        }
    }


    public User(String username, String hashedPassword) {
        this.username = username;
        this.password = hashedPassword;
    }
}
