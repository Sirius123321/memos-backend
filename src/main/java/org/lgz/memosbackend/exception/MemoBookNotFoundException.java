package org.lgz.memosbackend.exception;

public class MemoBookNotFoundException  extends Exception{
    public MemoBookNotFoundException(String message) {
        super(message);
    }
    public MemoBookNotFoundException(Long id) {
        super("Memo book with id " + id + " not found");
    }
}
