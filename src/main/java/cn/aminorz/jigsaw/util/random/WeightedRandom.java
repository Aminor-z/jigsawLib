package cn.aminorz.jigsaw.util.random;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
//TODO:Wait for optimize...
public class WeightedRandom<T> extends HashMap<Float, T> {
    private Float weightSum = 0f;

    public WeightedRandom() {
    }

    public WeightedRandom(LinkedList<WeightRandomItem<T>> weightRandomItems) {
        if (weightRandomItems == null) return;
        for (WeightRandomItem<T> weightRandomItem : weightRandomItems) {
            weightSum += weightRandomItem.getWeight();
            put(weightSum, weightRandomItem.getItem());
        }
    }

    public final T getRandomObj() {
        if (size() == 0) return null;
        Float random =  (float)(Math.random() * weightSum);
        for (Map.Entry<Float, T> entry : entrySet()) {
            if (entry.getKey() >= random) return entry.getValue();
        }
        return null;
    }

    public void add(LinkedList<WeightRandomItem<T>> weightRandomItems) {
        if (weightRandomItems == null) return;
        for (WeightRandomItem<T> weightRandomItem : weightRandomItems) {
            weightSum += weightRandomItem.getWeight();
            put(weightSum, weightRandomItem.getItem());
        }
    }

    public void add(WeightRandomItem<T> weightRandomItem) {
        if (weightRandomItem == null) return;
        weightSum += weightRandomItem.getWeight();
        put(weightSum, weightRandomItem.getItem());
    }

    public void add(T item, Float weight) {
        if (item == null) return;
        weightSum += weight;
        put(weightSum, item);
    }
}
