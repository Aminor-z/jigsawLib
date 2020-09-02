package cn.aminorz.jigsaw.jigsaw;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.HashSet;

@Immutable
public class JigsawSideType extends JigsawType {
    private final HashSet<JigsawSideType> validTypes = new HashSet<>();
    private final String type;

    public JigsawSideType(String type) {
        this.type = type;
    }
    public JigsawSideType(String type, String... validTypes) {
        this.type = type;
        for (String validType : validTypes) {
            this.validTypes.add(new JigsawSideType(validType));
        }
    }

    public String getType() {
        return type;
    }

    public HashSet<JigsawSideType> getValidSideTypes() {
        return validTypes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof JigsawSideType) {
            JigsawSideType jigsawAccessType = (JigsawSideType) obj;
            return this.type.equals(jigsawAccessType.getType());
        } else {
            return false;
        }
    }
}
