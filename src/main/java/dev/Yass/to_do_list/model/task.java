package dev.Yass.to_do_list.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
public class task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    private String description;
    @DateTimeFormat
    private java.time.LocalDateTime reminderDate;
    private boolean repeat;
    private boolean done;
    private boolean deleted;
    private boolean archived;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories categories; // Category assignment for tasks

}
