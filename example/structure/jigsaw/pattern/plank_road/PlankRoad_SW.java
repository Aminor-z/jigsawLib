package com.aminor.civilizatus.world.gen.structure.jigsaw.pattern.plank_road;

import cn.aminorz.jigsaw.jigsaw.IJigsawPattern;
import cn.aminorz.jigsaw.jigsaw.JigsawPatternType;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawOccupiedSectionPool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodePool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodeSocketPool;
import com.aminor.civilizatus.world.gen.structure.jigsaw.piece.plank_road.PlankRoad_SW_Piece;

import static cn.aminorz.jigsaw.util.math.SimpleDirection.*;
import static com.aminor.civilizatus.world.gen.structure.jigsaw.Public_JigsawSummonNodeSocketType.JSNKT_PLANK_ROAD;

public class PlankRoad_SW implements IJigsawPattern {
    private static final PlankRoad_SW instance = new PlankRoad_SW();
    private static final JigsawSummonNodePool summonNodes = new JigsawSummonNodePool();
    private static final JigsawOccupiedSectionPool occupiedSectionPool = new JigsawOccupiedSectionPool();
    private static final JigsawSummonNodeSocketPool summonNodeSocketPool = new JigsawSummonNodeSocketPool();
    private static final JigsawPatternType jigsawPatternType = new JigsawPatternType("plank_road");

    static {
        //register summonNodes here
        summonNodes.register(0,0,0, SOUTH, JSNKT_PLANK_ROAD);
        summonNodes.register(0,0,0, WEST, JSNKT_PLANK_ROAD);
    }

    static {
        //register occupiedSectionPool here
        occupiedSectionPool.put(0, 0, 0, PlankRoad_SW_Piece::getInstance);
    }

    static {
        //register summonNodeSocketPool here
        summonNodeSocketPool.register(0,0,0,SOUTH, JSNKT_PLANK_ROAD,2, PlankRoad_SW::getInstance);
        summonNodeSocketPool.register(0,0,0,WEST, JSNKT_PLANK_ROAD,2, PlankRoad_SW::getInstance);

    }

    public static PlankRoad_SW getInstance() {
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
