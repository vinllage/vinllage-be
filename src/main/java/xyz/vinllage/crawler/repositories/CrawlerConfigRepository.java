package xyz.vinllage.crawler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.vinllage.crawler.entities.CrawlerConfig;

public interface CrawlerConfigRepository extends JpaRepository<CrawlerConfig, Long> {
}
