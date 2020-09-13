package cn.aminorz.jigsaw.jigsaw;

import cn.aminorz.jigsaw.util.math.JigsawSectionPos;

import java.util.HashMap;
import java.util.Map;

public class JigsawMapState extends HashMap<JigsawSectionPos, JigsawPiece> {

    public void register(JigsawSectionPos jigsawSectionPos, IJigsawPattern jigsawPattern) {
        if (jigsawSectionPos != null && jigsawPattern != null && jigsawPattern.getOccupiedSectionPool() != null)
            for (Map.Entry<JigsawSectionPos, JigsawPiece> entry : jigsawPattern.getOccupiedSectionPool().entrySet()) {
                put(entry.getKey().add(jigsawSectionPos), entry.getValue());
            }
    }
}
