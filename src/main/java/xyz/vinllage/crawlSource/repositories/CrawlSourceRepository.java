package xyz.vinllage.crawlSource.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.vinllage.crawlSource.entities.CrawlSource;

public interface CrawlSourceRepository extends JpaRepository<CrawlSource, Long> {

}
