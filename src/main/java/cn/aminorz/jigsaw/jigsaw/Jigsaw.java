package cn.aminorz.jigsaw.jigsaw;

import cn.aminorz.jigsaw.util.math.JigsawSectionPos;
import javafx.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.*;
import java.util.function.Supplier;

public abstract class Jigsaw {
    private final JigsawStructureGenerator jigsawStructureGenerator;

    public Jigsaw(JigsawStructureGenerator jigsawStructureGenerator) {
        this.jigsawStructureGenerator = jigsawStructureGenerator;
    }

    private <V extends IJigsawPattern> ArrayList<Pair<BlockPos, JigsawPiece>> generate(int x, int y, int z, Supplier<V> beginPattern) {
        jigsawStructureGenerator.generate(JigsawSectionPos.ZERO, beginPattern.get());
        HashMap<JigsawSectionPos, JigsawPiece> t = jigsawStructureGenerator.getJigsawMapState();
        Set<Map.Entry<JigsawSectionPos, JigsawPiece>> entrySet = t.entrySet();
        ArrayList<Pair<BlockPos, JigsawPiece>> result = new ArrayList<>();
        for (Map.Entry<JigsawSectionPos, JigsawPiece> entry : entrySet)
            if(entry.getValue().getResourceLocation()!=null)
            result.add(new Pair<>(getActualPos(entry.getKey(), x, y, z), entry.getValue()));
        return result;
    }

    public <V extends IJigsawPattern> List<StructurePiece> getComponents(int x, int y, int z, Supplier<V> beginPattern, TemplateManager templateManager) {
        ArrayList<Pair<BlockPos, JigsawPiece>> pieces = generate(x, y, z, beginPattern);
        ArrayList<StructurePiece> result = new ArrayList<>(pieces.size());
        for (Pair<BlockPos, JigsawPiece> piece : pieces) {
            result.add(piece.getValue().setPosition(piece.getKey()).loadTemplate(templateManager));
            //System.out.println(piece.getValuq3e().getClass().getSimpleName()+" -> "+piece.getKey());
        }
        return result;
    }

    /**
     * @param jigsawSectionPos
     * @param x                start x
     * @param y                start y
     * @param z                start z
     * @return actual pos
     */
    public abstract BlockPos getActualPos(JigsawSectionPos jigsawSectionPos, int x, int y, int z);
}
