package cn.aminorz.jigsaw.util.math;

import cn.aminorz.jigsaw.util.math.SimpleDirection;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * <p>Default size 8x8x8</p>
 * <p>If you want to change the size, please remember to check the function {@link cn.aminorz.jigsaw.util.math.JigsawSectionPos#hashCode()}.</p>
 * <p>You should consider the hash collisions</p>
 *
 * @see cn.aminorz.jigsaw.util.math.JigsawSectionPos#hashCode()
 */
@Immutable
public class JigsawSectionPos {
    public static cn.aminorz.jigsaw.util.math.JigsawSectionPos ZERO = new cn.aminorz.jigsaw.util.math.JigsawSectionPos(0, 0, 0);
    public int x;
    public int y;
    public int z;

    public JigsawSectionPos(int x, int y, int z) {
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

    public cn.aminorz.jigsaw.util.math.JigsawSectionPos add(cn.aminorz.jigsaw.util.math.JigsawSectionPos jigsawSectionPos) {
        return new cn.aminorz.jigsaw.util.math.JigsawSectionPos(this.x + jigsawSectionPos.x, this.y + jigsawSectionPos.y, this.z + jigsawSectionPos.z);
    }

    public cn.aminorz.jigsaw.util.math.JigsawSectionPos subtract(cn.aminorz.jigsaw.util.math.JigsawSectionPos jigsawSectionPos) {
        return new cn.aminorz.jigsaw.util.math.JigsawSectionPos(this.x - jigsawSectionPos.x, this.y - jigsawSectionPos.y, this.z - jigsawSectionPos.z);
    }

    public cn.aminorz.jigsaw.util.math.JigsawSectionPos getEast() {
        return new cn.aminorz.jigsaw.util.math.JigsawSectionPos(this.x + 1, this.y, this.z);
    }

    public cn.aminorz.jigsaw.util.math.JigsawSectionPos getSouth() {
        return new cn.aminorz.jigsaw.util.math.JigsawSectionPos(this.x, this.y, this.z + 1);
    }


    public cn.aminorz.jigsaw.util.math.JigsawSectionPos getWest() {
        return new cn.aminorz.jigsaw.util.math.JigsawSectionPos(this.x - 1, this.y, this.z);
    }


    public cn.aminorz.jigsaw.util.math.JigsawSectionPos getNorth() {
        return new cn.aminorz.jigsaw.util.math.JigsawSectionPos(this.x, this.y, this.z - 1);
    }


    public cn.aminorz.jigsaw.util.math.JigsawSectionPos getUp() {
        return new cn.aminorz.jigsaw.util.math.JigsawSectionPos(this.x, this.y + 1, this.z);
    }

    public cn.aminorz.jigsaw.util.math.JigsawSectionPos getDown() {
        return new cn.aminorz.jigsaw.util.math.JigsawSectionPos(this.x, this.y - 1, this.z);
    }

    public cn.aminorz.jigsaw.util.math.JigsawSectionPos add(SimpleDirection simpleDirection) {
        switch (simpleDirection) {
            case NONE:
                return this.clone();
            case EAST:
                return getEast();
            case SOUTH:
                return getSouth();
            case WEST:
                return getWest();
            case NORTH:
                return getNorth();
            case UP:
                return getUp();
            case DOWN:
                return getDown();
            default:
                return ZERO;
        }
    }

    public cn.aminorz.jigsaw.util.math.JigsawSectionPos getMinus() {
        return new cn.aminorz.jigsaw.util.math.JigsawSectionPos(-this.x, -this.y, -this.z);
    }

    //y max=64,x and y max= 4096
    @Override
    public int hashCode() {
        return this.y << 25 + this.x << 13+z;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof cn.aminorz.jigsaw.util.math.JigsawSectionPos) {
            cn.aminorz.jigsaw.util.math.JigsawSectionPos jigsawSectionPos = (cn.aminorz.jigsaw.util.math.JigsawSectionPos) obj;
            return this.x == jigsawSectionPos.x && this.z == jigsawSectionPos.z && this.y == jigsawSectionPos.y;
        } else {
            return false;
        }
    }

    @Override
    protected cn.aminorz.jigsaw.util.math.JigsawSectionPos clone() {
       return new cn.aminorz.jigsaw.util.math.JigsawSectionPos(this.x, this.y, this.z);
    }
}
