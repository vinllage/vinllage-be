package xyz.vinllage.recycle.entities;

import jakarta.persistence.*;
import lombok.Data;
import xyz.vinllage.global.entities.BaseEntity;
import xyz.vinllage.member.entities.Member;

@Data
@Entity
public class RecycleResult extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rid; // 기본키 rid

	/** 비회원 허용: nullable=true */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@Column(precision = 5, nullable = false) // 길이 5자리, 소수점 4자리 까지
	private Double topConfidence; // 최고 예측 신뢰도 (0~1)

	@Transient
	private String topJson; // 상위 예측 결과 JSON 문자열
}
