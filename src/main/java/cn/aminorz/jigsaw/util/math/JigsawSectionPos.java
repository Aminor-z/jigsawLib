package cn.aminorz.jigsaw.util.math;

import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * <p>Default size 8x8x8</p>
 * <p>If you want to change the size, please remember to check the function {@link JigsawSectionPos#hashCode()}.</p>
 * <p>You should consider the hash collisions</p>
 *
 * @see JigsawSectionPos#hashCode()
 */
@Immutable
public class JigsawSectionPos {
    public static final JigsawSectionPos ZERO = new JigsawSectionPos(0, 0, 0);
    public final int x;
    public final int y;
    public final int z;

    public JigsawSectionPos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }

    public final int getZ() {
        return z;
    }

    public final JigsawSectionPos add(JigsawSectionPos jigsawSectionPos) {
        return new JigsawSectionPos(this.x + jigsawSectionPos.x, this.y + jigsawSectionPos.y, this.z + jigsawSectionPos.z);
    }

    public final JigsawSectionPos minus(JigsawSectionPos jigsawSectionPos) {
        return new JigsawSectionPos(this.x - jigsawSectionPos.x, this.y - jigsawSectionPos.y, this.z - jigsawSectionPos.z);
    }

    public final JigsawSectionPos getEast() {
        return new JigsawSectionPos(this.x + 1, this.y, this.z);
    }

    public final JigsawSectionPos getSouth() {
        return new JigsawSectionPos(this.x, this.y, this.z + 1);
    }


    public final JigsawSectionPos getWest() {
        return new JigsawSectionPos(this.x - 1, this.y, this.z);
    }


    public final JigsawSectionPos getNorth() {
        return new JigsawSectionPos(this.x, this.y, this.z - 1);
    }


    public final JigsawSectionPos getUp() {
        return new JigsawSectionPos(this.x, this.y + 1, this.z);
    }

    public final JigsawSectionPos getDown() {
        return new JigsawSectionPos(this.x, this.y - 1, this.z);
    }

    public final JigsawSectionPos add(SimpleDirection simpleDirection) {
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

    public final JigsawSectionPos getMinus() {
        return new JigsawSectionPos(-this.x, -this.y, -this.z);
    }

    //y max=64,x and y max= 4096
    @Override
    public int hashCode() {
        return this.y << 25 + this.x << 13;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof JigsawSectionPos) {
            JigsawSectionPos jigsawSectionPos = (JigsawSectionPos) obj;
            return this.x == jigsawSectionPos.x && this.z == jigsawSectionPos.z && this.y == jigsawSectionPos.y;
        } else {
            return false;
        }
    }

    @Override
    protected JigsawSectionPos clone() {
        try {
            return (JigsawSectionPos) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return new JigsawSectionPos(this.x, this.y, this.z);
        }
    }
}
