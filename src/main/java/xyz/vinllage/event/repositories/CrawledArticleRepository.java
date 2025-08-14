package xyz.vinllage.event.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.vinllage.event.entities.CrawledArticle;

import java.util.List;

public interface CrawledArticleRepository extends JpaRepository<CrawledArticle, Long> {
    List<CrawledArticle> findBySiteNameOrderByPublishedAtDesc(String siteName);
}
