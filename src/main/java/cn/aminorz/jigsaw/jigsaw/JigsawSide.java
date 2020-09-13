package cn.aminorz.jigsaw.jigsaw;

import cn.aminorz.jigsaw.jigsaw.JigsawSideType;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawMapPool;
import cn.aminorz.jigsaw.util.math.SimpleDirection;

public class JigsawSide extends JigsawMapPool<SimpleDirection, JigsawSideType> {
    public JigsawSide() {
    }

    public cn.aminorz.jigsaw.jigsaw.JigsawSide up(JigsawSideType jigsawSideType) {
        register(SimpleDirection.UP, jigsawSideType);
        return this;
    }

    public cn.aminorz.jigsaw.jigsaw.JigsawSide down(JigsawSideType jigsawSideType) {
        register(SimpleDirection.DOWN, jigsawSideType);
        return this;
    }

    public cn.aminorz.jigsaw.jigsaw.JigsawSide east(JigsawSideType jigsawSideType) {
        register(SimpleDirection.EAST, jigsawSideType);
        return this;
    }

    public cn.aminorz.jigsaw.jigsaw.JigsawSide south(JigsawSideType jigsawSideType) {
        register(SimpleDirection.SOUTH, jigsawSideType);
        return this;
    }

    public cn.aminorz.jigsaw.jigsaw.JigsawSide west(JigsawSideType jigsawSideType) {
        register(SimpleDirection.WEST, jigsawSideType);
        return this;
    }

    public cn.aminorz.jigsaw.jigsaw.JigsawSide north(JigsawSideType jigsawSideType) {
        register(SimpleDirection.NORTH, jigsawSideType);
        return this;
    }
    public cn.aminorz.jigsaw.jigsaw.JigsawSide all(JigsawSideType jigsawSideType)
    {
        up(jigsawSideType);
        down(jigsawSideType);
        east(jigsawSideType);
        south(jigsawSideType);
        west(jigsawSideType);
        north(jigsawSideType);
        return this;
    }

}
