package xyz.vinllage.board.exceptions;

import xyz.vinllage.global.exceptions.BadRequestException;

public class BoardDataNotFoundException extends BadRequestException {
    public BoardDataNotFoundException() {
        super("NotFound.boardData");
        setErrorCode(true);
    }
}