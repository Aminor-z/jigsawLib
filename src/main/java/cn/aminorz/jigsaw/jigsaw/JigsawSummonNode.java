package cn.aminorz.jigsaw.jigsaw;

import cn.aminorz.jigsaw.util.math.JigsawSectionPos;
import cn.aminorz.jigsaw.util.math.SimpleDirection;
import jdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
public class JigsawSummonNode {
    private final SimpleDirection simpleDirection;
    private final JigsawSummonNodeSocketType jigsawSummonNodeType;
    private final JigsawSectionPos nodeSectionPos;
    /**
     * <p>false: the summon node hope it will not be the last of a summon turn.</p>
     * <p>true: the summon node never mind that.</p>
     */
    private final boolean isTerminator=true;
    private final int weight;

    public boolean isTerminator() {
        return isTerminator;
    }

    public JigsawSummonNode(JigsawSectionPos nodeSectionPos, SimpleDirection simpleDirection, String jigsawSummonNodeType, int weight) {
        this.simpleDirection = simpleDirection;
        this.jigsawSummonNodeType = new JigsawSummonNodeSocketType(jigsawSummonNodeType);
        this.nodeSectionPos = nodeSectionPos;
        this.weight = weight;
    }

    public JigsawSectionPos getNodeSectionPos() {
        return nodeSectionPos;
    }

    public SimpleDirection getSimpleDirection() {
        return simpleDirection;
    }

    public JigsawSummonNodeSocketType getJigsawSummonNodeType() {
        return jigsawSummonNodeType;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int hashCode() {
        return jigsawSummonNodeType.hashCode() ^ simpleDirection.hashCode() + weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof JigsawSummonNode) {
            JigsawSummonNode jigsawSummonNode = (JigsawSummonNode) obj;
            return this.jigsawSummonNodeType.equals(jigsawSummonNode.getJigsawSummonNodeType())
                    && this.simpleDirection.equals(jigsawSummonNode.getSimpleDirection()) &&
                    this.weight == jigsawSummonNode.getWeight();
        } else {
            return false;
        }
    }
}
