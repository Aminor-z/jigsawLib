package cn.aminorz.jigsaw.jigsaw;

import cn.aminorz.jigsaw.util.math.JigsawSectionPos;
import cn.aminorz.jigsaw.util.math.SimpleDirection;

import java.util.*;
import java.util.function.Supplier;


public class JigsawPool implements IJigsawInitializable {
    private HashSet<Class<? extends IJigsawPattern>> registeredJigsawPiece = new HashSet<>();
    private HashMap<JigsawSummonNode, HashSet<JigsawSummonNodeSocket>> reflectJMSP = new HashMap<>();
    private LinkedList<IJigsawPattern> jigsawPatterns;

    public JigsawPool(List<IJigsawPattern> list) {
        jigsawPatterns = new LinkedList<>();
        for (IJigsawPattern iJigsawPattern : list) {
            register(() -> iJigsawPattern);
        }
    }

    public JigsawPool() {
        jigsawPatterns = new LinkedList<>();
    }

    @SafeVarargs
    public JigsawPool(Supplier<? extends IJigsawPattern>... suppliers) {
        jigsawPatterns = new LinkedList<>();
        for (Supplier<? extends IJigsawPattern> supplier : suppliers) {
            register(supplier);
        }
    }

    public JigsawPool(LinkedList<IJigsawPattern> jigsawPatterns) {
        this.jigsawPatterns = jigsawPatterns;
    }

    public LinkedList<IJigsawPattern> getJigsawPatterns() {
        return jigsawPatterns;
    }

    public HashSet<Class<? extends IJigsawPattern>> getRegisteredJigsawPiece() {
        return registeredJigsawPiece;
    }

    public Map<JigsawSummonNode, HashSet<JigsawSummonNodeSocket>> getReflectJMSP() {
        return reflectJMSP;
    }

    private void preCheck() {
        Iterator<IJigsawPattern> iter = jigsawPatterns.iterator();
        while (iter.hasNext()) {
            IJigsawPattern source = iter.next();
            if (source.getOccupiedSectionPool().isEmpty()) {
                jigsawPatterns.remove(iter);
            } else if (source.getSummonNodes().isEmpty() && source.getSummonNodeSocketPool().isEmpty()) {
                jigsawPatterns.remove(iter);
            }
        }
    }

    public void init() {
        preCheck();
        for (IJigsawPattern source : jigsawPatterns) {
            HashSet<JigsawSummonNode> sourceSummonNodes = source.getSummonNodes();
            for (JigsawSummonNode sourceSummonNode : sourceSummonNodes) {
                for (IJigsawPattern target : jigsawPatterns) {
                    HashMap<JigsawSummonNodeSocketType, JigsawSummonNodeSocket> jigsawSummonNodeSockets = target.getSummonNodeSocketPool().get(sourceSummonNode.getSimpleDirection().getOpposite());
                    //JigsawSummonNodeSocket jigsawSummonNodeSocket = target.getSummonNodeSocketPool().get(sourceSummonNode.getJigsawSummonNodeType());
                    if (jigsawSummonNodeSockets != null) {
                        JigsawSummonNodeSocket jigsawSummonNodeSocket = jigsawSummonNodeSockets.get(sourceSummonNode.getJigsawSummonNodeType());
                        if(jigsawSummonNodeSocket!=null)
                        {
                            //TODO:TEST
                            /*JigsawSectionPos offsetSectionPos =
                                    sourceSummonNode.getNodeSectionPos().add(sourceSummonNode.getSimpleDirection());*/
                            JigsawSectionPos offsetSectionPos =
                                    sourceSummonNode.getNodeSectionPos().add(sourceSummonNode.getSimpleDirection()).subtract(jigsawSummonNodeSocket.getSocketSectionPos());
                            //sourceSummonNode.getNodeSectionPos().minus(jigsawSummonNodeSocket.getSocketSectionPos());
                            //position crash check
                            boolean positionCrashCheck = true;
                            boolean vaildTypeCheck = true;
                            for (JigsawSectionPos targetSectionPos : target.getOccupiedSectionPool().keySet()) {
                                if (source.getOccupiedSectionPool().containsKey(targetSectionPos.add(offsetSectionPos))) {
                                    positionCrashCheck = false;
                                    break;
                                }
                                IJigsawPiece _t = target.getOccupiedSectionPool().get(targetSectionPos);
                                if (_t == null) continue;
                                JigsawSide t = _t.getJigsawSide();
                                if (t == null) continue;
                                for (Map.Entry<SimpleDirection, JigsawSideType> targetType : t.entrySet()) {
                                    //summonNode.Direction-summonNodeSocket.Direction==0;
                                    //JigsawSide ttt = source.getSidePool().get(targetSectionPos.add(offsetSectionPos));
                                    IJigsawPiece _ttt = source.getOccupiedSectionPool().get(targetSectionPos.add(offsetSectionPos));
                                    if (_ttt != null) {
                                        JigsawSide ttt = _ttt.getJigsawSide();
                                        if (ttt != null) {
                                            JigsawSideType qwq = ttt.get(targetType.getKey().getOpposite());
                                            if (qwq != null) {
                                                if (!qwq.getValidSideTypes().contains(targetType.getValue())) {
                                                    vaildTypeCheck = false;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!vaildTypeCheck) break;
                            }
                            if (positionCrashCheck && vaildTypeCheck) {
                                HashSet<JigsawSummonNodeSocket> t = reflectJMSP.get(sourceSummonNode);
                                if (t != null)
                                    t.add(jigsawSummonNodeSocket);
                                else {
                                    HashSet<JigsawSummonNodeSocket> tt = new HashSet<JigsawSummonNodeSocket>();
                                    tt.add(jigsawSummonNodeSocket);
                                    reflectJMSP.put(sourceSummonNode, tt);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void register(Supplier<? extends IJigsawPattern> supplier) {
        jigsawPatterns.add(supplier.get());
        registeredJigsawPiece.add(supplier.get().getClass());
    }
    /*
    public <T> void JigsawMultiSectionPieceRegister(@NotNull JigsawMultiSectionPiece jigsawMultiSectionPiece) {
        for (Class<? extends JigsawPiece> value : jigsawMultiSectionPiece.getOccupiedSections().values()) {
            if (!registedJigsawPiece.contains(value)) {
                Registry.register(Registry.STRUCTURE_PIECE, getModName() + ":" + value.getSimpleName(), (templateManager, nbt) -> {
                    JigsawPiece t = null;
                    //noinspection finally
                    try {
                        t = value.getDeclaredConstructor(TemplateManager.class, CompoundNBT.class)
                                .newInstance(templateManager, nbt);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                    } finally {
                        //noinspection ReturnInsideFinallyBlock
                        return t;
                    }
                });
                registedJigsawPiece.add(value);
            }
        }
    }*/
}