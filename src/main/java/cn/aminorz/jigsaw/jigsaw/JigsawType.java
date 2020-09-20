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
            JigsawType jigsawType = (JigsawType) obj;
            return this.getType().equals(jigsawType.getType());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return getType();
    }

}
