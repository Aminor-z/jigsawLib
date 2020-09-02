package cn.aminorz.jigsaw.test.multipiece;

import cn.aminorz.jigsaw.jigsaw.IJigsawPattern;
import cn.aminorz.jigsaw.jigsaw.JigsawSummonNode;
import cn.aminorz.jigsaw.jigsaw.JigsawSummonNodeSocket;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawOccupiedSectionPool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSidePool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodeSocketPool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodesPool;
import cn.aminorz.jigsaw.test.piece.JigsawPieceA;
import cn.aminorz.jigsaw.test.piece.JigsawPieceA_socketed_n;
import cn.aminorz.jigsaw.util.math.JigsawSectionPos;
import cn.aminorz.jigsaw.util.math.SimpleDirection;

public class JigsawPatternA implements IJigsawPattern {
    private static final JigsawSummonNodesPool summonNodes = new JigsawSummonNodesPool();
    private static final JigsawOccupiedSectionPool occupiedSectionPool = new JigsawOccupiedSectionPool();
    private static final JigsawSidePool sidePool = new JigsawSidePool();
    private static final JigsawSummonNodeSocketPool summonNodeSocketPool = new JigsawSummonNodeSocketPool();

    static {
        summonNodes.register(() -> new JigsawSummonNode(
                new JigsawSectionPos(0, 0, 0),
                SimpleDirection.EAST,
                "A-summon-e",
                10)
        );
    }

    static {
        //A
        occupiedSectionPool.register(new JigsawSectionPos(0, 0, 0), JigsawPieceA_socketed_n::new);
        occupiedSectionPool.register(new JigsawSectionPos(0, 0, 1), JigsawPieceA::new);
        occupiedSectionPool.register(new JigsawSectionPos(0, 0, 2), JigsawPieceA::new);
    }

    static {
        sidePool.register(new JigsawSectionPos(0, 0, 0), JigsawPieceA_socketed_n.jigsawSide);
    }

    static {
        summonNodeSocketPool.register(
                new JigsawSummonNodeSocket(
                        SimpleDirection.NORTH,
                        new JigsawSectionPos(0, 0, 0),
                        "B-summon-s",
                        1, JigsawPatternA::new
                )
        );
    }

    @Override
    public JigsawSidePool getSidePool() {
        return sidePool;
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
