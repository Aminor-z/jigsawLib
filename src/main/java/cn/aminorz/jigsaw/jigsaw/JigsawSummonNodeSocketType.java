package cn.aminorz.jigsaw.jigsaw;

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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof JigsawSummonNodeSocketType) {
            JigsawSummonNodeSocketType jigsawSummonNodeSocketType = (JigsawSummonNodeSocketType) obj;
            return this.type.equals(jigsawSummonNodeSocketType.getType());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "JigsawSummonNodeSocketType["+super.toString()+"]";
    }

    @Override
    String getType() {
        return type;
    }
}
