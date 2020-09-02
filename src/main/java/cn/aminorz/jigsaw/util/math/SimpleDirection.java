package cn.aminorz.jigsaw.util.math;

import cn.aminorz.jigsaw.exception.SimpleDirectionException;
import jdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
public enum SimpleDirection {
    NONE(0b000000),
    EAST(0b000001),
    SOUTH(0b000010),
    WEST(0b000100),
    NORTH(0b001000),
    UP(0b010000),
    DOWN(0b100000),
    ;
    private int value;

    SimpleDirection(int i) {
        value = i;
    }

    @SuppressWarnings({"finally", "ReturnInsideFinallyBlock"})
    public static SimpleDirection get(int i) {
        switch (i) {
            case 0b000000:
                return NONE;
            case 0b000001:
                return EAST;
            case 0b000010:
                return SOUTH;
            case 0b000100:
                return WEST;
            case 0b001000:
                return NORTH;
            case 0b010000:
                return UP;
            case 0b100000:
                return DOWN;
            default:
                try {
                    throw new SimpleDirectionException(i);
                } catch (SimpleDirectionException e) {
                    e.printStackTrace();
                } finally {
                    return NONE;
                }
        }
    }

    public final int getValue() {
        return this.value;
    }

    public final SimpleDirection getOpposite() {
        switch (this) {
            case EAST:
                return WEST;
            case SOUTH:
                return NORTH;
            case WEST:
                return EAST;
            case NORTH:
                return SOUTH;
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            default:
                return NONE;
        }
    }

    public final int toInt() {
        return value;
    }

}