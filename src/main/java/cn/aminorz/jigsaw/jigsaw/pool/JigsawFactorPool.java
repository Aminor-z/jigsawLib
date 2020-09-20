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
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class JigsawFactorPool {

    private HashMap<JigsawPatternType, IFactor> data = new HashMap<>();
    private Consumer<JigsawPatternType> factorConsumer;
    private Consumer<JigsawPatternType> defaultFactorConsumer = jigsawPatternType ->
    {
        IFactor t = data.get(jigsawPatternType);
        if (t != null) {
            t.step();
        }
    };

    public JigsawFactorPool() {
        factorConsumer = defaultFactorConsumer;
    }

    public <T extends IFactor> JigsawFactorPool register(Supplier<IJigsawPattern> iJigsawPattern, T factorItem) {
        data.put(iJigsawPattern.get().getJigsawPatternType(), factorItem);
        return this;
    }

    public JigsawFactorPool setFactorConsumer(Consumer<JigsawPatternType> factorConsumer) {
        this.factorConsumer = factorConsumer;
        return this;
    }

    public Pair<JigsawSectionPos, IJigsawPattern> getRandomObj(List<WeightRandomItem<Pair<JigsawSectionPos, IJigsawPattern>>> validItem) {
        WeightedRandom<Pair<JigsawSectionPos, IJigsawPattern>> weightedRandom = new WeightedRandom<>();
        for (WeightRandomItem<Pair<JigsawSectionPos, IJigsawPattern>> pairWeightRandomItem : validItem) {
            IFactor t = data.get(pairWeightRandomItem.getItem().getValue().getJigsawPatternType());
            if (t != null) {
                if (t.get() > 1e-9)
                    weightedRandom.add(pairWeightRandomItem.getItem(), pairWeightRandomItem.getWeight() * t.get());
            } else weightedRandom.add(pairWeightRandomItem);
        }
        Pair<JigsawSectionPos, IJigsawPattern> result=weightedRandom.getRandomObj();
        factorConsumer.accept(result.getValue().getJigsawPatternType());
        return result;
    }
}
