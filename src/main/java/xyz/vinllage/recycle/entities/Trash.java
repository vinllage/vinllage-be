package xyz.vinllage.recycle.entities;

import jakarta.persistence.*;
import lombok.Data;
import xyz.vinllage.recycle.constants.TrashState;

@Data
@Entity
public class Trash {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rid; // 기본키 rid

	@Column(nullable = false)
	private Long x; // 웹캠 속 x 좌표

	@Column(nullable = false)
	private Long y; // 웹캠 속 y 좌표

	@Enumerated(EnumType.STRING) // 쓰레기 종류 enum값 받기
	private TrashState trashState;
}
