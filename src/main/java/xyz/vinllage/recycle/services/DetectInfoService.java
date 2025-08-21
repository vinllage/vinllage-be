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

    public DetectedRecycle get() {

        return null;
    }

    public List<DetectedRecycle> getList(String gid) {
        QDetectedRecycle detectedRecycle = QDetectedRecycle.detectedRecycle;
        return queryFactory
                .selectFrom(detectedRecycle)
                .where(detectedRecycle.gid.eq(gid))
                .stream().toList();
    }
}
