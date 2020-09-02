package cn.aminorz.jigsaw.jigsaw.pool;

import cn.aminorz.jigsaw.jigsaw.JigsawSummonNodeSocket;
import cn.aminorz.jigsaw.jigsaw.JigsawSummonNodeSocketType;

import java.util.HashMap;
import java.util.function.Supplier;

public class JigsawSummonNodeSocketPool extends HashMap<JigsawSummonNodeSocketType, JigsawSummonNodeSocket> {
    public void register(JigsawSummonNodeSocket jigsawSummonNodeSocket) {
        put(jigsawSummonNodeSocket.getType(), jigsawSummonNodeSocket);
    }

    public void register(Supplier<JigsawSummonNodeSocket> supplier) {
        put(supplier.get().getType(), supplier.get());
    }

    @SafeVarargs
    public final <TJigsawSummonNodeSocket> void register(Supplier<JigsawSummonNodeSocket>... suppliers) {
        for (Supplier<JigsawSummonNodeSocket> supplier : suppliers) {
            register(supplier);
        }
    }

    @SafeVarargs
    public final <TJigsawSummonNodeSocket> void register(JigsawSummonNodeSocket... jigsawSummonNodeSockets) {
        for (JigsawSummonNodeSocket jigsawSummonNodeSocket : jigsawSummonNodeSockets) {
            register(jigsawSummonNodeSocket);
        }
    }
}
