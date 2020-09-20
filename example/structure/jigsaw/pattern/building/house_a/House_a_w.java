package com.aminor.civilizatus.world.gen.structure.jigsaw.pattern.building.house_a;

import cn.aminorz.jigsaw.jigsaw.IJigsawPattern;
import cn.aminorz.jigsaw.jigsaw.JigsawPatternType;
import cn.aminorz.jigsaw.jigsaw.JigsawSide;
import cn.aminorz.jigsaw.jigsaw.JigsawSummonNodeSocket;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawOccupiedSectionPool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodeSocketPool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodePool;
import cn.aminorz.jigsaw.util.math.JigsawSectionPos;
import com.aminor.civilizatus.world.gen.structure.jigsaw.piece.building.house_a.House_a_w_piece;

import static cn.aminorz.jigsaw.util.math.SimpleDirection.WEST;
import static com.aminor.civilizatus.world.gen.structure.jigsaw.Public_JigsawSummonNodeSocketType.JSNKT_BUILDING;
import static com.aminor.civilizatus.world.gen.structure.jigsaw.Public_SideType.BUILDING_ENTRY;

public class House_a_w implements IJigsawPattern {
    private static final House_a_w instance = new House_a_w();
    private static final JigsawSummonNodePool summonNodes = new JigsawSummonNodePool();
    private static final JigsawOccupiedSectionPool occupiedSectionPool = new JigsawOccupiedSectionPool();
    private static final JigsawSummonNodeSocketPool summonNodeSocketPool = new JigsawSummonNodeSocketPool();
    private static final JigsawPatternType jigsawPatternType = new JigsawPatternType("house_a");

    static {
        //register summonNodes here
    }

    static {
        //register occupiedSectionPool here
        occupiedSectionPool.fillPlaceHolder(0, 0, 0, 2, 1, 2);
        occupiedSectionPool.put(0, 0, 0, House_a_w_piece::getInstance);
        occupiedSectionPool.putSide(0, 0, 1, new JigsawSide().west(BUILDING_ENTRY));
        occupiedSectionPool.putSide(0, 0, 2, new JigsawSide().west(BUILDING_ENTRY));
    }

    static {
        //register summonNodeSocketPool here
        summonNodeSocketPool.register(2, 0, 0, WEST, JSNKT_BUILDING, 10, House_a_w::getInstance);
        summonNodeSocketPool.register(2, 0, 1, WEST, JSNKT_BUILDING, 10, House_a_w::getInstance);
        summonNodeSocketPool.register(2, 0, 2, WEST, JSNKT_BUILDING, 10, House_a_w::getInstance);
    }

    public static House_a_w getInstance() {
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
