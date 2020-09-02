package cn.aminorz.jigsaw.util;

import jdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
public class WeightRandomItem<T> {
    private final T item;
    private final Integer weight;

    public WeightRandomItem(T item, int weight) {
        this.item = item;
        this.weight = weight;
    }

    public final T getItem() {
        return item;
    }

    public final Integer getWeight() {
        return weight;
    }
}
