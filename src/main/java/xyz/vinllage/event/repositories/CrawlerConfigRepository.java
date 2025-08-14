package xyz.vinllage.event.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.vinllage.event.entities.CrawlerConfig;

import java.util.List;

public interface CrawlerConfigRepository extends JpaRepository<CrawlerConfig, Long> {
    List<CrawlerConfig> findByEnabledTrue();
}
