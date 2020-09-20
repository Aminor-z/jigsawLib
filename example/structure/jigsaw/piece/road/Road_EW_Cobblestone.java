package com.aminor.civilizatus.world.gen.structure.jigsaw.piece.road;

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

import static com.aminor.civilizatus.world.gen.structure.jigsaw.PieceRegister.ROAD_EW_COBBLESTONE;
import static com.aminor.civilizatus.world.gen.structure.jigsaw.Public_SideType.ROAD;

public class Road_EW_Cobblestone extends JigsawPiece {
    private static final Road_EW_Cobblestone instance=new Road_EW_Cobblestone();
    public static Road_EW_Cobblestone getInstance(){return instance;}
    //set your registered feature here
    private static final IStructurePieceType iStructurePieceType = ROAD_EW_COBBLESTONE;
    //set your nbt file here
    private static ResourceLocation resourceLocation = new ResourceLocation("civilizatus","flat/cobble_stone");
    private static JigsawSide jigsawSide = new JigsawSide();

    static {
        //register side type here
        jigsawSide.east(ROAD).west(ROAD);
    }

    public Road_EW_Cobblestone() {
        super(ROAD_EW_COBBLESTONE);
    }

    public Road_EW_Cobblestone(TemplateManager manager, BlockPos pos) {
        super(ROAD_EW_COBBLESTONE, manager, pos);
    }

    public Road_EW_Cobblestone(TemplateManager manager, CompoundNBT nbt) {
        super(ROAD_EW_COBBLESTONE, manager, nbt);
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
