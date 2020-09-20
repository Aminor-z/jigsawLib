package cn.aminorz.jigsaw.jigsaw;

import cn.aminorz.jigsaw.exception.JigsawTypeException;
import cn.aminorz.jigsaw.exception.JigsawUnsetException;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawOccupiedSectionPool;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawSummonNodePool;
import cn.aminorz.jigsaw.util.math.JigsawSectionPos;
import cn.aminorz.jigsaw.util.math.SimpleDirection;
import cn.aminorz.jigsaw.util.random.WeightRandomItem;
import cn.aminorz.jigsaw.util.random.WeightedRandom;
import javafx.util.Pair;

import java.util.*;
import java.util.function.Function;


public class JigsawStructureGenerator implements IJigsawInitializable {
    private JigsawPool jigsawPool;
    /**
     * <P>On DFS mode: the depth limit of DFS.</P>
     * <P>On BFS mode: the summon limit of BFS.</P>
     */
    private Integer limit;
    private JigsawMapState jigsawMapState = new JigsawMapState();
    private Mode mode = Mode.NONE;
    private Function<List<WeightRandomItem<Pair<JigsawSectionPos, IJigsawPattern>>>, Pair<JigsawSectionPos, IJigsawPattern>> WeightRandomFunction;
    /**
     * If make it on, you must promise that the DFS or Mode will not become an endless loop.
     */
    private boolean terminatorChecker = false;
    private Function<List<WeightRandomItem<Pair<JigsawSectionPos, IJigsawPattern>>>, Pair<JigsawSectionPos, IJigsawPattern>> defaultWeightRandomFunction = weightRandomItems -> {
        return new WeightedRandom<>(weightRandomItems).getRandomObj();
    };

    public JigsawStructureGenerator() {
        WeightRandomFunction = defaultWeightRandomFunction;
    }

    public JigsawStructureGenerator(JigsawPool jigsawPool) {
        jigsawPool.init();
        this.jigsawPool = jigsawPool;
        WeightRandomFunction = defaultWeightRandomFunction;
    }

    public JigsawStructureGenerator setWeightRandomFunction(Function<List<WeightRandomItem<Pair<JigsawSectionPos, IJigsawPattern>>>, Pair<JigsawSectionPos, IJigsawPattern>> weightRandomFunction) {
        this.WeightRandomFunction = weightRandomFunction;
        return this;
    }

    /**
     * If make it on, you must promise that the  DFS or BFS will not become an endless loop.
     */
    public JigsawStructureGenerator setTerminatorChecker(boolean terminatorChecker) {
        this.terminatorChecker = terminatorChecker;
        return this;
    }

    public Mode getMode() {
        return mode;
    }

    public JigsawStructureGenerator setMode(Mode mode) {
        try {
            if (mode.equals(Mode.NONE))
                throw new JigsawTypeException(mode);
        } catch (JigsawTypeException e) {
            e.printStackTrace();
        }
        this.mode = mode;
        return this;
    }

    public JigsawMapState getJigsawMapState() {
        return jigsawMapState;
    }

    public JigsawStructureGenerator reset() {
        jigsawMapState = null;
        jigsawMapState = new JigsawMapState();
        return this;
    }

    public JigsawPool getJigsawPool() {
        return jigsawPool;
    }

