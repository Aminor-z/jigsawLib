package cn.aminorz.jigsaw.jigsaw;

import cn.aminorz.jigsaw.jigsaw.pool.JigsawOccupiedSectionPool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodeSocketPool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodePool;

public interface IJigsawPattern {

    JigsawOccupiedSectionPool getOccupiedSectionPool();

    JigsawSummonNodePool getSummonNodes();

    JigsawSummonNodeSocketPool getSummonNodeSocketPool();
    JigsawPatternType getJigsawPatternType();
}
