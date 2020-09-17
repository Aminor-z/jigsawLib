package cn.aminorz.jigsaw.jigsaw;

import cn.aminorz.jigsaw.util.math.JigsawSectionPos;

import java.util.HashMap;

public class JigsawMapState extends HashMap<JigsawSectionPos, JigsawPiece> {

    public void register(JigsawSectionPos jigsawSectionPos, IJigsawPattern jigsawPattern) {
        if (jigsawSectionPos != null && jigsawPattern != null && jigsawPattern.getOccupiedSectionPool() != null)
            for (Entry<JigsawSectionPos, JigsawPiece> entry : jigsawPattern.getOccupiedSectionPool().entrySet()) {
                try {
                    put(entry.getKey().add(jigsawSectionPos), entry.getValue().getNewInstance());
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
    }
}
