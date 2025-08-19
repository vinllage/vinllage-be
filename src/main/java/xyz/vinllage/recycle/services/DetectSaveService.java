package xyz.vinllage.recycle.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import xyz.vinllage.file.controllers.RequestUpload;
import xyz.vinllage.file.services.FileUploadService;
import xyz.vinllage.member.entities.Member;
import xyz.vinllage.recycle.entities.DetectedRecycle;
import xyz.vinllage.recycle.repositories.DetectedRecycleRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DetectSaveService {

    private final FileUploadService fileUploadService;
    private final DetectedRecycleRepository recycleFileDataRepository;

    @Transactional
    public void process(List<MultipartFile> files, String itemsJson, Member member) {
        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("업로드할 파일이 없습니다.");
        }
        if (!StringUtils.hasText(itemsJson)) {
            throw new IllegalArgumentException("items(JSON) 값이 비어 있습니다.");
        }

        // 1) 파일 업로드: 동일 gid로 그룹화
        String gid = UUID.randomUUID().toString();
        RequestUpload upload = new RequestUpload();
        upload.setGid(gid);
        upload.setFiles(files.toArray(MultipartFile[]::new));
        upload.setSingle(false);     // 여러 파일
        upload.setImageOnly(true);
        upload.setLocation("detect");

        // 2) RecycleFileData 생성
        DetectedRecycle entity = new DetectedRecycle();
        entity.setGid(gid);
        entity.setMember(member);
        entity.setData(itemsJson); // 원본 JSON 문자열 그대로 저장

        recycleFileDataRepository.saveAndFlush(entity);

        // 3) 파일 그룹 작업 완료 처리
        fileUploadService.processDone(gid);
    }
}