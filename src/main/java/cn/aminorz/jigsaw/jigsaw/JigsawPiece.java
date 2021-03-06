package cn.aminorz.jigsaw.jigsaw;

import cn.aminorz.jigsaw.util.math.SimpleDirection;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.HashSet;
import java.util.Random;

public abstract class JigsawPiece extends TemplateStructurePiece implements IJigsawPiece {
    public static final JigsawPiece PLACEHOLDER = new Placeholder();
    public JigsawPiece() {
        super(null, 0);
    }
    public JigsawPiece(IStructurePieceType iStructurePieceType) {
        super(iStructurePieceType, 0);
    }

    public JigsawPiece(IStructurePieceType iStructurePieceType, TemplateManager manager, BlockPos pos) {
        super(iStructurePieceType, 0);
        this.templatePosition = pos;
        loadTemplate(manager);
    }

    public JigsawPiece(IStructurePieceType iStructurePieceType, TemplateManager manager, CompoundNBT nbt) {
        super(iStructurePieceType, nbt);
        loadTemplate(manager);
    }

    public JigsawPiece getNewInstance() throws IllegalAccessException, InstantiationException {
            return this.getClass().newInstance();
    }

    protected abstract ResourceLocation getResourceLocation();
    protected abstract boolean isIgnoreEntities();
    protected abstract boolean isVirtual();
    public JigsawPiece setPosition(BlockPos blockPos) {
        this.templatePosition = blockPos;
        return this;
    }

    public JigsawPiece loadTemplate(TemplateManager manager) {
        Template template = manager.getTemplateDefaulted(getResourceLocation());
        PlacementSettings placementsettings = new PlacementSettings().setIgnoreEntities(isIgnoreEntities());
        this.setup(template, this.templatePosition, placementsettings);
        return this;
    }
    public static class Placeholder extends JigsawPiece {
        public Placeholder(){}
        @Override
        protected ResourceLocation getResourceLocation() {
            return null;
        }

        @Override
        protected boolean isIgnoreEntities() {
            return true;
        }

        @Override
        protected boolean isVirtual() {
            return false;
        }

        @Override
        public JigsawSide getJigsawSide() {
            return null;
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
        }

        @Override
        public boolean create(IWorld worldIn, ChunkGenerator<?> chunkGeneratorIn, Random randomIn, MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn) {
            return false;
        }
    }
    public static class SidePlaceholder extends JigsawPiece {
        JigsawSide jigsawSide = new JigsawSide();
        public SidePlaceholder(SimpleDirection simpleDirection, HashSet<JigsawSideType> jigsawSideType) {
            super(null);
            jigsawSide.put(simpleDirection, jigsawSideType);
        }
        public SidePlaceholder(){}
        public SidePlaceholder(JigsawSide jigsawSide) {
            super(null);
            this.jigsawSide = jigsawSide;
        }
        public SidePlaceholder up(JigsawSideType... jigsawSideType) {
            jigsawSide.up(jigsawSideType);
            return this;
        }

        public SidePlaceholder down(JigsawSideType... jigsawSideType) {
            jigsawSide.down(jigsawSideType);
            return this;
        }

        public SidePlaceholder east(JigsawSideType... jigsawSideType) {
            jigsawSide.east(jigsawSideType);
            return this;
        }

        public SidePlaceholder south(JigsawSideType... jigsawSideType) {
            jigsawSide.south(jigsawSideType);
            return this;
        }

        public SidePlaceholder west(JigsawSideType... jigsawSideType) {
            jigsawSide.west(jigsawSideType);
            return this;
        }

        public SidePlaceholder north(JigsawSideType... jigsawSideType) {
            jigsawSide.north(jigsawSideType);
            return this;
        }

        @Override
        protected ResourceLocation getResourceLocation() {
            return null;
        }

        @Override
        protected boolean isIgnoreEntities() {
            return true;
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
        protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
        }

        @Override
        public boolean create(IWorld worldIn, ChunkGenerator<?> chunkGeneratorIn, Random randomIn, MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn) {
            return false;
        }
    }
}
