package com.aminor.civilizatus.world.gen.structure.jigsaw.pattern.building.shop_a;

import cn.aminorz.jigsaw.jigsaw.IJigsawPattern;
import cn.aminorz.jigsaw.jigsaw.JigsawPatternType;
import cn.aminorz.jigsaw.jigsaw.JigsawSide;
import cn.aminorz.jigsaw.jigsaw.JigsawSummonNodeSocket;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawOccupiedSectionPool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodePool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodeSocketPool;
import cn.aminorz.jigsaw.util.math.JigsawSectionPos;
import com.aminor.civilizatus.world.gen.structure.jigsaw.piece.building.shop_a.Shop_a_e_piece;

import static cn.aminorz.jigsaw.util.math.SimpleDirection.EAST;
import static com.aminor.civilizatus.world.gen.structure.jigsaw.Public_JigsawSummonNodeSocketType.JSNKT_BUILDING;
import static com.aminor.civilizatus.world.gen.structure.jigsaw.Public_SideType.BUILDING_ENTRY;

public class Shop_a_e implements IJigsawPattern {
    private static final Shop_a_e instance = new Shop_a_e();
    private static final JigsawSummonNodePool summonNodes = new JigsawSummonNodePool();
    private static final JigsawOccupiedSectionPool occupiedSectionPool = new JigsawOccupiedSectionPool();
    private static final JigsawSummonNodeSocketPool summonNodeSocketPool = new JigsawSummonNodeSocketPool();
    private static final JigsawPatternType jigsawPatternType = new JigsawPatternType("shop_a");

    static {
        //register summonNodes here
    }

    static {
        //register occupiedSectionPool here
        occupiedSectionPool.fillPlaceHolder(0, 1, 0, 1, 1, 1);
        occupiedSectionPool.putPlaceHolder(0, 0, 1);
        occupiedSectionPool.putSide(1, 0, 0, new JigsawSide().east(BUILDING_ENTRY));
        occupiedSectionPool.putSide(1, 0, 1, new JigsawSide().east(BUILDING_ENTRY));
        occupiedSectionPool.put(0, 0, 0, Shop_a_e_piece::getInstance);
    }

    static {
        //register summonNodeSocketPool here
        summonNodeSocketPool.register(1, 0, 0, EAST, JSNKT_BUILDING, 10, Shop_a_e::getInstance);
        summonNodeSocketPool.register(1, 0, 1, EAST, JSNKT_BUILDING, 10, Shop_a_e::getInstance);
    }

    public static Shop_a_e getInstance() {
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
