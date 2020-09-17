package cn.aminorz.jigsaw.util.factor;

public interface IFactorItem extends IItemStep, IFactor {
    float getFactor();

    void stepFactor();

    void resetFactor();
}
