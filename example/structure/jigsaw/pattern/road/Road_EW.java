package com.aminor.civilizatus.world.gen.structure.jigsaw.pattern.road;

import cn.aminorz.jigsaw.jigsaw.IJigsawPattern;
import cn.aminorz.jigsaw.jigsaw.JigsawPatternType;
import cn.aminorz.jigsaw.jigsaw.JigsawSummonNode;
import cn.aminorz.jigsaw.jigsaw.JigsawSummonNodeSocket;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawOccupiedSectionPool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodePool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodeSocketPool;
import cn.aminorz.jigsaw.util.math.JigsawSectionPos;
import com.aminor.civilizatus.world.gen.structure.jigsaw.piece.road.Road_EW_Cobblestone;

import static cn.aminorz.jigsaw.util.math.SimpleDirection.*;
import static com.aminor.civilizatus.world.gen.structure.jigsaw.Public_JigsawSummonNodeSocketType.JSNKT_BUILDING;
import static com.aminor.civilizatus.world.gen.structure.jigsaw.Public_JigsawSummonNodeSocketType.JSNKT_ROAD;

public class Road_EW implements IJigsawPattern {
    private static final Road_EW instance = new Road_EW();
    private static final JigsawSummonNodePool summonNodes = new JigsawSummonNodePool();
    private static final JigsawOccupiedSectionPool occupiedSectionPool = new JigsawOccupiedSectionPool();
    private static final JigsawSummonNodeSocketPool summonNodeSocketPool = new JigsawSummonNodeSocketPool();
    private static final JigsawPatternType jigsawPatternType = new JigsawPatternType("road");

    static {
        //register summonNodes here
        summonNodes.register(0, 0, 0, EAST, JSNKT_ROAD);
        summonNodes.register(0, 0, 0, WEST, JSNKT_ROAD);
        summonNodes.register(0, 0, 0, SOUTH, JSNKT_BUILDING);
        summonNodes.register(0, 0, 0, NORTH, JSNKT_BUILDING);
    }

    static {
        //register occupiedSectionPool here
        occupiedSectionPool.put(new JigsawSectionPos(0, 0, 0), Road_EW_Cobblestone::getInstance);
    }

    static {
        //register summonNodeSocketPool here
        summonNodeSocketPool.register(0, 0, 0, EAST, JSNKT_ROAD, 10, Road_EW::getInstance);
        summonNodeSocketPool.register(0, 0, 0, WEST, JSNKT_ROAD, 10, Road_EW::getInstance);
    }

    public static Road_EW getInstance() {
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
