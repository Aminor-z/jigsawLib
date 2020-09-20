package com.aminor.civilizatus.world.gen.structure.jigsaw.piece.plank_road;

import cn.aminorz.jigsaw.jigsaw.JigsawPiece;
import cn.aminorz.jigsaw.jigsaw.JigsawSide;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

import static com.aminor.civilizatus.world.gen.structure.jigsaw.PieceRegister.PLANK_ROAD_PIECE;
import static com.aminor.civilizatus.world.gen.structure.jigsaw.Public_SideType.PLANK_ROAD;
import static com.aminor.civilizatus.world.gen.structure.jigsaw.Public_SideType.PLANK_ROAD_DENY;

public class PlankRoad_WN_Piece extends JigsawPiece {
    private static final PlankRoad_WN_Piece instance = new PlankRoad_WN_Piece();

    public static PlankRoad_WN_Piece getInstance() {
        return instance;
    }

    //set your registered feature here
    private static final IStructurePieceType ISPT_PlankRoad_EW = PLANK_ROAD_PIECE;
    //set your nbt file here
    private static final ResourceLocation resourceLocation = new ResourceLocation("civilizatus", "plank_road/wn");
    private static JigsawSide jigsawSide = new JigsawSide();

    static {
        //register side type here
        jigsawSide.east(PLANK_ROAD_DENY).south(PLANK_ROAD_DENY).west(PLANK_ROAD).north(PLANK_ROAD);
    }

    public PlankRoad_WN_Piece() {
        super(ISPT_PlankRoad_EW);
    }

    public PlankRoad_WN_Piece(TemplateManager manager, BlockPos pos) {
        super(ISPT_PlankRoad_EW, manager, pos);
    }

    public PlankRoad_WN_Piece(TemplateManager manager, CompoundNBT nbt) {
        super(ISPT_PlankRoad_EW, manager, nbt);
    }

    @Override
    protected ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    @Override
    protected boolean isIgnoreEntities() {
        return false;
    }

    @Override
    protected boolean isVirtual() {
        return false;
    }

    @Override
    public JigsawSide getJigsawSide() {
        return jigsawSide;
    }

    @Override
    protected void handleDataMarker(String s, BlockPos blockPos, IWorld iWorld, Random random, MutableBoundingBox mutableBoundingBox) {
    }
}
