package xyz.vinllage.event.entities;

import jakarta.persistence.*;
import lombok.Data;
import xyz.vinllage.global.entities.BaseEntity;

@Data
@Entity
public class Event extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String title;

    @Column(length = 1000)
    private String description;
}