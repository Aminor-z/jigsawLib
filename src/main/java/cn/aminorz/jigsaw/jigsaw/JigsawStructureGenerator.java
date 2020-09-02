package cn.aminorz.jigsaw.jigsaw;

import cn.aminorz.jigsaw.exception.JigsawTypeException;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawOccupiedSectionPool;
import cn.aminorz.jigsaw.util.WeightRandomItem;
import cn.aminorz.jigsaw.util.WeightedRandom;
import cn.aminorz.jigsaw.util.math.JigsawSectionPos;
import cn.aminorz.jigsaw.util.math.SimpleDirection;
import javafx.util.Pair;
import cn.aminorz.jigsaw.exception.JigsawUnsetException;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class JigsawStructureGenerator implements IJigsawInitializable {
    private JigsawPool jigsawPool;
    /**
     * <P>On DFS mode: the depth limit of DFS.</P>
     * <P>On BFS mode: the summon limit of BFS.</P>
     */
    private Integer limit;
    private JigsawMapState jigsawMapState = new JigsawMapState();
    private Mode mode = Mode.NONE;
    /**
     * If make it on, you must promise that the DFS or Mode will not become an endless loop.
     */
    private boolean terminatorChecker = false;

    public JigsawStructureGenerator() {
    }

    public JigsawStructureGenerator(JigsawPool jigsawPool) {
        jigsawPool.init();
        this.jigsawPool = jigsawPool;
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

    public void BFS_generate(JigsawSectionPos startPos, IJigsawPattern startPattern) {
        Queue<Pair<JigsawSectionPos, IJigsawPattern>> BFS_QUEUE = new LinkedList<>();
        jigsawMapState.add(startPos, startPattern);
        BFS_QUEUE.add(new Pair<>(startPos, startPattern));
        int counter = 1;
        do {
            Pair<JigsawSectionPos, IJigsawPattern> BFS_pair = BFS_QUEUE.poll();
            JigsawSectionPos currentPos = BFS_pair.getKey();
            IJigsawPattern currentPattern = BFS_pair.getValue();
            for (JigsawSummonNode summonNode : currentPattern.getSummonNodes()) {
                LinkedList<WeightRandomItem<Pair<JigsawSectionPos, IJigsawPattern>>> validPatterns = getValidPatterns(summonNode, currentPos);
                WeightedRandom<Pair<JigsawSectionPos, IJigsawPattern>> weightedRandom = new WeightedRandom<>(validPatterns);
                if (weightedRandom.getCategory().size() != 0) {
                    Pair<JigsawSectionPos, IJigsawPattern> patternPair = weightedRandom.getRandomObj();
                    if (patternPair != null) {
                        //offset pos patternPair.getKey();
                        //actual pos(start point) startPos.add(patternPair.getKey()).add(summonNode.getSimpleDirection());
                        JigsawSectionPos actualPos = currentPos.add(patternPair.getKey()).add(summonNode.getSimpleDirection());
                        jigsawMapState.add(actualPos, patternPair.getValue());
                        ++counter;
                        for (JigsawSummonNode nextSummonNode : patternPair.getValue().getSummonNodes()) {
                            //next start point(startPos)
                            JigsawSectionPos nextCurrentPos = actualPos.add(nextSummonNode.getNodeSectionPos());
                            if (terminatorChecker && nextSummonNode.isTerminator() && counter == limit) {
                                BFS_QUEUE.add(new Pair<>(nextCurrentPos, patternPair.getValue()));
                            } else if (counter <= limit) {
                                BFS_QUEUE.add(new Pair<>(nextCurrentPos, patternPair.getValue()));
                            }
                        }
                    }
                }
            }
        } while ((BFS_QUEUE.size() != 0));
    }

    public void DFS_generateStarter(JigsawSectionPos startPos, IJigsawPattern startPattern) {
        jigsawMapState.add(startPos, startPattern);
        for (JigsawSummonNode summonNode : startPattern.getSummonNodes()) {
            LinkedList<WeightRandomItem<Pair<JigsawSectionPos, IJigsawPattern>>> validPatterns = getValidPatterns(summonNode, startPos);
            WeightedRandom<Pair<JigsawSectionPos, IJigsawPattern>> weightedRandom = new WeightedRandom<>(validPatterns);
            if (weightedRandom.getCategory().size() != 0) {
                Pair<JigsawSectionPos, IJigsawPattern> patternPair = weightedRandom.getRandomObj();
                if (patternPair != null) {
                    //offset pos patternPair.getKey();
                    //actual pos(start point) startPos.add(patternPair.getKey()).add(summonNode.getSimpleDirection());
                    JigsawSectionPos actualPos = startPos.add(patternPair.getKey()).add(summonNode.getSimpleDirection());
                    jigsawMapState.add(actualPos, patternPair.getValue());
                    for (JigsawSummonNode nextSummonNode : patternPair.getValue().getSummonNodes()) {
                        //next start point(startPos)
                        JigsawSectionPos nextCurrentPos = actualPos.add(nextSummonNode.getNodeSectionPos());
                        DFS_generate(nextCurrentPos, patternPair.getValue(), 1);
                    }
                }
            }
        }
    }

    public void DFS_generate(JigsawSectionPos currentPos, IJigsawPattern currentPattern, final int depth) {
        if (depth > limit)
            return;
        for (JigsawSummonNode summonNode : currentPattern.getSummonNodes()) {
            LinkedList<WeightRandomItem<Pair<JigsawSectionPos, IJigsawPattern>>> validPatterns = getValidPatterns(summonNode, currentPos);
            WeightedRandom<Pair<JigsawSectionPos, IJigsawPattern>> weightedRandom = new WeightedRandom<>(validPatterns);
            if (weightedRandom.getCategory().size() != 0) {
                Pair<JigsawSectionPos, IJigsawPattern> patternPair = weightedRandom.getRandomObj();
                if (patternPair != null) {
                    //offset pos patternPair.getKey();
                    //actual pos(start point) startPos.add(patternPair.getKey()).add(summonNode.getSimpleDirection());
                    JigsawSectionPos actualPos = currentPos.add(patternPair.getKey()).add(summonNode.getSimpleDirection());
                    jigsawMapState.add(actualPos, patternPair.getValue());
                    for (JigsawSummonNode nextSummonNode : patternPair.getValue().getSummonNodes()) {
                        //next start point(startPos)
                        JigsawSectionPos nextCurrentPos = actualPos.add(nextSummonNode.getNodeSectionPos());
                        if (terminatorChecker && nextSummonNode.isTerminator() && depth == limit) {
                            DFS_generate(nextCurrentPos, patternPair.getValue(), depth);
                        } else {
                            DFS_generate(nextCurrentPos, patternPair.getValue(), depth + 1);
                        }
                    }
                }
            }
        }
    }

    /**
     * @param jigsawSummonNode
     * @param jigsawSectionPos summon pos
     * @return JigsawSectionPos is the offset pos
     */
    private LinkedList<WeightRandomItem<Pair<JigsawSectionPos, IJigsawPattern>>> getValidPatterns(JigsawSummonNode jigsawSummonNode, JigsawSectionPos jigsawSectionPos) {
        LinkedList<WeightRandomItem<Pair<JigsawSectionPos, IJigsawPattern>>> validPattern = new LinkedList<>();
        JigsawSummonNodeSocket[] jigsawSummonNodeSockets = jigsawPool.getReflectJMSP().get(jigsawSummonNode).toArray(new JigsawSummonNodeSocket[0]);
        for (JigsawSummonNodeSocket jigsawSummonNodeSocket : jigsawSummonNodeSockets) {
            boolean valid = true;
            JigsawSectionPos jigsawSummonNodeSocketPos = jigsawSectionPos.minus(jigsawSummonNodeSocket.getSocketSectionPos());
            JigsawOccupiedSectionPool occupiedSections = jigsawSummonNodeSocket.getJigsawPattern().getOccupiedSectionPool();
            for (JigsawSectionPos sectionPos : occupiedSections.keySet()) {
                if (jigsawMapState.getMapState().containsKey(jigsawSectionPos.add(jigsawSummonNode.getSimpleDirection()).add(jigsawSummonNodeSocketPos).add(sectionPos))) {
                    valid = false;
                    break;
                }
            }
            if (!valid) break;
            Set<Map.Entry<JigsawSectionPos, JigsawSide>> sides = jigsawSummonNodeSocket.getJigsawPattern().getSidePool().entrySet();
            for (Map.Entry<JigsawSectionPos, JigsawSide> jigsawSectionPosJigsawSideEntry : sides) {
                JigsawSectionPos sectionPos = jigsawSectionPosJigsawSideEntry.getKey();
                Map<SimpleDirection, JigsawSideType> simpleDirectionJigsawSideTypeMap = jigsawSectionPosJigsawSideEntry.getValue();
                for (Map.Entry<SimpleDirection, JigsawSideType> simpleDirectionJigsawSideTypeEntry : simpleDirectionJigsawSideTypeMap.entrySet()) {
                    JigsawSectionPos targetPos = jigsawSummonNodeSocketPos.add(sectionPos).add(simpleDirectionJigsawSideTypeEntry.getKey());
                    if (jigsawMapState.getMapState().containsKey(targetPos))
                    //if directed section pos has piece;
                    {
                        //check type
                        JigsawSide jigsawSide = jigsawMapState.getMapState().get(targetPos).getJigsawSide();
                        if (jigsawSide != null) {
                            JigsawSideType jigsawSideType = jigsawSide.get(simpleDirectionJigsawSideTypeEntry.getKey());
                            if (jigsawSideType != null) {
                                if (!jigsawSideType.getValidSideTypes().contains(simpleDirectionJigsawSideTypeEntry.getValue())) {
                                    valid = false;
                                    break;
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
                                        jigsawSummonNodeSocket.getSocketSectionPos().getMinus(),
                                        jigsawSummonNodeSocket.getJigsawPattern()
                                )
                                , jigsawSummonNodeSocket.getWeight()
                        )
                );
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
