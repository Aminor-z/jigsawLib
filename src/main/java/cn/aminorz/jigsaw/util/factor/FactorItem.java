package cn.aminorz.jigsaw.util.factor;

public abstract class FactorItem implements IFactorItem{
    protected float factor=1f;
    public FactorItem() {
    }
    public float getFactor() {
        return factor;
    }

    public void setFactor(float factor) {
        this.factor = factor;
    }

    public void resetFactor() {
        this.factor = 1f;
    }

    public abstract void stepFactor();
    public void step()
    {
        stepFactor();
    }
}
