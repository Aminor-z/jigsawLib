package cn.aminorz.jigsaw.jigsaw;

import cn.aminorz.jigsaw.jigsaw.pool.JigsawOccupiedSectionPool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodeSocketPool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodesPool;

public interface IJigsawPattern {

    JigsawOccupiedSectionPool getOccupiedSectionPool();

    JigsawSummonNodesPool getSummonNodes();

    JigsawSummonNodeSocketPool getSummonNodeSocketPool();
}
