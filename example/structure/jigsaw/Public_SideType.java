package com.aminor.civilizatus.world.gen.structure.jigsaw;

import cn.aminorz.jigsaw.jigsaw.JigsawSideType;

public class Public_SideType {
    public static final JigsawSideType ROAD=new JigsawSideType("road");
    public static final JigsawSideType ROAD_SIDE=new JigsawSideType("road_side");
    public static final JigsawSideType BUILDING_ENTRY=new JigsawSideType("building_entry");
    public static final JigsawSideType PLANK_ROAD=new JigsawSideType("plank_road");
    public static final JigsawSideType PLANK_ROAD_DENY=new JigsawSideType("PLANK_ROAD_DENY");
    static{
        ROAD.addValidSideType(ROAD);
        ROAD_SIDE.addValidSideType(BUILDING_ENTRY);
        BUILDING_ENTRY.addValidSideType(ROAD_SIDE);
        PLANK_ROAD.addValidSideType(PLANK_ROAD);
    }

}
