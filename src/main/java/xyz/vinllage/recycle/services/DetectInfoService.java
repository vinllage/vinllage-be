package xyz.vinllage.recycle.services;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.vinllage.recycle.entities.DetectedRecycle;
import xyz.vinllage.recycle.entities.QDetectedRecycle;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetectInfoService {

    private final JPAQueryFactory queryFactory;

    public List<DetectedRecycle> getList() {
        QDetectedRecycle detectedRecycle = QDetectedRecycle.detectedRecycle;
        return queryFactory
                .selectFrom(detectedRecycle)
                .fetch();
    }
}
