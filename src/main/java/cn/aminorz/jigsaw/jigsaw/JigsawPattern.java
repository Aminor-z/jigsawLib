package cn.aminorz.jigsaw.jigsaw;

import cn.aminorz.jigsaw.jigsaw.pool.JigsawOccupiedSectionPool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodesPool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSidePool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodeSocketPool;

/**
 * @deprecated use interface instead
 */
@Deprecated
public abstract class JigsawPattern implements IJigsawPattern {
    public abstract JigsawSidePool getSidePool();

    public abstract JigsawOccupiedSectionPool getOccupiedSectionPool();
    public abstract JigsawSummonNodesPool getSummonNodes();

    public abstract JigsawSummonNodeSocketPool getSummonNodeSocketPool();

}