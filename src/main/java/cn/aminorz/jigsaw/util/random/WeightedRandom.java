package cn.aminorz.jigsaw.util.random;


import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

//TODO:Wait for optimize...
public class WeightedRandom<T> extends TreeMap<Float, T> {
    private Random random = new Random();
    private Float weightSum = 0f;

    public WeightedRandom() {
    }

    public WeightedRandom(List<WeightRandomItem<T>> weightRandomItems) {
        if (weightRandomItems == null) return;
        for (WeightRandomItem<T> weightRandomItem : weightRandomItems) {
            weightSum += weightRandomItem.getWeight();
            put(weightSum, weightRandomItem.getItem());
        }
    }

    public T getRandomObj() {
        Map.Entry<Float, T> t = ceilingEntry(random.nextFloat() * weightSum);
        return t == null ? null : t.getValue();
    }

    public void add(List<WeightRandomItem<T>> weightRandomItems) {
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
