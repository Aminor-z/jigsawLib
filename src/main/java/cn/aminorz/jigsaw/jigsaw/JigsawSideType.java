package cn.aminorz.jigsaw.jigsaw;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.Arrays;
import java.util.HashSet;

@Immutable
public class JigsawSideType extends JigsawType {
    public static JigsawSideType DENY=new JigsawSideType("DENY");
    private final HashSet<JigsawSideType> validTypes = new HashSet<>();
    private final String type;

    public JigsawSideType(String type) {
        this.type = type;
    }
    public JigsawSideType(String type, JigsawSideType... validTypes) {
        this.type = type;
        this.validTypes.addAll(Arrays.asList(validTypes));
    }
    public JigsawSideType addValidSideType(JigsawSideType jigsawSideType){
        validTypes.add(jigsawSideType);
        return this;
    }
    public JigsawSideType addValidSideType(JigsawSideType... jigsawSideType){
        validTypes.addAll(Arrays.asList(jigsawSideType));
        return this;
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
            JigsawSideType jigsawSideType = (JigsawSideType) obj;
            return this.type.equals(jigsawSideType.getType());
        } else {
            return false;
        }
    }
    @Override
    public String toString() {
        return "JigsawSideType["+super.toString()+"]";
    }
}
