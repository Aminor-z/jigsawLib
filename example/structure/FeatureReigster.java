package com.aminor.civilizatus.world.gen.structure;

import com.aminor.civilizatus.world.gen.structure.core.JigsawCore;
import com.aminor.civilizatus.world.gen.structure.core.PlankRoad;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FeatureReigster {
    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, "civilizatus");
    public static final Structure<NoFeatureConfig> CITY = (Structure) register("city", new JigsawCore(NoFeatureConfig::deserialize));
    public static final Structure<NoFeatureConfig> PLANK_ROAD = (Structure) register("plank_road", new PlankRoad(NoFeatureConfig::deserialize));
    static {
        for (Biome biome : ForgeRegistries.BIOMES) {
            biome.addStructure(CITY.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
            biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, CITY.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
        }
    }

    private static <C extends IFeatureConfig, F extends Feature<C>> F register(String p_214468_0_, F p_214468_1_) {
        return (F) Registry.register(Registry.FEATURE, p_214468_0_, p_214468_1_);
    }
}
