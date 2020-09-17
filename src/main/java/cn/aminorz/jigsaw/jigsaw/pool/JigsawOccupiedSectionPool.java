package cn.aminorz.jigsaw.jigsaw.pool;

import cn.aminorz.jigsaw.jigsaw.JigsawPiece;
import cn.aminorz.jigsaw.jigsaw.JigsawSide;
import cn.aminorz.jigsaw.util.math.JigsawSectionPos;

public class JigsawOccupiedSectionPool extends JigsawMapPool<JigsawSectionPos, JigsawPiece> {
    public void putSide(JigsawSectionPos jigsawSectionPos, JigsawSide jigsawSide) {
        register(jigsawSectionPos, new JigsawPiece.SidePlaceholder(jigsawSide));
    }
    public void putSide(int x1,int y1,int z1, JigsawSide jigsawSide) {
        register(new JigsawSectionPos(x1,y1,z1), new JigsawPiece.SidePlaceholder(jigsawSide));
    }
    public void rectRegister(JigsawSectionPos jigsawSectionPos1, JigsawSectionPos jigsawSectionPos2, JigsawPiece jigsawPiece) {
        int x1 = Math.min(jigsawSectionPos1.x, jigsawSectionPos2.x);
        int x2 = Math.max(jigsawSectionPos1.x, jigsawSectionPos2.x);
        int y1 = Math.min(jigsawSectionPos1.y, jigsawSectionPos2.y);
        int y2 = Math.max(jigsawSectionPos1.y, jigsawSectionPos2.y);
        int z1 = Math.min(jigsawSectionPos1.z, jigsawSectionPos2.z);
        int z2 = Math.max(jigsawSectionPos1.z, jigsawSectionPos2.z);
        for (int x=x1; x <= x2; ++x)
            for (int y=y1; y <= y2; ++y)
                for (int z=z1; z <= z2; ++z) {
                    register(new JigsawSectionPos(x, y, z), jigsawPiece);
                }
    }
    public void rectRegister(int x1,int y1,int z1,int x2,int y2,int z2,JigsawPiece jigsawPiece) {
        int xA = Math.min(x1, x2);
        int xB = Math.max(x1, x2);
        int yA = Math.min(y1, y2);
        int yB = Math.max(y1, y2);
        int zA = Math.min(z1, z2);
        int zB = Math.max(z1, z2);
        for (int x = xA; x <= xB; ++x)
            for (int y=yA; y <= yB; ++y)
                for (int z=zA; z <= zB; ++z) {
                    register(new JigsawSectionPos(x, y, z), jigsawPiece);
                }
    }
    public void fillPlaceHolder(JigsawSectionPos jigsawSectionPos1, JigsawSectionPos jigsawSectionPos2) {
        rectRegister(jigsawSectionPos1, jigsawSectionPos2, JigsawPiece.PLACEHOLDER);
    }
    public void fillPlaceHolder(int x1,int y1,int z1,int x2,int y2,int z2)
    {
        rectRegister( x1, y1, z1, x2, y2, z2, JigsawPiece.PLACEHOLDER);
    }
    public void putPlaceHolder(JigsawSectionPos jigsawSectionPos) {
        register(jigsawSectionPos, JigsawPiece.PLACEHOLDER);
    }
    public void putPlaceHolder(int x1,int y1,int z1) {
        register(new JigsawSectionPos(x1,y1,z1), JigsawPiece.PLACEHOLDER);
    }
}
