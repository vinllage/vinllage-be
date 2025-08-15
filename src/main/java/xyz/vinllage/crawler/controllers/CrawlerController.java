package xyz.vinllage.crawler.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.vinllage.crawler.entities.CrawledData;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/crawler")
@Tag(name="Crawler API")
public class CrawlerController {

    private final HttpServletRequest request;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.POST})
    public CrawledData update() {
        String mode = request.getMethod().equalsIgnoreCase("PATCH") ? "update" : "register";

        return null;
    }

}
