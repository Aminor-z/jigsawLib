package cn.aminorz.jigsaw.util.factor;

public interface IFactorItem extends IFactor {
    float getFactor();

    void stepFactor();

    void resetFactor();
}
