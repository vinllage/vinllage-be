package xyz.vinllage.member.entities;

import xyz.vinllage.global.entities.BaseEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Member extends BaseEntity implements Serializable {
    private Long seq;

    private String email;

    private String password;

    private String name;

    private String mobile;

    private boolean termsAgree;

    private boolean locked;

    private LocalDateTime expired;
}
