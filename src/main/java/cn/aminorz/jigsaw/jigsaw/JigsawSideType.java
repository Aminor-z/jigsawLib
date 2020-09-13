package cn.aminorz.jigsaw.jigsaw;

import cn.aminorz.jigsaw.jigsaw.JigsawType;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.HashSet;

@Immutable
public class JigsawSideType extends JigsawType {
    public static cn.aminorz.jigsaw.jigsaw.JigsawSideType DENY=new cn.aminorz.jigsaw.jigsaw.JigsawSideType("DENY");
    private final HashSet<cn.aminorz.jigsaw.jigsaw.JigsawSideType> validTypes = new HashSet<>();
    private final String type;

    public JigsawSideType(String type) {
        this.type = type;
    }
    public JigsawSideType(String type, String... validTypes) {
        this.type = type;
        for (String validType : validTypes) {
            this.validTypes.add(new cn.aminorz.jigsaw.jigsaw.JigsawSideType(validType));
        }
    }

    public String getType() {
        return type;
    }

    public HashSet<cn.aminorz.jigsaw.jigsaw.JigsawSideType> getValidSideTypes() {
        return validTypes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof cn.aminorz.jigsaw.jigsaw.JigsawSideType) {
            cn.aminorz.jigsaw.jigsaw.JigsawSideType jigsawSideType = (cn.aminorz.jigsaw.jigsaw.JigsawSideType) obj;
            return this.type.equals(jigsawSideType.getType());
        } else {
            return false;
        }
    }
}
