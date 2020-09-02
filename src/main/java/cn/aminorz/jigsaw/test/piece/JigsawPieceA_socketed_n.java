package cn.aminorz.jigsaw.test.piece;

import cn.aminorz.jigsaw.jigsaw.IJigsawPiece;
import cn.aminorz.jigsaw.jigsaw.JigsawSide;
import cn.aminorz.jigsaw.jigsaw.JigsawSideType;
import cn.aminorz.jigsaw.util.math.SimpleDirection;

public class JigsawPieceA_socketed_n implements IJigsawPiece {
    public static JigsawSide jigsawSide = new JigsawSide();

    static {
        jigsawSide.put(SimpleDirection.NORTH, new JigsawSideType("A_socket", "A_socket"));
    }

    public JigsawPieceA_socketed_n() {
    }

    @Override
    public JigsawSide getJigsawSide() {
        return jigsawSide;
    }

    @Override
    public IJigsawPiece get() {
        return null;
    }
}
