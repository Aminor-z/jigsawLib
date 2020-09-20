package cn.aminorz.jigsaw.jigsaw;

import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;

import java.util.function.Supplier;

public class JigsawPatternType extends JigsawType {
    private final String type;

    public JigsawPatternType(String type) {
        this.type = type;
    }

    @Override
    String getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof JigsawPatternType) {
            JigsawPatternType jigsawPatternType = (JigsawPatternType) obj;
            return this.type.equals(jigsawPatternType.getType());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "JigsawPatternType["+super.toString()+"]";
    }
}
