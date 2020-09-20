package cn.aminorz.jigsaw.jigsaw.pool;

import cn.aminorz.jigsaw.jigsaw.JigsawSummonNode;
import cn.aminorz.jigsaw.jigsaw.JigsawSummonNodeSocketType;
import cn.aminorz.jigsaw.util.math.JigsawSectionPos;
import cn.aminorz.jigsaw.util.math.SimpleDirection;

import java.util.HashSet;
import java.util.function.Supplier;

public class JigsawSummonNodePool extends HashSet<JigsawSummonNode> {
    public void register(Supplier<JigsawSummonNode> supplier) {
        add(supplier.get());
    }

    public void register(JigsawSummonNode jigsawSummonNode) {
        add(jigsawSummonNode);
    }
    public void register(int x1, int y1, int z1, SimpleDirection simpleDirection, JigsawSummonNodeSocketType jigsawSummonNodeSocketType) {
        add(new JigsawSummonNode(new JigsawSectionPos(x1,y1,z1),simpleDirection,jigsawSummonNodeSocketType));
    }
    public void register(JigsawSectionPos jigsawSectionPos,SimpleDirection simpleDirection, JigsawSummonNodeSocketType jigsawSummonNodeSocketType) {
        add(new JigsawSummonNode(jigsawSectionPos,simpleDirection,jigsawSummonNodeSocketType));
    }
}
