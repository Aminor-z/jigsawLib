package cn.aminorz.jigsaw.jigsaw.pool;

import cn.aminorz.jigsaw.jigsaw.IJigsawPattern;
import cn.aminorz.jigsaw.jigsaw.JigsawSummonNodeSocket;
import cn.aminorz.jigsaw.jigsaw.JigsawSummonNodeSocketType;
import cn.aminorz.jigsaw.util.math.JigsawSectionPos;
import cn.aminorz.jigsaw.util.math.SimpleDirection;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class JigsawSummonNodeSocketPool extends HashMap<SimpleDirection, Map<JigsawSummonNodeSocketType, JigsawSummonNodeSocket>> {

    public void register(Supplier<JigsawSummonNodeSocket> supplier) {
        register(supplier.get());
    }

    public void register(JigsawSectionPos jigsawSectionPos, SimpleDirection simpleDirection, JigsawSummonNodeSocketType jigsawSummonNodeSocketType, int weight, Supplier<IJigsawPattern> iJigsawPatternSupplier) {
        register(new JigsawSummonNodeSocket(jigsawSectionPos, simpleDirection, jigsawSummonNodeSocketType, weight, iJigsawPatternSupplier));
    }

    public void register(int x1, int y1, int z1, SimpleDirection simpleDirection, JigsawSummonNodeSocketType jigsawSummonNodeSocketType, int weight, Supplier<IJigsawPattern> iJigsawPatternSupplier) {
        register(new JigsawSummonNodeSocket(new JigsawSectionPos(x1, y1, z1), simpleDirection, jigsawSummonNodeSocketType, weight, iJigsawPatternSupplier));
    }

    public void register(JigsawSummonNodeSocket jigsawSummonNodeSocket) {
        Map<JigsawSummonNodeSocketType, JigsawSummonNodeSocket> t = get(jigsawSummonNodeSocket.getSimpleDirection());
        if (t == null) {
            Map<JigsawSummonNodeSocketType, JigsawSummonNodeSocket> tt = new HashMap<>();
            tt.put(jigsawSummonNodeSocket.getType(), jigsawSummonNodeSocket);
            put(jigsawSummonNodeSocket.getSimpleDirection(), tt);
        } else
            t.put(jigsawSummonNodeSocket.getType(), jigsawSummonNodeSocket);
    }
}
