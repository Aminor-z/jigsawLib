package cn.aminorz.jigsaw.jigsaw;

import cn.aminorz.jigsaw.util.math.JigsawSectionPos;

import java.util.HashMap;
import java.util.Map;

public class JigsawMapState {
    private final HashMap<JigsawSectionPos, IJigsawPiece> mapState = new HashMap<>();

    public HashMap<JigsawSectionPos, IJigsawPiece> getMapState() {
        return mapState;
    }

    public void add(JigsawSectionPos jigsawSectionPos, IJigsawPiece piece) {
        mapState.put(jigsawSectionPos, piece);
    }

    public void add(JigsawSectionPos jigsawSectionPos, IJigsawPattern jigsawPattern) {
        for (Map.Entry<JigsawSectionPos, IJigsawPiece> entry : jigsawPattern.getOccupiedSectionPool().entrySet()) {
            mapState.put(entry.getKey().add(jigsawSectionPos), entry.getValue());
        }
    }
}
