package xyz.vinllage.board.exceptions;

import xyz.vinllage.global.exceptions.BadRequestException;

public class CommentNotFoundException extends BadRequestException {
    public CommentNotFoundException() {
        super("NotFound.comment");
        setErrorCode(true);
    }
}