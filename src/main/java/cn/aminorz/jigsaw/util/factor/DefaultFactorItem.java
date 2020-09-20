package cn.aminorz.jigsaw.util.factor;

public class DefaultFactorItem extends FactorItem {
    private final float stepFactor;
    public DefaultFactorItem(float stepFactor) {
        this.stepFactor=stepFactor;
    }

    @Override
    public void stepFactor() {
        super.factor*=stepFactor;
    }

    @Override
    public Float get() {
        return super.factor;
    }
}
