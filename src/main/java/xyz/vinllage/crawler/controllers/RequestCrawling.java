package xyz.vinllage.crawler.controllers;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "크롤링 요청 정보")
public class RequestCrawling {
    private String url;
    private List<String> keywords;
    private String linkSelector;
    private String titleSelector;
    private String dateSelector;
    private String contentSelector;
    private String urlPrefix;
}
