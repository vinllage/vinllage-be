package xyz.vinllage.crawler.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import xyz.vinllage.crawler.entities.CrawledData;
import xyz.vinllage.crawler.entities.CrawlerConfig;
import xyz.vinllage.crawler.services.CrawlerConfigService;
import xyz.vinllage.crawler.services.CrawlerSettingService;
import xyz.vinllage.crawler.services.CrawlingService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/crawler")
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name="크롤러 설정 API", description = "크롤러 설정 및 스케줄러 관리 기능 제공")
public class CrawlerAdminController {
    private final CrawlerConfigService configService;
    private final CrawlerSettingService settingService;
    private final CrawlingService crawlingService;

    @Operation(summary = "크롤러 사이트 설정 목록")
    @GetMapping("/configs")
    public List<CrawlerConfig> configs() {
        return configService.gets();
    }

    @Operation(summary = "크롤러 사이트 설정 저장")
    @PostMapping("/configs")
    public void save(@RequestBody List<RequestCrawling> forms) {
        configService.save(forms);
    }

    @Operation(summary = "크롤링 테스트")
    @PostMapping("/test")
    public List<CrawledData> test(@RequestBody RequestCrawling form) {
        return crawlingService.process(form);
    }

    @Operation(summary = "스케줄러 상태 조회")
    @GetMapping("/scheduler")
    public Map<String, Boolean> scheduler() {
        return Map.of("enabled", settingService.isSchedulerEnabled());
    }

    @Operation(summary = "스케줄러 상태 변경")
    @PostMapping("/scheduler")
    public Map<String, Boolean> scheduler(@RequestParam("enabled") boolean enabled) {
        settingService.setScheduler(enabled);
        return Map.of("enabled", enabled);
    }
}
