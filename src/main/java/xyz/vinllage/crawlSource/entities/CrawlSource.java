package xyz.vinllage.crawlSource.entities;

import jakarta.persistence.*;
import lombok.Data;
import xyz.vinllage.global.entities.BaseEntity;

@Data
@Entity
public class CrawlSource extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 255)
    private String url;
}