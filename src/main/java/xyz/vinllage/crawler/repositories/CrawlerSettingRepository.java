package xyz.vinllage.crawler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.vinllage.crawler.entities.CrawlerSetting;

public interface CrawlerSettingRepository  extends JpaRepository<CrawlerSetting, Long> {
}
