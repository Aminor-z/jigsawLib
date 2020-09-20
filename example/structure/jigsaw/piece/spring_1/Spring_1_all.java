package com.aminor.civilizatus.world.gen.structure.jigsaw.piece.spring_1;

import cn.aminorz.jigsaw.jigsaw.JigsawPiece;
import cn.aminorz.jigsaw.jigsaw.JigsawSide;
import com.aminor.civilizatus.world.gen.structure.jigsaw.PieceRegister;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

import static com.aminor.civilizatus.world.gen.structure.jigsaw.PieceRegister.SPRING_1_ALL;

public class Spring_1_all extends JigsawPiece {
    private static Spring_1_all instance=new Spring_1_all();
    public static Spring_1_all getInstance(){return instance;}
    //set your registered feature here
    private static IStructurePieceType iStructurePieceType = SPRING_1_ALL;
    //set your nbt file here
    private static ResourceLocation resourceLocation = new ResourceLocation("civilizatus","spring/pattern-1/spring_all");
    static {
        //register side type here
    }
    public Spring_1_all() {
        super(SPRING_1_ALL);
    }

    public Spring_1_all(TemplateManager manager, BlockPos pos) {
        super(SPRING_1_ALL, manager, pos);
    }

    public Spring_1_all(TemplateManager manager, CompoundNBT nbt) {
        super(SPRING_1_ALL, manager, nbt);
    }

    @Override
    protected ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    @Override
    protected boolean isIgnoreEntities() {
        return true;
    }

    @Override
    protected boolean isVirtual() {
        return true;
    }

    @Override
    public JigsawSide getJigsawSide() {
        return null;
    }

    @Override
    protected void handleDataMarker(String s, BlockPos blockPos, IWorld iWorld, Random random, MutableBoundingBox mutableBoundingBox) {

    }
}
