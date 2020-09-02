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
            register(()->iJigsawPattern);
        }
    }
    public JigsawPool() {
        jigsawPatterns = new LinkedList<>();
    }
    @SafeVarargs
    public JigsawPool(Supplier<IJigsawPattern>... suppliers) {
        jigsawPatterns = new LinkedList<>();
        for (Supplier<IJigsawPattern> supplier : suppliers) {
            register(supplier);
        }
    }
    public JigsawPool(LinkedList<IJigsawPattern> jigsawPatterns) {
        this.jigsawPatterns = jigsawPatterns;
    }

    public HashSet<Class<? extends IJigsawPattern>> getRegisteredJigsawPiece() {
        return registeredJigsawPiece;
    }

    public Map<JigsawSummonNode, HashSet<JigsawSummonNodeSocket>> getReflectJMSP() {
        return reflectJMSP;
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    private void preCheck() {
        Iterator<IJigsawPattern> iter = jigsawPatterns.iterator();
        while (iter.hasNext()) {
            IJigsawPattern source =iter.next();
            if (source.getOccupiedSectionPool().size() == 0) {
                jigsawPatterns.remove(iter);
                System.out.println(source.getClass().getSimpleName() + " no occupied section -> removed");
            } else if (source.getSummonNodes().size() == 0 && source.getSummonNodeSocketPool().size() == 0) {
                jigsawPatterns.remove(iter);
                System.out.println(source.getClass().getSimpleName() + " no way to summon this -> removed");
            }
        }
    }

    public void init() {
        for (IJigsawPattern source : jigsawPatterns) {
            HashSet<JigsawSummonNode> sourceSummonNodes = source.getSummonNodes();
            for (JigsawSummonNode sourceSummonNode : sourceSummonNodes) {
                for (IJigsawPattern target : jigsawPatterns) {
                    JigsawSummonNodeSocket jigsawSummonNodeSocket = target.getSummonNodeSocketPool().get(sourceSummonNode.getJigsawSummonNodeType());
                    if (jigsawSummonNodeSocket != null&&jigsawSummonNodeSocket.getSimpleDirection().equals(sourceSummonNode.getSimpleDirection().getOpposite())) {
                        JigsawSectionPos offsetSectionPos =
                                sourceSummonNode.getNodeSectionPos().add(sourceSummonNode.getSimpleDirection());
                        //sourceSummonNode.getNodeSectionPos().minus(jigsawSummonNodeSocket.getSocketSectionPos());
                        //position crash check
                        boolean positionCrashCheck = true;
                        boolean vaildTypeCheck = true;
                        for (JigsawSectionPos targetSectionPos : target.getOccupiedSectionPool().keySet()) {
                            if (source.getOccupiedSectionPool().containsKey(targetSectionPos.add(offsetSectionPos))) {
                                positionCrashCheck = false;
                                break;
                            }
                            JigsawSide t = target.getSidePool().get(targetSectionPos);
                            if (t == null) continue;
                            for (Map.Entry<SimpleDirection, JigsawSideType> targetType : t.entrySet()) {
                                //summonNode.Direction-summonNodeSocket.Direction==0;
                                JigsawSide ttt = source.getSidePool().get(targetSectionPos.add(offsetSectionPos));
                                if (ttt != null) {
                                    JigsawSideType qwq = ttt.get(targetType.getKey().getOpposite());
                                    if (qwq != null) {
                                        if (!qwq.getValidSideTypes().contains(targetType.getValue())) {
                                            vaildTypeCheck = false;
                                            break;
                                        }
                                    }
                                }
                                break;
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

    public void register(Supplier<IJigsawPattern> supplier) {
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