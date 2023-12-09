package com.example.task_management_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String message;
    @ManyToOne
    private User author;
    @ManyToOne
    @JsonIgnore
    private Task task;

    public Comment(String message, User author, Task task) {
        this.message = message;
        this.author = author;
        this.task = task;
    }
}
