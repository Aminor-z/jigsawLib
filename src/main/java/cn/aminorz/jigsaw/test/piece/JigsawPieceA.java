package cn.aminorz.jigsaw.test.piece;

import cn.aminorz.jigsaw.jigsaw.IJigsawPiece;
import cn.aminorz.jigsaw.jigsaw.JigsawSide;

public class JigsawPieceA implements IJigsawPiece {
    public JigsawPieceA() {
    }

    @Override
    public JigsawSide getJigsawSide() {
        return null;
    }

    @Override
    public <T extends IJigsawPiece> T get() {
        return (T) new JigsawPieceA();
    }
}
