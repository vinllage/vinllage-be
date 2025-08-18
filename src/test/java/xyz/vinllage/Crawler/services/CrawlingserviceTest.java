package xyz.vinllage.Crawler.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.vinllage.crawler.controllers.RequestCrawling;
import xyz.vinllage.crawler.services.CrawlingService;

import java.util.List;

@SpringBootTest
public class CrawlingserviceTest {
    @Autowired
    private CrawlingService service;

    @Test
    void crawlingTest() {
        RequestCrawling form = new RequestCrawling();
        form.setUrl("https://www.me.go.kr/mamo/web/index.do?menuId=631");
        form.setKeywords(List.of("환경", "수도권"));
        form.setLinkSelector(".brd_body .title a");
        form.setTitleSelector(".board_view .board_tit");
        form.setDateSelector(".board_view .createDate");
        form.setUrlPrefix("https://www.me.go.kr");
        service.process(form);
    }
}