    public JigsawStructureGenerator setJigsawPool(JigsawPool jigsawPool) {
        this.jigsawPool = jigsawPool;
        this.jigsawPool.init();
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public JigsawStructureGenerator setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public <T extends IJigsawPattern> void generate(JigsawSectionPos startPos, T startPattern) {
        try {
            if (jigsawPool == null)
                throw new JigsawUnsetException(this.getClass(), "jigsawPool");
            if (mode == Mode.NONE)
                throw new JigsawUnsetException(this.getClass(), "mode");
            if (limit == null)
                throw new JigsawUnsetException(this.getClass(), "depthLimit");
            switch (mode) {
                case DFS:
                    DFS_generateStarter(startPos, startPattern);
                    break;
                case BFS:
                    BFS_generate(startPos, startPattern);
                    break;
            }
        } catch (JigsawUnsetException e) {
            e.printStackTrace();
        }
    }

    private void BFS_generate(JigsawSectionPos startPos, IJigsawPattern startPattern) {
        Queue<Pair<JigsawSectionPos, IJigsawPattern>> BFS_QUEUE = new LinkedList<>();
        jigsawMapState.register(startPos, startPattern);
        BFS_QUEUE.add(new Pair<>(startPos, startPattern));
        int counter = 1;
        do {
            Pair<JigsawSectionPos, IJigsawPattern> BFS_pair = BFS_QUEUE.poll();
            JigsawSectionPos currentPos = BFS_pair.getKey();
            IJigsawPattern currentPattern = BFS_pair.getValue();
            if (currentPattern.getSummonNodes() != null)
                for (JigsawSummonNode summonNode : currentPattern.getSummonNodes()) {
                    List<WeightRandomItem<Pair<JigsawSectionPos, IJigsawPattern>>> validPatterns = getValidPatterns(summonNode, currentPos);
                    if (validPatterns.size() != 0) {
                        Pair<JigsawSectionPos, IJigsawPattern> patternPair = WeightRandomFunction.apply(validPatterns);
                        if (patternPair != null) {
                            IJigsawPattern iJigsawPattern = patternPair.getValue();
                            JigsawSectionPos actualPos = currentPos.add(patternPair.getKey());
                            jigsawMapState.register(actualPos, iJigsawPattern);
                            ++counter;
                            JigsawSummonNodePool nextSummonNodes = iJigsawPattern.getSummonNodes();
                            if (nextSummonNodes != null)
                                for (JigsawSummonNode nextSummonNode : nextSummonNodes) {
                                    //next start point(startPos)
                                    //JigsawSectionPos nextCurrentPos = actualPos.add(nextSummonNode.getNodeSectionPos()).add(nextSummonNode.getSimpleDirection());
                                    JigsawSectionPos nextCurrentPos = actualPos;
                                    if (terminatorChecker && nextSummonNode.isTerminator() && counter == limit) {
                                        BFS_QUEUE.add(new Pair<>(nextCurrentPos, iJigsawPattern));
                                    } else if (counter <= limit) {
                                        BFS_QUEUE.add(new Pair<>(nextCurrentPos, iJigsawPattern));
                                    }
                                }
                        }
                    }
                }
        } while ((BFS_QUEUE.size() != 0));
    }

    private void DFS_generateStarter(JigsawSectionPos startPos, IJigsawPattern startPattern) {
        jigsawMapState.register(startPos, startPattern);
        if (startPattern.getSummonNodes() != null)
            for (JigsawSummonNode summonNode : startPattern.getSummonNodes()) {
                List<WeightRandomItem<Pair<JigsawSectionPos, IJigsawPattern>>> validPatterns = getValidPatterns(summonNode, startPos);
                Pair<JigsawSectionPos, IJigsawPattern> patternPair = null;
                if (validPatterns.size() != 0) patternPair = WeightRandomFunction.apply(validPatterns);
                if (patternPair != null) {
                    IJigsawPattern iJigsawPattern = patternPair.getValue();
                    JigsawSectionPos socketPosition = patternPair.getValue().getSummonNodeSocketPool().get(summonNode.getSimpleDirection()).get(summonNode.getJigsawSummonNodeType()).getSocketSectionPos();
                    JigsawSectionPos actualPos = startPos.add(patternPair.getKey()).add(summonNode.getSimpleDirection()).add(socketPosition);
                    jigsawMapState.register(actualPos, iJigsawPattern);
                    JigsawSummonNodePool nextSummonNodes = iJigsawPattern.getSummonNodes();
                    if (nextSummonNodes != null)
                        for (JigsawSummonNode nextSummonNode : nextSummonNodes) {
                            //next start point(startPos)
                            JigsawSectionPos nextCurrentPos = actualPos.add(nextSummonNode.getNodeSectionPos());
                            DFS_generate(nextCurrentPos, iJigsawPattern, 1);
                        }
                }
            }
    }


    private void DFS_generate(JigsawSectionPos currentPos, IJigsawPattern currentPattern, final int depth) {
        if (depth > limit)
            return;
        if (currentPattern.getSummonNodes() != null)
            for (JigsawSummonNode summonNode : currentPattern.getSummonNodes()) {
                List<WeightRandomItem<Pair<JigsawSectionPos, IJigsawPattern>>> validPatterns = getValidPatterns(summonNode, currentPos);
                Pair<JigsawSectionPos, IJigsawPattern> patternPair = null;
                if (validPatterns.size() != 0) patternPair = WeightRandomFunction.apply(validPatterns);
                if (patternPair != null) {
                    IJigsawPattern iJigsawPattern = patternPair.getValue();
                    JigsawSectionPos socketPosition = patternPair.getValue().getSummonNodeSocketPool().get(summonNode.getSimpleDirection()).get(summonNode.getJigsawSummonNodeType()).getSocketSectionPos();
                    JigsawSectionPos actualPos = currentPos.add(patternPair.getKey()).add(summonNode.getSimpleDirection()).add(socketPosition);
                    jigsawMapState.register(actualPos, iJigsawPattern);
                    JigsawSummonNodePool nextSummonNodes = iJigsawPattern.getSummonNodes();
                    if (nextSummonNodes != null)
                        for (JigsawSummonNode nextSummonNode : nextSummonNodes) {
                            //next start point(startPos)
                            JigsawSectionPos nextCurrentPos = actualPos.add(nextSummonNode.getNodeSectionPos());
                            if (terminatorChecker && nextSummonNode.isTerminator() && depth == limit) {
                                DFS_generate(nextCurrentPos, iJigsawPattern, depth);
                            } else {
                                DFS_generate(nextCurrentPos, iJigsawPattern, depth + 1);
                            }
                        }
                }
            }
    }

    /**
     * @param jigsawSummonNode
     * @param currentPos       summon pos
     * @return JigsawSectionPos is the offset pos
     */
    private List<WeightRandomItem<Pair<JigsawSectionPos, IJigsawPattern>>> getValidPatterns(JigsawSummonNode jigsawSummonNode, JigsawSectionPos currentPos) {
        List<WeightRandomItem<Pair<JigsawSectionPos, IJigsawPattern>>> validPattern = new LinkedList<>();
        Set<JigsawSummonNodeSocket> t = jigsawPool.getReflectJMSP().get(jigsawSummonNode);
        if (t != null) {
            JigsawSummonNodeSocket[] jigsawSummonNodeSockets = t.toArray(new JigsawSummonNodeSocket[0]);
            for (JigsawSummonNodeSocket jigsawSummonNodeSocket : jigsawSummonNodeSockets) {
                IJigsawPattern jigsawPattern = jigsawSummonNodeSocket.getJigsawPattern();
                boolean valid = true;
                JigsawSectionPos jigsawSummonNodeSocketPos = jigsawSummonNodeSocket.getSocketSectionPos();
                if (!jigsawSummonNodeSocket.isIgnoreOccupation()) {
                    JigsawOccupiedSectionPool occupiedSections = jigsawPattern.getOccupiedSectionPool();
                    for (JigsawSectionPos sectionPos : occupiedSections.keySet()) {
                        //if (jigsawMapState.containsKey(currentPos.add(jigsawSummonNode.getSimpleDirection()).add(jigsawSummonNodeSocketPos).add(sectionPos))) {
                        //JigsawSectionPos actualPos = currentPos.add(jigsawSummonNodeSocketPos).add(summonNode.getSimpleDirection()).add(summonNode.getNodeSectionPos());
                        JigsawSectionPos transPos = currentPos.add(jigsawSummonNode.getNodeSectionPos()).add(jigsawSummonNode.getSimpleDirection()).add(sectionPos).subtract(jigsawSummonNodeSocketPos);
                        JigsawPiece piece = jigsawMapState.get(transPos);
                        if (piece != null && !piece.isVirtual()) {
                            valid = false;
                            break;
                        }
                    }
                }
                if (!valid) continue;
                Set<Map.Entry<JigsawSectionPos, JigsawPiece>> sides = jigsawPattern.getOccupiedSectionPool().entrySet();
                //Set<Map.Entry<JigsawSectionPos, JigsawSide>> sides = jigsawSummonNodeSocket.getJigsawPattern().getSidePool().entrySet();
                for (Map.Entry<JigsawSectionPos, JigsawPiece> jigsawSectionPosIJigsawPieceEntry : sides) {
                    JigsawSectionPos sectionPos = jigsawSectionPosIJigsawPieceEntry.getKey();
                    Map<SimpleDirection, HashSet<JigsawSideType>> simpleDirectionJigsawSideTypeMap = jigsawSectionPosIJigsawPieceEntry.getValue().getJigsawSide();
                    if (simpleDirectionJigsawSideTypeMap != null) {
                        for (Map.Entry<SimpleDirection, HashSet<JigsawSideType>> simpleDirectionJigsawSideTypeEntry : simpleDirectionJigsawSideTypeMap.entrySet()) {
                            JigsawSectionPos targetPos = currentPos.add(jigsawSummonNode.getNodeSectionPos()).add(jigsawSummonNode.getSimpleDirection()).add(sectionPos).subtract(jigsawSummonNodeSocketPos).add(simpleDirectionJigsawSideTypeEntry.getKey());
                            JigsawPiece targetJigsawPiece = jigsawMapState.get(targetPos);
                            if (targetJigsawPiece != null)
                            //if directed section pos has piece;
                            {
                                //check type
                                JigsawSide targetJigsawSide = targetJigsawPiece.getJigsawSide();
                                if (targetJigsawSide != null) {
                                    HashSet<JigsawSideType> jigsawSideType = targetJigsawSide.get(simpleDirectionJigsawSideTypeEntry.getKey().getOpposite());
                                    if (jigsawSideType != null) {
                                        boolean flag = false;
                                        for (JigsawSideType sideType : jigsawSideType) {
                                            if (simpleDirectionJigsawSideTypeEntry.getValue().stream().anyMatch(sourceSideType -> sideType.getValidSideTypes().contains(sourceSideType))) {
                                                flag = true;
                                            }
                                        }
                                        if (!flag) {
                                            valid = false;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!valid) break;
                }
                if (valid)
                    validPattern.add(
                            new WeightRandomItem<>(
                                    new Pair<>(
                                            jigsawSummonNodeSocketPos.getMinus().add(jigsawSummonNode.getNodeSectionPos()).add(jigsawSummonNode.getSimpleDirection()),
                                            jigsawPattern
                                    )
                                    , jigsawSummonNodeSocket.getWeight()
                            )
                    );
            }
        }
        if (validPattern.size() == 1) {
            int a = 1;
        }
        return validPattern;
    }

    @Override
    public void init() {
    }

    public enum Mode {
        NONE, DFS, BFS
    }
}
