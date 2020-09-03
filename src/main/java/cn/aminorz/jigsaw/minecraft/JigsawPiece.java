package cn.aminorz.jigsaw.minecraft;

import cn.aminorz.jigsaw.jigsaw.IJigsawPiece;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

public abstract class JigsawPiece extends TemplateStructurePiece implements IJigsawPiece {
    protected abstract ResourceLocation getResourceLocation();
    protected abstract  boolean isIgnoreEntities();
    public JigsawPiece(IStructurePieceType iStructurePieceType, TemplateManager manager, BlockPos pos) {
        super(iStructurePieceType, 0);
        this.templatePosition=pos;
        loadTemplate(manager,getResourceLocation());
    }

    public JigsawPiece(IStructurePieceType iStructurePieceType,TemplateManager manager, CompoundNBT nbt) {
        super(iStructurePieceType, nbt);
        loadTemplate(manager,getResourceLocation());
    }
    private void loadTemplate(TemplateManager manager,ResourceLocation resourceLocation) {
        Template template = manager.getTemplateDefaulted(resourceLocation);
        PlacementSettings placementsettings = new PlacementSettings().setIgnoreEntities(isIgnoreEntities());
        this.setup(template, this.templatePosition, placementsettings);
    }
}
