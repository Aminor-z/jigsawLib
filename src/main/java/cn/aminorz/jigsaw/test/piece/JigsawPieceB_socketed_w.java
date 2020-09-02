package cn.aminorz.jigsaw.test.piece;

import cn.aminorz.jigsaw.jigsaw.IJigsawPiece;
import cn.aminorz.jigsaw.jigsaw.JigsawSide;
import cn.aminorz.jigsaw.jigsaw.JigsawSideType;
import cn.aminorz.jigsaw.util.math.SimpleDirection;

public class JigsawPieceB_socketed_w implements IJigsawPiece {
    public static JigsawSide jigsawSide = new JigsawSide();

    static {
        jigsawSide.put(SimpleDirection.NORTH, new JigsawSideType("B_socket", "B_socket"));
    }

    public JigsawPieceB_socketed_w() {
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
