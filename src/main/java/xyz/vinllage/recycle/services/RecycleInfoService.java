package xyz.vinllage.recycle.services;

import com.querydsl.core.BooleanBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xyz.vinllage.global.search.ListData;
import xyz.vinllage.global.search.Pagination;
import xyz.vinllage.recycle.entities.QRecycleResult;
import xyz.vinllage.recycle.entities.RecycleResult;
import xyz.vinllage.recycle.repositories.RecycleRepository;

import static org.springframework.data.domain.Sort.Order.desc;

@Lazy
@Service
@RequiredArgsConstructor
public class RecycleInfoService {

	private final RecycleRepository repository;
	private final HttpServletRequest request;

	// 분리수거 결과 조회
	public ListData<RecycleResult> getList(int page, int limit) {
		page = Math.max(page, 1);
		limit = limit < 1 ? 20 : limit;

		Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(desc("rid")));

		QRecycleResult recycleResult = QRecycleResult.recycleResult;
		BooleanBuilder where = new BooleanBuilder().and(recycleResult.deletedAt.isNull());

		Page<RecycleResult> data = repository.findAll(where, pageable);

		Pagination pagination = new Pagination(page, (int) data.getTotalElements(), 10, limit, request);
		return new ListData<>(data.getContent(), pagination);
	}
}
