package cn.aminorz.jigsaw.util.factor;

public interface ILimitedItem extends IItemStep {

    int getCount();

    void stepLimit();


    void resetLimit();
}
