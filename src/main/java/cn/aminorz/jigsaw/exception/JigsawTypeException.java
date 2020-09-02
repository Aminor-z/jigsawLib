package cn.aminorz.jigsaw.exception;

public class JigsawTypeException extends Exception {
    public <T> JigsawTypeException(T obj) {
        super(obj.getClass().getSimpleName() + " is not a proper type.");
    }
}
