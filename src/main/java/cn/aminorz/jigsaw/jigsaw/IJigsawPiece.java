package cn.aminorz.jigsaw.jigsaw;

public interface IJigsawPiece {
    JigsawSide getJigsawSide();
    <T extends IJigsawPiece> T get();
}
