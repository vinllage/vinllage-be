package xyz.vinllage.member.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.vinllage.member.constants.Authority;
import xyz.vinllage.member.controllers.RequestJoin;
import xyz.vinllage.member.entities.Member;
import xyz.vinllage.member.repositories.MemberRepository;

import java.time.LocalDateTime;

@Lazy
@Service
@RequiredArgsConstructor
public class JoinService {
    private final MemberRepository repository;
    private final PasswordEncoder encoder;
    private final ModelMapper mapper;

    public void process(RequestJoin form) {
        Member member = mapper.map(form, Member.class);

        String password = form.getPassword();
        if (StringUtils.hasText(password)) {
            member.setPassword(encoder.encode(password));
        }

        // member 객체의 비밀번호 변경 시각(또는 자격 증명 변경 시각)을 현재 시간으로 설정
        member.setCredentialChangedAt(LocalDateTime.now());
        member.setAuthority(Authority.MEMBER);


        /**
         * save()
         * → 엔티티를 영속성 컨텍스트에 저장하고, 변경사항을 기록합니다.
         * (JPA는 즉시 DB에 쿼리를 날리지 않고, 보통 트랜잭션이 커밋될 때 한 번에 반영)
         *
         * flush()
         * → 지금까지 쌓여 있던 변경 사항들을 즉시 DB에 반영
         */
        repository.saveAndFlush(member);
    }
}
