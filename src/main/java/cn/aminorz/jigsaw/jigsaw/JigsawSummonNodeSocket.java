package cn.aminorz.jigsaw.jigsaw;

import cn.aminorz.jigsaw.util.math.JigsawSectionPos;
import cn.aminorz.jigsaw.util.math.SimpleDirection;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.function.Supplier;

@Immutable
public class JigsawSummonNodeSocket {
    private final SimpleDirection simpleDirection;
    private final JigsawSectionPos socketSectionPos;
    private final JigsawSummonNodeSocketType type;
    private final int weight;
    private final IJigsawPattern jigsawPattern;
    private boolean ignoreOccupation = false;

    /**
     * @param jigsawPattern self-jigsawPattern
     */
    public JigsawSummonNodeSocket(SimpleDirection simpleDirection, JigsawSectionPos socketSectionPos, String type, int weight, Supplier<IJigsawPattern> jigsawPattern) {
        this.simpleDirection = simpleDirection;
        this.socketSectionPos = socketSectionPos;
        this.type = new JigsawSummonNodeSocketType(type);
        this.weight = weight;
        this.jigsawPattern = jigsawPattern.get();
    }

    public boolean isIgnoreOccupation() {
        return ignoreOccupation;
    }

    public JigsawSummonNodeSocket setIgnoreOccupation(boolean b) {
        ignoreOccupation = b;
        return this;
    }

    public int getWeight() {
        return weight;
    }

    public JigsawSectionPos getSocketSectionPos() {
        return socketSectionPos;
    }

    public SimpleDirection getSimpleDirection() {
        return simpleDirection;
    }

    public JigsawSummonNodeSocketType getType() {
        return type;
    }

    public IJigsawPattern getJigsawPattern() {
        return jigsawPattern;
    }

    @Override
    public int hashCode() {
        return (socketSectionPos.hashCode() ^ type.hashCode() + 1013904223) ^ simpleDirection.hashCode() ^ jigsawPattern.hashCode() - weight;
    }
}
