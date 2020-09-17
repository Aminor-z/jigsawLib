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
}
