package cn.aminorz.jigsaw.jigsaw.pool;

import java.util.HashMap;
import java.util.function.Supplier;

public class JigsawMapPool<T, K> extends HashMap<T, K> {
    public void register(T t, K k) {
        put(t, k);
    }

    public void register(T t, Supplier<K> k) {
        put(t, k.get());
    }

    public void register(Supplier<T> t, K k) {
        put(t.get(), k);
    }

    public void register(Supplier<T> t, Supplier<K> k) {
        put(t.get(), k.get());
    }
}
