package xyz.vinllage.file.exceptions;

import xyz.vinllage.global.exceptions.NotFoundException;

public class FileNotFoundException extends NotFoundException {
    public FileNotFoundException() {
        super("NotFound.file");
        setErrorCode(true);
    }
}
