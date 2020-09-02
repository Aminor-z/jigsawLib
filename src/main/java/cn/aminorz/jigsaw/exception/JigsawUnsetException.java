package cn.aminorz.jigsaw.exception;

public class JigsawUnsetException extends Exception {
    public JigsawUnsetException(Class<?> cls, String name) {
        super("In " + cls.getSimpleName() + ": The setting of [" + name + "] is null. Please set to a proper value before use.");
    }
}
