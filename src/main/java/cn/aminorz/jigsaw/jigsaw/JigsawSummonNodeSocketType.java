package cn.aminorz.jigsaw.jigsaw;

import cn.aminorz.jigsaw.jigsaw.JigsawType;
import jdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
public class JigsawSummonNodeSocketType extends JigsawType {
    private final String type;

    public JigsawSummonNodeSocketType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return getType().hashCode();
    }

    @Override
    String getType() {
        return type;
    }
}
