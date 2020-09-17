package cn.aminorz.jigsaw.util.factor;

public abstract class LimitedFactorItem implements ILitmitedFactorItem {
    protected int count=0;
    protected float factor=1f;
    protected int limit=0;
    public LimitedFactorItem(int count) {
        this.limit = count;
    }

    public int getLimit() {
        return limit;
    }

    public int getCount() {
        return count;
    }


    public void resetFactor() {
        this.factor = 1f;
    }

    public void resetLimit() {
        this.count = 0;
    }

    public void resetItem() {
        resetFactor();
        resetLimit();
    }

    public abstract void stepLimit();

    public abstract void stepFactor();
    public void step()
    {
        stepLimit();
        stepFactor();
    }
}