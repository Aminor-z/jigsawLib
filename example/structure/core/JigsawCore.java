package com.aminor.civilizatus.world.gen.structure.core;

import cn.aminorz.jigsaw.jigsaw.Jigsaw;
import cn.aminorz.jigsaw.jigsaw.JigsawPool;
import cn.aminorz.jigsaw.jigsaw.JigsawStructureGenerator;
import cn.aminorz.jigsaw.jigsaw.pool.JigsawFactorPool;
import cn.aminorz.jigsaw.util.factor.DefaultLimitedFactorItem;
import cn.aminorz.jigsaw.util.math.JigsawSectionPos;
import com.aminor.civilizatus.world.gen.structure.jigsaw.pattern.Spring_1;
import com.aminor.civilizatus.world.gen.structure.jigsaw.pattern.building.house_a.House_a_e;
import com.aminor.civilizatus.world.gen.structure.jigsaw.pattern.building.house_a.House_a_n;
import com.aminor.civilizatus.world.gen.structure.jigsaw.pattern.building.house_a.House_a_s;
import com.aminor.civilizatus.world.gen.structure.jigsaw.pattern.building.house_a.House_a_w;
import com.aminor.civilizatus.world.gen.structure.jigsaw.pattern.building.shop_a.Shop_a_e;
import com.aminor.civilizatus.world.gen.structure.jigsaw.pattern.building.shop_a.Shop_a_n;
import com.aminor.civilizatus.world.gen.structure.jigsaw.pattern.building.shop_a.Shop_a_s;
import com.aminor.civilizatus.world.gen.structure.jigsaw.pattern.building.shop_a.Shop_a_w;
import com.aminor.civilizatus.world.gen.structure.jigsaw.pattern.road.Road_Cross_1;
import com.aminor.civilizatus.world.gen.structure.jigsaw.pattern.road.Road_EW;
import com.aminor.civilizatus.world.gen.structure.jigsaw.pattern.road.Road_SN;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.function.Function;

public class JigsawCore extends Structure<NoFeatureConfig> {
    public static String STRUCTURE_NAME =
            String.valueOf(new ResourceLocation("civilizatus", "city"));

    public JigsawCore(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
        super(config);
    }


    @Override
    public boolean canBeGenerated(@Nonnull BiomeManager manager, @Nonnull ChunkGenerator<?> generator, @Nonnull Random rand, int chunkX, int chunkZ, @Nonnull Biome biome) {
        int y = generator.getHeight(chunkX >> 3, chunkZ >> 3, Heightmap.Type.WORLD_SURFACE);
        if (y > 50 && y < 100) {
            return (chunkX % 10 == 0) && (chunkZ % 10 == 0);
        } else
            return false;
        //return false;
    }

    @Override
    @Nonnull
    public String getStructureName() {
        return STRUCTURE_NAME;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    @Nonnull
    public Structure.IStartFactory getStartFactory() {
        return Start::new;
    }

    public static class Start extends StructureStart {

        public Start(Structure<?> structure, int chunkPosX, int chunkPosZ, MutableBoundingBox box, int references, long seed) {
            super(structure, chunkPosX, chunkPosZ, box, references, seed);
        }

        @Override
        public void init(@Nonnull ChunkGenerator<?> generator, @Nonnull TemplateManager manager, int chunkX, int chunkZ, @Nonnull Biome biome) {
            Rotation rotation = Rotation.values()[0];
            int y = generator.getHeight(chunkX >> 4, chunkZ >> 4, Heightmap.Type.WORLD_SURFACE);
            BlockPos pos = new BlockPos(chunkX * 16, y, chunkZ * 16);
            this.components.addAll(getJigsaw().getComponents(pos.getX(), pos.getY(), pos.getZ(), Spring_1::getInstance, manager));
            this.recalculateStructureSize();
        }

        Jigsaw getJigsaw() {
            JigsawFactorPool jigsawFactorPool = new JigsawFactorPool();
            jigsawFactorPool.register(Shop_a_e::getInstance, new DefaultLimitedFactorItem(1, 0.5f));
            return new Jigsaw(
                    new JigsawStructureGenerator(
                            new JigsawPool(
                                    Spring_1::getInstance,
                                    Road_Cross_1::getInstance,
                                    Road_EW::getInstance,
                                    Road_SN::getInstance,
                                    Shop_a_e::getInstance,
                                    Shop_a_s::getInstance,
                                    Shop_a_w::getInstance,
                                    Shop_a_n::getInstance,
                                    House_a_e::getInstance,
                                    House_a_s::getInstance,
                                    House_a_w::getInstance,
                                    House_a_n::getInstance
                            ))
                            .setMode(JigsawStructureGenerator.Mode.BFS)
                            .setLimit(100)
                            .setWeightRandomFunction(jigsawFactorPool::getRandomObj)
            ) {
                @Override
                public BlockPos getActualPos(JigsawSectionPos jigsawSectionPos, int i, int i1, int i2) {
                    return new BlockPos((jigsawSectionPos.x << 3) + i, (jigsawSectionPos.y << 3) + i1, (jigsawSectionPos.z << 3) + i2);
                }
            };
        }
    }
}
