package cn.aminorz.jigsaw.jigsaw.pool;

import cn.aminorz.jigsaw.jigsaw.JigsawSummonNode;

import java.util.HashSet;
import java.util.function.Supplier;

public class JigsawSummonNodesPool extends HashSet<JigsawSummonNode> {
    public void register(Supplier<JigsawSummonNode> supplier) {
        add(supplier.get());
    }

    public void register(JigsawSummonNode supplier) {
        add(supplier);
    }
}
