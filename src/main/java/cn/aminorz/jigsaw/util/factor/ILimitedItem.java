package cn.aminorz.jigsaw.util.factor;

public interface ILimitedItem extends IFactor {

    int getCount();

    void stepLimit();


    void resetLimit();
}
