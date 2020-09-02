package cn.aminorz.jigsaw.jigsaw;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @deprecated
 */
@Deprecated
public abstract class JigsawSideAccessType extends JigsawType {
    private static final Map<JigsawSideAccessType, HashSet<JigsawSideAccessType>> jigsawSideAccessTypeMap = new HashMap<>();
    private final HashSet<JigsawSideAccessType> vaildSideAccessTypes = new HashSet<>();
    private final String type;

    public JigsawSideAccessType(String type) {
        this.type = type;
    }

    public static Map<JigsawSideAccessType, HashSet<JigsawSideAccessType>> getJigsawSideAccessTypeMap() {
        return jigsawSideAccessTypeMap;
    }

    public static void register(JigsawSideAccessType jigsawSideAccessType) {
        jigsawSideAccessTypeMap.put(jigsawSideAccessType, jigsawSideAccessType.getSideVaildAccessTypes());
    }

    public String getType() {
        return type;
    }

    public void addVaildSideAccessType(JigsawSideAccessType jigsawSideAccessType) {
        vaildSideAccessTypes.add(jigsawSideAccessType);
    }

    public void addVaildSideAccessType(HashSet<JigsawSideAccessType> jigsawSideAccessTypes) {
        vaildSideAccessTypes.addAll(jigsawSideAccessTypes);
    }

    public HashSet<JigsawSideAccessType> getSideVaildAccessTypes() {
        return vaildSideAccessTypes;
    }


    @Override
    public int hashCode() {
        return type.hashCode()^vaildSideAccessTypes.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof JigsawSideAccessType) {
            JigsawSideAccessType jigsawSideAccessType = (JigsawSideAccessType) obj;
            return this.type.equals(jigsawSideAccessType.getType());
        } else {
            return false;
        }
    }
}
