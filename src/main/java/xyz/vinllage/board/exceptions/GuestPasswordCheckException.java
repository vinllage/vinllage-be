package xyz.vinllage.board.exceptions;

import xyz.vinllage.global.exceptions.UnAuthorizedException;

public class GuestPasswordCheckException extends UnAuthorizedException {
    public GuestPasswordCheckException() {
        super("Required.guest.password");
        setErrorCode(true);
    }
}
