package cn.aminorz.jigsaw.util.factor;

public class DefaultLimitedFactorItem extends LimitedFactorItem {
    private final float stepFactor;

    public DefaultLimitedFactorItem(int limit, float stepFactor) {
        super(limit);
        this.stepFactor = stepFactor;
    }

    @Override
    public void stepLimit() {
        ++super.count;
    }


    @Override
    public float getFactor() {
        return super.factor;
    }

    @Override
    public void stepFactor() {
        super.factor *= stepFactor;
    }

    @Override
    public Float get() {
        if (super.count < super.limit) {
            return super.factor;
        } else return 0f;
    }
}
