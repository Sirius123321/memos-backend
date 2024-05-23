package org.lgz.memosbackend.exception;

/**
 * 备忘录没找到异常，信息
 */
public class MemoNotFoundException extends Exception{
    public MemoNotFoundException(String message) {
        super(message);
    }
    public MemoNotFoundException(Long id) {
        super("Memo with id " + id + " not found");
    }
}
