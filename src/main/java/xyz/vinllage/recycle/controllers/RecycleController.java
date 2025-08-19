package xyz.vinllage.recycle.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.vinllage.global.search.ListData;
import xyz.vinllage.recycle.entities.RecycleResult;
import xyz.vinllage.recycle.services.RecycleInfoService;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/api/v1/recycle", "/recycle"})
@Tag(name = "분리수거 API", description = "분리수거 결과 조회 기능 제공")
public class RecycleController {
	private final RecycleInfoService infoService;

	@Operation(summary = "쓰레기 목록 조회", description = "page 기본값 1, limit 기본값 20")
	@GetMapping("/list")
	public ListData<RecycleResult> list(
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int limit
	) {
		return infoService.getList(page, limit);
	}
}
