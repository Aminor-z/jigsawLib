package cn.aminorz.jigsaw.jigsaw.pool;

import cn.aminorz.jigsaw.jigsaw.JigsawSummonNodeSocket;
import cn.aminorz.jigsaw.jigsaw.JigsawSummonNodeSocketType;
import cn.aminorz.jigsaw.util.math.SimpleDirection;

import java.util.HashMap;
import java.util.function.Supplier;

public class JigsawSummonNodeSocketPool extends HashMap<SimpleDirection, HashMap<JigsawSummonNodeSocketType, JigsawSummonNodeSocket>> {
    public void register(JigsawSummonNodeSocket jigsawSummonNodeSocket) {
        HashMap<JigsawSummonNodeSocketType, JigsawSummonNodeSocket> t = get(jigsawSummonNodeSocket.getSimpleDirection());
        if (t == null) {
            HashMap<JigsawSummonNodeSocketType, JigsawSummonNodeSocket> tt = new HashMap<>();
            tt.put(jigsawSummonNodeSocket.getType(), jigsawSummonNodeSocket);
            put(jigsawSummonNodeSocket.getSimpleDirection(),tt);
        } else
            t.put(jigsawSummonNodeSocket.getType(), jigsawSummonNodeSocket);
    }

    public void register(Supplier<JigsawSummonNodeSocket> supplier) {
        JigsawSummonNodeSocket jsns=supplier.get();
        HashMap<JigsawSummonNodeSocketType, JigsawSummonNodeSocket> t = get(jsns.getSimpleDirection());
        if (t == null) {
            HashMap<JigsawSummonNodeSocketType, JigsawSummonNodeSocket> tt = new HashMap<>();
            tt.put(jsns.getType(), jsns);
        } else
            t.put(jsns.getType(), jsns);
    }

    @SafeVarargs
    public final void register(Supplier<JigsawSummonNodeSocket>... suppliers) {
        for (Supplier<JigsawSummonNodeSocket> supplier : suppliers) {
            register(supplier);
        }
    }

    @SafeVarargs
    public final void register(JigsawSummonNodeSocket... jigsawSummonNodeSockets) {
        for (JigsawSummonNodeSocket jigsawSummonNodeSocket : jigsawSummonNodeSockets) {
            register(jigsawSummonNodeSocket);
        }
    }
}
