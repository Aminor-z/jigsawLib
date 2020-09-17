package cn.aminorz.jigsaw.jigsaw.pool;

import cn.aminorz.jigsaw.jigsaw.IJigsawPattern;
import cn.aminorz.jigsaw.jigsaw.JigsawPatternType;
import cn.aminorz.jigsaw.util.factor.IFactor;
import cn.aminorz.jigsaw.util.math.JigsawSectionPos;
import cn.aminorz.jigsaw.util.random.WeightRandomItem;
import cn.aminorz.jigsaw.util.random.WeightedRandom;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.Supplier;

public class JigsawFactorPool {

    private HashMap<JigsawPatternType, IFactor> data = new HashMap<>();

    public <T extends IFactor> void register(Supplier<IJigsawPattern> iJigsawPattern, T factorItem) {
        data.put(iJigsawPattern.get().getJigsawPatternType(), factorItem);
    }

    public Pair<JigsawSectionPos, IJigsawPattern> getRandomObj(LinkedList<WeightRandomItem<Pair<JigsawSectionPos, IJigsawPattern>>> validItem) {
        WeightedRandom<Pair<JigsawSectionPos, IJigsawPattern>> weightedRandom = new WeightedRandom<>();
        for (WeightRandomItem<Pair<JigsawSectionPos, IJigsawPattern>> pairWeightRandomItem : validItem) {
            IFactor t = data.get(pairWeightRandomItem.getItem().getValue().getJigsawPatternType());
            if (t != null) {
                if (t.get() > 0.01f)
                    weightedRandom.add(pairWeightRandomItem.getItem(), pairWeightRandomItem.getWeight() * t.get());
            } else weightedRandom.add(pairWeightRandomItem);
        }
        return weightedRandom.getRandomObj();
    }
}
