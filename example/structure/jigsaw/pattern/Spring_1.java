package com.aminor.civilizatus.world.gen.structure.jigsaw.pattern;

import cn.aminorz.jigsaw.jigsaw.*;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawOccupiedSectionPool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodePool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodeSocketPool;
import cn.aminorz.jigsaw.util.math.JigsawSectionPos;
import com.aminor.civilizatus.world.gen.structure.jigsaw.piece.spring_1.Spring_1_all;

import static cn.aminorz.jigsaw.util.math.SimpleDirection.*;
import static com.aminor.civilizatus.world.gen.structure.jigsaw.Public_JigsawSummonNodeSocketType.JSNKT_ROAD;
import static com.aminor.civilizatus.world.gen.structure.jigsaw.Public_SideType.ROAD;

public class Spring_1 implements IJigsawPattern {
    private static final Spring_1 instance = new Spring_1();
    private static final JigsawSummonNodePool summonNodes = new JigsawSummonNodePool();
    private static final JigsawOccupiedSectionPool occupiedSectionPool = new JigsawOccupiedSectionPool();
    private static final JigsawSummonNodeSocketPool summonNodeSocketPool = new JigsawSummonNodeSocketPool();
    private static final JigsawPatternType jigsawPatternType = new JigsawPatternType("spring_a");

    static {
        //register summonNodes
        summonNodes.register(4, 0, 0, NORTH, JSNKT_ROAD);
        summonNodes.register(0, 0, 4, WEST, JSNKT_ROAD);
        summonNodes.register(4, 0, 8, SOUTH, JSNKT_ROAD);
        summonNodes.register(8, 0, 4, EAST, JSNKT_ROAD);
    }

    static {
        //register occupiedSectionPool here
        occupiedSectionPool.fillPlaceHolder(2, 0, 0,6, 0, 8);
        occupiedSectionPool.fillPlaceHolder(0, 0, 2,8, 0, 6);
        occupiedSectionPool.put(0, 0, 0, Spring_1_all::getInstance);
        occupiedSectionPool.putSide(4, 0, 0, new JigsawSide().north(ROAD));
        occupiedSectionPool.putSide(0, 0, 4, new JigsawSide().west(ROAD));
        occupiedSectionPool.putSide(4, 0, 8, new JigsawSide().south(ROAD));
        occupiedSectionPool.putSide(8, 0, 4, new JigsawSide().east(ROAD));
    }

    static {
        //register summonNodeSocketPool here
        summonNodeSocketPool.register(4, 0, 0, NORTH, JSNKT_ROAD, 1, Spring_1::getInstance);
        summonNodeSocketPool.register(0, 0, 4, WEST, JSNKT_ROAD, 1, Spring_1::getInstance);
        summonNodeSocketPool.register(4, 0, 8, SOUTH, JSNKT_ROAD, 1, Spring_1::getInstance);
        summonNodeSocketPool.register(8, 0, 4, EAST, JSNKT_ROAD, 1, Spring_1::getInstance);
    }

    public static Spring_1 getInstance() {
        return instance;
    }

    @Override
    public JigsawOccupiedSectionPool getOccupiedSectionPool() {
        return occupiedSectionPool;
    }

    @Override
    public JigsawSummonNodePool getSummonNodes() {
        return summonNodes;
    }

    @Override
    public JigsawSummonNodeSocketPool getSummonNodeSocketPool() {
        return summonNodeSocketPool;
    }

    @Override
    public JigsawPatternType getJigsawPatternType() {
        return jigsawPatternType;
    }
}
