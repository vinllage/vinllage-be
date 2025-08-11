package xyz.vinllage.member.exceptions;

import xyz.vinllage.global.exceptions.NotFoundException;

public class MemberNotFoundException extends NotFoundException {
    public MemberNotFoundException(String message) {
        super("NotFound.member");
        setErrorCode(true);
    }
}
