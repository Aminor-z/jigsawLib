package cn.aminorz.jigsaw.jigsaw.pool;

import cn.aminorz.jigsaw.jigsaw.JigsawPiece;
import cn.aminorz.jigsaw.jigsaw.JigsawSide;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawMapPool;
import cn.aminorz.jigsaw.util.math.JigsawSectionPos;

public class JigsawOccupiedSectionPool extends JigsawMapPool<JigsawSectionPos,JigsawPiece> {
    public void putSide(JigsawSectionPos jigsawSectionPos, JigsawSide jigsawSide) {
        register(jigsawSectionPos, new JigsawPiece.SidePlaceholder(jigsawSide));
    }

    public void rectRegister(JigsawSectionPos jigsawSectionPos1, JigsawSectionPos jigsawSectionPos2, JigsawPiece jigsawPiece) {
        int x1 = Math.min(jigsawSectionPos1.x, jigsawSectionPos2.x);
        int x2 = Math.max(jigsawSectionPos1.x, jigsawSectionPos2.x);
        int y1 = Math.min(jigsawSectionPos1.y, jigsawSectionPos2.y);
        int y2 = Math.max(jigsawSectionPos1.y, jigsawSectionPos2.y);
        int z1 = Math.min(jigsawSectionPos1.z, jigsawSectionPos2.z);
        int z2 = Math.max(jigsawSectionPos1.z, jigsawSectionPos2.z);
        for (; x1 <= x2; ++x1)
            for (; y1 <= y2; ++y1)
                for (; z1 <= z2; ++z1) {
                    register(new JigsawSectionPos(x1, y1, z1), jigsawPiece);
                }
    }

    public void fillPlaceHolder(JigsawSectionPos jigsawSectionPos1, JigsawSectionPos jigsawSectionPos2) {
        rectRegister(jigsawSectionPos1, jigsawSectionPos2, JigsawPiece.PLACEHOLDER);
    }

    public void putPlaceHolder(JigsawSectionPos jigsawSectionPos) {
        register(jigsawSectionPos, JigsawPiece.PLACEHOLDER);
    }
}
