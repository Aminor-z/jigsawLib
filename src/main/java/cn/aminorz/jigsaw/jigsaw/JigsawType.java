package cn.aminorz.jigsaw.jigsaw;

import jdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
public abstract class JigsawType {
    abstract String getType();

    @Override
    public int hashCode() {
        return getType().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof JigsawType) {
            JigsawType jigsawAccessType = (JigsawType) obj;
            return this.getType().equals(jigsawAccessType.getType());
        } else {
            return false;
        }
    }
}
