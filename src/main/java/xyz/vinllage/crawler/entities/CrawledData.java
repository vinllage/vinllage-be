package xyz.vinllage.crawler.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;
import xyz.vinllage.global.entities.BaseEntity;

import java.time.LocalDate;

@Data
@Entity
@Schema(description = "크롤링된 데이터 정보")
public class CrawledData extends BaseEntity {
    @Id
    private Integer hash;

    @Column(length = 500)
    private String link;
    private String title;

    @Lob
    private String content;

    @Lob
    private String image;

    private Boolean html;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

}
