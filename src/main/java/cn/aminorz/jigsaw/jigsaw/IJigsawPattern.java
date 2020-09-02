package cn.aminorz.jigsaw.jigsaw;

import cn.aminorz.jigsaw.jigsaw.pool.JigsawOccupiedSectionPool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSidePool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodesPool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodeSocketPool;

public interface IJigsawPattern {

    JigsawSidePool getSidePool();

    JigsawOccupiedSectionPool getOccupiedSectionPool();

    JigsawSummonNodesPool getSummonNodes();

    JigsawSummonNodeSocketPool getSummonNodeSocketPool();
}
