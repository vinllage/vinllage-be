package xyz.vinllage.member.exceptions;

import xyz.vinllage.global.exceptions.NotFoundException;

public class MemberNotFoundException extends NotFoundException {
    public MemberNotFoundException() {
        super("NotFound.member");
        setErrorCode(true);
    }
}
