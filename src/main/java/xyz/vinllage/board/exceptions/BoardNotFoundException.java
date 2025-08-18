package xyz.vinllage.board.exceptions;

import xyz.vinllage.global.exceptions.NotFoundException;

public class BoardNotFoundException extends NotFoundException {
    public BoardNotFoundException() {
        super("NotFound.board");
        setErrorCode(true);
    }
}