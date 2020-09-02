package cn.aminorz.jigsaw.exception;

public class SimpleDirectionException extends Exception {
    public SimpleDirectionException(int value) {
        super("The int value " + value + " is not proper for any simple direction");
    }
}
