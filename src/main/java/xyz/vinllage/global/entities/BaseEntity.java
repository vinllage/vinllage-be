package xyz.vinllage.global.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*
* 모든 엔티티 클래스가 공통으로 갖는 기본 필드를 모아둔 상위 클래스
* 즉 생성, 수정, 삭제 시간 정보를 자동으로 관리
*/
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false) // 저장 후엔 수정 금지
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt; // 생성 시각

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedAt; // 수정 시각

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deletedAt; // 삭제 시각 (소프트 삭제(삭제 후 복구 가능))
}
