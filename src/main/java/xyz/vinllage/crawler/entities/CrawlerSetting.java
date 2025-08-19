package xyz.vinllage.crawler.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import xyz.vinllage.global.entities.BaseEntity;

@Data
@Entity
@Schema(description = "크롤러 실행 설정")
public class CrawlerSetting extends BaseEntity {
    @Id
    private Long id =1L;
    private boolean scheduler;
}
