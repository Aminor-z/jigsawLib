package cn.aminorz.jigsaw.util;


import cn.aminorz.jigsaw.util.WeightRandomItem;

import java.util.HashMap;
import java.util.LinkedList;

public class WeightedRandom<T> extends HashMap<Integer, T> {
    private Integer weightSum = 0;

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
        int random = (int) (Math.random() * weightSum);
        for (Entry<Integer, T> entry : entrySet()) {
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
    public void add(WeightRandomItem<T> weightRandomItem)
    {
        if (weightRandomItem == null) return;
        weightSum += weightRandomItem.getWeight();
        put(weightSum, weightRandomItem.getItem());
    }
}
