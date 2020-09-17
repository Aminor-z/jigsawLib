package cn.aminorz.jigsaw.util.factor;

public abstract class LimitedItem implements ILimitedItem {
    protected int count = 0;
    protected int limit = 0;

    public LimitedItem(int limit) {
        this.limit = limit;
    }

    public int getCount() {
        return count;
    }

    public void resetLimit() {
        this.count = 0;
    }

    public abstract void stepLimit();

    public void step() {
        if (this.count < limit) {
            stepLimit();
        }
    }
}
