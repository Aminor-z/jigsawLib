package cn.aminorz.jigsaw.jigsaw.prefab;

import cn.aminorz.jigsaw.jigsaw.JigsawPiece;
import cn.aminorz.jigsaw.jigsaw.JigsawSide;
import jdk.nashorn.internal.ir.annotations.Immutable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;
@Immutable
public class JigsawEmptyPiece extends JigsawPiece {
    public JigsawEmptyPiece(TemplateManager manager, BlockPos pos) {
        super(null, manager, pos);
    }

    public JigsawEmptyPiece(TemplateManager manager, CompoundNBT nbt) {
        super(null, manager, nbt);
    }

    @Override
    protected ResourceLocation getResourceLocation() {
        return null;
    }

    @Override
    protected boolean isIgnoreEntities() {
        return false;
    }

    @Override
    public JigsawSide getJigsawSide() {
        return null;
    }

    @Override
    protected void handleDataMarker(String s, BlockPos blockPos, IWorld iWorld, Random random, MutableBoundingBox mutableBoundingBox) {
    }
}
