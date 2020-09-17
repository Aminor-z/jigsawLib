package cn.aminorz.jigsaw.util.factor;

public class DefaultLimiedItem extends LimitedItem {
    public DefaultLimiedItem(int count) {
        super(count);
    }

    @Override
    public void stepLimit() {
        ++super.count;
    }

    @Override
    public Float get() {
        float t = super.count <= super.limit ? 1f : 0f;
        step();
        return t;
    }
}
