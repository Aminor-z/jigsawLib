package cn.aminorz.jigsaw.util;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class WeightedRandom<T> {
    private final Map<Integer, T> category = new HashMap<>();
    private Integer weightSum = 0;

    public WeightedRandom() {
    }

    public WeightedRandom(LinkedList<WeightRandomItem<T>> weightRandomItems) {
        if (weightRandomItems == null) return;
        for (WeightRandomItem<T> weightRandomItem : weightRandomItems) {
            weightSum += weightRandomItem.getWeight();
            category.put(weightSum, weightRandomItem.getItem());
        }
    }

    public WeightedRandom(Map<T, Integer> map) {
        if (map == null) return;
        for (Map.Entry<T, Integer> tIntegerEntry : map.entrySet()) {
            weightSum += tIntegerEntry.getValue();
            category.put(weightSum, tIntegerEntry.getKey());
        }
    }

    public final Map<Integer, T> getCategory() {
        return category;
    }

    public final T getRandomObj() {
        if (category.size() == 0) return null;
        int random = (int) (Math.random() * weightSum);
        for (Map.Entry<Integer, T> entry : category.entrySet()) {
            if (entry.getKey() >= random) return entry.getValue();
        }
        return null;
    }

    public void add(T obj, Integer weight) {
        weightSum += weight;
        category.put(weightSum, obj);
    }

    public void add(Map<T, Integer> map) {
        if (map == null) return;
        for (Map.Entry<T, Integer> tIntegerEntry : map.entrySet()) {
            weightSum += tIntegerEntry.getValue();
            category.put(weightSum, tIntegerEntry.getKey());
        }
    }

    public void add(WeightedRandom<T> weightedRandom) {
        if (weightedRandom == null) return;
        for (Map.Entry<Integer, T> integerTEntry : weightedRandom.getCategory().entrySet()) {
            weightSum += integerTEntry.getKey();
            this.category.put(weightSum, integerTEntry.getValue());
        }
    }
}
