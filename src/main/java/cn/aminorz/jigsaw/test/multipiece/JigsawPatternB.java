package cn.aminorz.jigsaw.test.multipiece;

import cn.aminorz.jigsaw.jigsaw.IJigsawPattern;
import cn.aminorz.jigsaw.jigsaw.JigsawSummonNode;
import cn.aminorz.jigsaw.jigsaw.JigsawSummonNodeSocket;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawOccupiedSectionPool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodeSocketPool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodesPool;
import cn.aminorz.jigsaw.test.piece.JigsawPieceB;
import cn.aminorz.jigsaw.test.piece.JigsawPieceB_socketed_w;
import cn.aminorz.jigsaw.util.math.JigsawSectionPos;
import cn.aminorz.jigsaw.util.math.SimpleDirection;

public class JigsawPatternB implements IJigsawPattern {
    private static final JigsawSummonNodesPool summonNodes = new JigsawSummonNodesPool();
    private static final JigsawOccupiedSectionPool occupiedSectionPool = new JigsawOccupiedSectionPool();
    private static final JigsawSummonNodeSocketPool summonNodeSocketPool = new JigsawSummonNodeSocketPool();

    static {
        summonNodes.register(
                () -> new JigsawSummonNode(
                        new JigsawSectionPos(0, 0, 0),
                        SimpleDirection.SOUTH,
                        "B-summon-s",
                        10)
        );
    }

    static {
        //B
        occupiedSectionPool.register(new JigsawSectionPos(0, 0, 0), JigsawPieceB_socketed_w::new);
        occupiedSectionPool.register(new JigsawSectionPos(1, 0, 0), JigsawPieceB::new);
        occupiedSectionPool.register(new JigsawSectionPos(2, 0, 0), JigsawPieceB::new);
    }

    static {
        summonNodeSocketPool.register(
                () -> new JigsawSummonNodeSocket(SimpleDirection.WEST,
                        new JigsawSectionPos(0, 0, 0),
                        "A-summon-e",
                        1, JigsawPatternB::new)
        );
    }

    @Override
    public JigsawOccupiedSectionPool getOccupiedSectionPool() {
        return occupiedSectionPool;
    }

    @Override
    public JigsawSummonNodesPool getSummonNodes() {
        return summonNodes;
    }

    @Override
    public JigsawSummonNodeSocketPool getSummonNodeSocketPool() {
        return summonNodeSocketPool;
    }
}
