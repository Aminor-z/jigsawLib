package cn.aminorz.jigsaw.test;


import javafx.util.Pair;
import cn.aminorz.jigsaw.jigsaw.IJigsawPiece;
import cn.aminorz.jigsaw.jigsaw.Jigsaw;
import cn.aminorz.jigsaw.jigsaw.JigsawPool;
import cn.aminorz.jigsaw.jigsaw.JigsawStructureGenerator;
import cn.aminorz.jigsaw.test.multipiece.JigsawPatternA;
import cn.aminorz.jigsaw.test.multipiece.JigsawPatternB;
import cn.aminorz.jigsaw.util.math.JigsawSectionPos;
import cn.aminorz.jigsaw.util.math.SimplePos;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {

        generate_test();
    }

    private static void generate_test() {
        Jigsaw<SimplePos> jigsaw = new Jigsaw<SimplePos>(
                new JigsawStructureGenerator()
                        .setJigsawPool(
                                new JigsawPool(
                                        JigsawPatternA::new,
                                        JigsawPatternB::new))
                        .setMode(JigsawStructureGenerator.Mode.BFS)
                        .setLimit(100)
                        .setTerminatorChecker(true)
        ) {
            @Override
            public SimplePos getActualPos(JigsawSectionPos jsp, int x, int y, int z) {
                return new SimplePos(jsp.getX() + x, jsp.getY() + y, jsp.getZ() + z);
            }
        };
        long startTime = System.currentTimeMillis();
        ArrayList<Pair<SimplePos, IJigsawPiece>> t =
                jigsaw.generate(0, 0, 0, JigsawPatternA::new);
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("Summon " + t.size() + " piece in " + (time) + "ms.");
    }
}

