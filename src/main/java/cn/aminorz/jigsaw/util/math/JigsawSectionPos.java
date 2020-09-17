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
    public static  JigsawSectionPos ZERO = new JigsawSectionPos(0, 0, 0);
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

    public JigsawSectionPos add(JigsawSectionPos jigsawSectionPos) {
        return new JigsawSectionPos(this.x + jigsawSectionPos.x, this.y + jigsawSectionPos.y, this.z + jigsawSectionPos.z);
    }

    public JigsawSectionPos subtract(JigsawSectionPos jigsawSectionPos) {
        return new JigsawSectionPos(this.x - jigsawSectionPos.x, this.y - jigsawSectionPos.y, this.z - jigsawSectionPos.z);
    }

    public JigsawSectionPos getEast() {
        return new JigsawSectionPos(this.x + 1, this.y, this.z);
    }

    public  JigsawSectionPos getSouth() {
        return new JigsawSectionPos(this.x, this.y, this.z + 1);
    }


    public  JigsawSectionPos getWest() {
        return new JigsawSectionPos(this.x - 1, this.y, this.z);
    }


    public  JigsawSectionPos getNorth() {
        return new JigsawSectionPos(this.x, this.y, this.z - 1);
    }


    public  JigsawSectionPos getUp() {
        return new JigsawSectionPos(this.x, this.y + 1, this.z);
    }

    public  JigsawSectionPos getDown() {
        return new JigsawSectionPos(this.x, this.y - 1, this.z);
    }

    public  JigsawSectionPos add(SimpleDirection simpleDirection) {
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
    public JigsawSectionPos subtract(SimpleDirection simpleDirection) {
        switch (simpleDirection) {
            case NONE:
                return this.clone();
            case EAST:
                return getWest();
            case SOUTH:
                return getNorth();
            case WEST:
                return getEast();
            case NORTH:
                return getSouth();
            case UP:
                return getDown();
            case DOWN:
                return getUp();
            default:
                return ZERO;
        }    }
    public  JigsawSectionPos getMinus() {
        return new JigsawSectionPos(-this.x, -this.y, -this.z);
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

    @Override
    public String toString() {
        return "JigsawSectionPos["+x+","+y+","+z+"]";
    }
}
