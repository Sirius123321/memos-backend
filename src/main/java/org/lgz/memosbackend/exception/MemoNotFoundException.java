package org.lgz.memosbackend.exception;


public class MemoNotFoundException extends Exception{
    public MemoNotFoundException(String message) {
        super(message);
    }
    public MemoNotFoundException(Long id) {
        super("Memo with id " + id + " not found");
    }
}
