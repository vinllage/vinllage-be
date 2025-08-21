package xyz.vinllage.recycle.services;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.vinllage.file.entities.FileInfo;
import xyz.vinllage.file.services.FileDownloadService;
import xyz.vinllage.file.services.FileInfoService;
import xyz.vinllage.recycle.entities.DetectedRecycle;
import xyz.vinllage.recycle.entities.QDetectedRecycle;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetectInfoService {

    private final JPAQueryFactory queryFactory;
    private final FileDownloadService fileDownloadService;
    private final FileInfoService fileInfoService;

    public List<DetectedRecycle> getList() {
        QDetectedRecycle detectedRecycle = QDetectedRecycle.detectedRecycle;
        return queryFactory
                .selectFrom(detectedRecycle)
                .fetch();
    }

    public void downloadAllDetectedImages() {
        List<DetectedRecycle> detectedRecycles = getList();

        // 감지된 이미지 파일 모두 조회
        List<FileInfo> images = new ArrayList<>();
        for (DetectedRecycle r : detectedRecycles) {
            images.addAll(fileInfoService.getList(r.getGid()));
        }

        // 감지된 이미지 파일 모두 다운로드
        for (FileInfo f : images) {
            fileDownloadService.process(f.getSeq());
        }
    }
}
