package cn.aminorz.jigsaw.jigsaw.pool;

import cn.aminorz.jigsaw.jigsaw.JigsawPiece;
import cn.aminorz.jigsaw.jigsaw.JigsawSide;
import cn.aminorz.jigsaw.util.math.JigsawSectionPos;

import java.util.HashMap;
import java.util.function.Supplier;

public class JigsawOccupiedSectionPool extends HashMap<JigsawSectionPos, JigsawPiece> {
    public void put(int x1, int y1, int z1, Supplier<JigsawPiece> jigsawPiece) {
        put(new JigsawSectionPos(x1, y1, z1), jigsawPiece.get());
    }

    public void put(int x1, int y1, int z1, JigsawPiece jigsawPiece) {
        put(new JigsawSectionPos(x1, y1, z1), jigsawPiece);
    }

    public void put(JigsawSectionPos jigsawSectionPos, Supplier<JigsawPiece> jigsawPiece) {
        put(jigsawSectionPos, jigsawPiece.get());
    }

    public void putSide(JigsawSectionPos jigsawSectionPos, JigsawSide jigsawSide) {
        put(jigsawSectionPos, new JigsawPiece.SidePlaceholder(jigsawSide));
    }

    public void putSide(int x1, int y1, int z1, JigsawSide jigsawSide) {
        put(new JigsawSectionPos(x1, y1, z1), new JigsawPiece.SidePlaceholder(jigsawSide));
    }

    public void rectRegister(JigsawSectionPos jigsawSectionPos1, JigsawSectionPos jigsawSectionPos2, JigsawPiece jigsawPiece) {
        rectCycleRegisterByJigsawSectionPos(jigsawSectionPos1, jigsawSectionPos2, jigsawPiece);
    }

    public void rectRegister(JigsawSectionPos jigsawSectionPos1, JigsawSectionPos jigsawSectionPos2, Supplier<JigsawPiece> jigsawPiece) {
        rectCycleRegisterByJigsawSectionPos(jigsawSectionPos1, jigsawSectionPos2, jigsawPiece.get());
    }


    private void rectCycleRegisterByJigsawSectionPos(JigsawSectionPos jigsawSectionPos1, JigsawSectionPos jigsawSectionPos2, JigsawPiece jigsawPiece) {
        rectCycleRegister(Math.min(jigsawSectionPos1.x, jigsawSectionPos2.x), Math.min(jigsawSectionPos1.y, jigsawSectionPos2.y), Math.min(jigsawSectionPos1.z, jigsawSectionPos2.z), Math.max(jigsawSectionPos1.x, jigsawSectionPos2.x), Math.max(jigsawSectionPos1.y, jigsawSectionPos2.y), Math.max(jigsawSectionPos1.z, jigsawSectionPos2.z), jigsawPiece);
    }

    public void rectRegister(int x1, int y1, int z1, int x2, int y2, int z2, JigsawPiece jigsawPiece) {
        rectCycleRegister(x1, y1, z1, x2, y2, z2, jigsawPiece);
    }

    public void rectRegister(int x1, int y1, int z1, int x2, int y2, int z2, Supplier<JigsawPiece> jigsawPiece) {
        rectCycleRegister(x1, y1, z1, x2, y2, z2, jigsawPiece.get());
    }

    private void rectCycleRegister(int x1, int y1, int z1, int x2, int y2, int z2, JigsawPiece jigsawPiece) {
        int xA = Math.min(x1, x2);
        int xB = Math.max(x1, x2);
        int yA = Math.min(y1, y2);
        int yB = Math.max(y1, y2);
        int zA = Math.min(z1, z2);
        int zB = Math.max(z1, z2);
        for (int x = xA; x <= xB; ++x)
            for (int y = yA; y <= yB; ++y)
                for (int z = zA; z <= zB; ++z) {
                    put(new JigsawSectionPos(x, y, z), jigsawPiece);
                }
    }

    public void fillPlaceHolder(JigsawSectionPos jigsawSectionPos1, JigsawSectionPos jigsawSectionPos2) {
        rectRegister(jigsawSectionPos1, jigsawSectionPos2, JigsawPiece.PLACEHOLDER);
    }

    public void fillPlaceHolder(int x1, int y1, int z1, int x2, int y2, int z2) {
        rectRegister(x1, y1, z1, x2, y2, z2, JigsawPiece.PLACEHOLDER);
    }

    public void putPlaceHolder(JigsawSectionPos jigsawSectionPos) {
        put(jigsawSectionPos, JigsawPiece.PLACEHOLDER);
    }

    public void putPlaceHolder(int x1, int y1, int z1) {
        put(new JigsawSectionPos(x1, y1, z1), JigsawPiece.PLACEHOLDER);
    }
}
