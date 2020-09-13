package cn.aminorz.jigsaw.jigsaw;

import cn.aminorz.jigsaw.jigsaw.JigsawSummonNodeSocketType;
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
    private final boolean isTerminator = true;
    public JigsawSummonNode(JigsawSectionPos nodeSectionPos, SimpleDirection simpleDirection, String jigsawSummonNodeType) {
        this.simpleDirection = simpleDirection;
        this.jigsawSummonNodeType = new JigsawSummonNodeSocketType(jigsawSummonNodeType);
        this.nodeSectionPos = nodeSectionPos;
    }

    public boolean isTerminator() {
        return isTerminator;
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


    @Override
    public int hashCode() {
        return jigsawSummonNodeType.hashCode() ^ (simpleDirection.hashCode()<<30);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof cn.aminorz.jigsaw.jigsaw.JigsawSummonNode) {
            cn.aminorz.jigsaw.jigsaw.JigsawSummonNode jigsawSummonNode = (cn.aminorz.jigsaw.jigsaw.JigsawSummonNode) obj;
            return this.jigsawSummonNodeType.equals(jigsawSummonNode.getJigsawSummonNodeType())
                    && this.simpleDirection.equals(jigsawSummonNode.getSimpleDirection());
        } else {
            return false;
        }
    }
}
