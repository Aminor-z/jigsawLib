package cn.aminorz.jigsaw.jigsaw;

import cn.aminorz.jigsaw.util.math.SimpleDirection;

import java.util.*;

import static cn.aminorz.jigsaw.util.math.SimpleDirection.*;

public class JigsawSide extends HashMap<SimpleDirection, HashSet<JigsawSideType>> {
    public JigsawSide() {
    }
    private void register(SimpleDirection simpleDirection,JigsawSideType... jigsawSideType)
    {
        HashSet<JigsawSideType> t = get(simpleDirection);
        if (t != null) {
            Collections.addAll(t, jigsawSideType);
        }
        else{
            HashSet<JigsawSideType> tt=new HashSet<>();
            Collections.addAll(tt, jigsawSideType);
            put(simpleDirection,tt);
        }
    }
    public JigsawSide up(JigsawSideType... jigsawSideType) {
        register(UP,jigsawSideType);
        return this;
    }

    public JigsawSide down(JigsawSideType... jigsawSideType) {
        register(DOWN, jigsawSideType);
        return this;
    }

    public JigsawSide east(JigsawSideType... jigsawSideType) {
        register(EAST, jigsawSideType);
        return this;
    }

    public JigsawSide south(JigsawSideType... jigsawSideType) {
        register(SOUTH, jigsawSideType);
        return this;
    }

    public JigsawSide west(JigsawSideType... jigsawSideType) {
        register(WEST, jigsawSideType);
        return this;
    }

    public JigsawSide north(JigsawSideType... jigsawSideType) {
        register(NORTH, jigsawSideType);
        return this;
    }

    public JigsawSide all(JigsawSideType... jigsawSideType) {
        up(jigsawSideType);
        down(jigsawSideType);
        east(jigsawSideType);
        south(jigsawSideType);
        west(jigsawSideType);
        north(jigsawSideType);
        return this;
    }
    private void register(SimpleDirection simpleDirection,JigsawSideType jigsawSideType)
    {
        HashSet<JigsawSideType> t = get(simpleDirection);
        if (t != null) {
            t.add(jigsawSideType);
        }
        else{
            HashSet<JigsawSideType> tt=new HashSet<>();
            tt.add(jigsawSideType);
            put(simpleDirection,tt);
        }
    }
    public JigsawSide up(JigsawSideType jigsawSideType) {
        register(UP,jigsawSideType);
        return this;
    }

    public JigsawSide down(JigsawSideType jigsawSideType) {
        register(DOWN, jigsawSideType);
        return this;
    }

    public JigsawSide east(JigsawSideType jigsawSideType) {
        register(EAST, jigsawSideType);
        return this;
    }

    public JigsawSide south(JigsawSideType jigsawSideType) {
        register(SOUTH, jigsawSideType);
        return this;
    }

    public JigsawSide west(JigsawSideType jigsawSideType) {
        register(WEST, jigsawSideType);
        return this;
    }

    public JigsawSide north(JigsawSideType jigsawSideType) {
        register(NORTH, jigsawSideType);
        return this;
    }

    public JigsawSide all(JigsawSideType jigsawSideType) {
        up(jigsawSideType);
        down(jigsawSideType);
        east(jigsawSideType);
        south(jigsawSideType);
        west(jigsawSideType);
        north(jigsawSideType);
        return this;
    }
}
