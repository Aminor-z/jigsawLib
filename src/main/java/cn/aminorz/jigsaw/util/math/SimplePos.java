package cn.aminorz.jigsaw.util.math;

import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * for the simple test
 */
@Immutable
public class SimplePos {
    private final int x;
    private final int y;
    private final int z;

    public SimplePos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public int hashCode() {
        return (this.y + this.z * 31) * 31 + this.x;
    }

    @Override
    public String toString() {
        return "("+x+" , "+y+" , "+z+")";
    }
}
