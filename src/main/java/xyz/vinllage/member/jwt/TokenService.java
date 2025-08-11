package xyz.vinllage.member.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import xyz.vinllage.global.libs.Utils;
import xyz.vinllage.member.services.MemberInfoService;

import java.security.Key;

@Lazy
@Service
@EnableConfigurationProperties(JwtProperties.class)
public class TokenService {
    private final JwtProperties properties;
    private final MemberInfoService infoService;
    @Autowired
    private Utils utils;

    private Key key;

    public TokenService(JwtProperties properties, MemberInfoService infoService) {
        this.properties = properties;
        this.infoService = infoService;

        byte[] keyBytes = Decoders.BASE64URL.decode(properties.getSecret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
}
