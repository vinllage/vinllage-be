package xyz.vinllage.recycle.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import xyz.vinllage.file.controllers.RequestUpload;
import xyz.vinllage.file.entities.FileInfo;
import xyz.vinllage.file.services.FileUploadService;
import xyz.vinllage.member.entities.Member;
import xyz.vinllage.member.repositories.MemberRepository;
import xyz.vinllage.recycle.entities.DetectedRecycle;
import xyz.vinllage.recycle.repositories.DetectedRecycleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DetectSaveService {

    private final FileUploadService fileUploadService;
    private final DetectedRecycleRepository recycleFileDataRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public SaveResult save(List<MultipartFile> files, String itemsJson, Long memberId) {
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
        upload.setImageOnly(false);  // 필요시 true
        // upload.setLocation("detect"); // 폴더/카테고리 구분 필요 시 설정

        List<FileInfo> uploaded = fileUploadService.process(upload);

        // 2) member 조회 (선택)
        Member member = null;
        if (memberId != null) {
            member = memberRepository.findById(memberId).orElse(null);
        }

        // 3) 파일당 RecycleFileData 생성
        List<Long> savedIds = new ArrayList<>();
        for (FileInfo f : uploaded) {
            DetectedRecycle entity = new DetectedRecycle();
            entity.setFileSeq(f.getSeq());
            entity.setMember(member);
            entity.setData(itemsJson); // 원본 JSON 문자열 그대로 저장

            recycleFileDataRepository.save(entity);
            savedIds.add(entity.getSeq());
        }

        // 4) 파일 그룹 작업 완료 처리(선택)
        fileUploadService.processDone(gid);

        return new SaveResult(gid, savedIds.size(), savedIds);
    }

    public record SaveResult(String gid, int count, List<Long> ids) {}
}