package com.aminor.civilizatus.world.gen.structure.jigsaw.piece.building.house_a;

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

import static com.aminor.civilizatus.world.gen.structure.jigsaw.PieceRegister.HOUSE_A_E_PIECE;
import static com.aminor.civilizatus.world.gen.structure.jigsaw.Public_SideType.BUILDING_ENTRY;

public class House_a_w_piece extends JigsawPiece {
    private static final House_a_w_piece instance = new House_a_w_piece();

    public static House_a_w_piece getInstance() {
        return instance;
    }

    //set your registered feature here
    private static final IStructurePieceType ISPT_House_a_e = HOUSE_A_E_PIECE;
    //set your nbt file here
    private static final ResourceLocation resourceLocation = new ResourceLocation("civilizatus", "building/house_a/w");
    private static JigsawSide jigsawSide = new JigsawSide();

    static {
        //register side type here
        jigsawSide.west(BUILDING_ENTRY);
    }

    public House_a_w_piece() {
        super(ISPT_House_a_e);
    }

    public House_a_w_piece(TemplateManager manager, BlockPos pos) {
        super(ISPT_House_a_e, manager, pos);
    }

    public House_a_w_piece(TemplateManager manager, CompoundNBT nbt) {
        super(ISPT_House_a_e, manager, nbt);
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
    protected boolean isVirtual() { return false; }

    @Override
    public JigsawSide getJigsawSide() {
        return jigsawSide;
    }

    @Override
    protected void handleDataMarker(String s, BlockPos blockPos, IWorld iWorld, Random random, MutableBoundingBox mutableBoundingBox) {
    }
}
