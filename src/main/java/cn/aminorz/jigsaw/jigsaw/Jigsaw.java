package cn.aminorz.jigsaw.jigsaw;

import cn.aminorz.jigsaw.util.math.JigsawSectionPos;
import javafx.util.Pair;

import java.util.*;
import java.util.function.Supplier;

/**
 *
 * @param <T> the Vec3i type
 */
public abstract class Jigsaw<T> {
    private final JigsawStructureGenerator jigsawStructureGenerator;
    public void print(ArrayList<Pair<T, IJigsawPiece>> key){
        System.out.println("cn.aminorz.jigsaw.Jigsaw["+this.getClass().getName()+"] print jigsawPool data:{");
        key.sort(new Comparator<Pair<T, IJigsawPiece>>() {

            @Override
            public int compare(Pair<T, IJigsawPiece> o1, Pair<T, IJigsawPiece> o2) {
                return o1.getKey().hashCode() - o2.getKey().hashCode();
            }
        });
        for (Pair<T, IJigsawPiece> i : key) {
            System.out.println("\t"+i.getKey().toString()+" => "+i.getValue().getClass().getSimpleName());
        }
        System.out.println("} => " +key.size()+" items.");
    }
    public Jigsaw(JigsawStructureGenerator jigsawStructureGenerator) {
        this.jigsawStructureGenerator = jigsawStructureGenerator;
    }

    public <V extends IJigsawPattern> ArrayList<Pair<T, IJigsawPiece>> generate(int x, int y, int z, Supplier<V> beginPattern) {
        jigsawStructureGenerator.generate(JigsawSectionPos.ZERO, beginPattern.get());
        HashMap<JigsawSectionPos, IJigsawPiece> t = jigsawStructureGenerator.getJigsawMapState().getMapState();
        Set<Map.Entry<JigsawSectionPos, IJigsawPiece>> entrySet=t.entrySet();
        ArrayList<Pair<T, IJigsawPiece>> result=new ArrayList<>();
        for(Map.Entry<JigsawSectionPos, IJigsawPiece> entry : entrySet)
            result.add(new Pair<>(getActualPos(entry.getKey(), x, y, z), entry.getValue()));
        return result;
    }

    /**
     * @param jigsawSectionPos
     * @param x                start x
     * @param y                start y
     * @param z                start z
     * @return actual pos
     */
    public abstract T getActualPos(JigsawSectionPos jigsawSectionPos, int x, int y, int z);
}
