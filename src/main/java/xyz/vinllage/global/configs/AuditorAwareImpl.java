//package xyz.vinllage.global.configs;
//
//import lombok.RequiredArgsConstructor;
//import org.aspectj.weaver.MemberUtils;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.stereotype.Component;
//import xyz.vinllage.member.libs.MemberUtil;
//
//import java.util.Optional;
//
//@Component
//@RequiredArgsConstructor
//public class AuditorAwareImpl implements AuditorAware<String> {
//
//    private final MemberUtil memberUtil;
//
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        return Optional.ofNullable(memberUtil)
//    }
//}
