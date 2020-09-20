package com.aminor.civilizatus.world.gen.structure.jigsaw.piece.building.shop_a;

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

import static com.aminor.civilizatus.world.gen.structure.jigsaw.PieceRegister.SHOP_A_E_PIECE;

public class Shop_a_s_piece extends JigsawPiece {
    private static final Shop_a_s_piece instance = new Shop_a_s_piece();
    //set your registered feature here
    private static final IStructurePieceType ISPT_Shop_1_e_piece = SHOP_A_E_PIECE;
    //set your nbt file here
    private static final ResourceLocation resourceLocation = new ResourceLocation("civilizatus", "building/shop_a/s");
    private static JigsawSide jigsawSide = new JigsawSide();

    static {
        //register side type here
    }

    public Shop_a_s_piece() {
        super(SHOP_A_E_PIECE);
    }

    public Shop_a_s_piece(TemplateManager manager, BlockPos pos) {
        super(SHOP_A_E_PIECE, manager, pos);
    }

    public Shop_a_s_piece(TemplateManager manager, CompoundNBT nbt) {
        super(SHOP_A_E_PIECE, manager, nbt);
    }

    public static Shop_a_s_piece getInstance() {
        return instance;
    }

    @Override
    protected ResourceLocation getResourceLocation() { return resourceLocation; }

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
