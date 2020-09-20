package com.aminor.civilizatus.world.gen.structure.jigsaw;

import com.aminor.civilizatus.world.gen.structure.jigsaw.piece.building.house_a.House_a_e_piece;
import com.aminor.civilizatus.world.gen.structure.jigsaw.piece.building.shop_a.Shop_a_e_piece;
import com.aminor.civilizatus.world.gen.structure.jigsaw.piece.plank_road.PlankRoad_EW_Piece;
import com.aminor.civilizatus.world.gen.structure.jigsaw.piece.road.Road_Cross_Cobblestone;
import com.aminor.civilizatus.world.gen.structure.jigsaw.piece.road.Road_EW_Cobblestone;
import com.aminor.civilizatus.world.gen.structure.jigsaw.piece.road.Road_SN_Cobblestone;
import com.aminor.civilizatus.world.gen.structure.jigsaw.piece.spring_1.Spring_1_all;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PieceRegister {
    public static final IStructurePieceType SPRING_1_ALL = register(Spring_1_all.class, Spring_1_all::new);
    public static final IStructurePieceType ROAD_CROSS_COBBLESTONE = register(Road_Cross_Cobblestone.class, Road_Cross_Cobblestone::new);
    public static final IStructurePieceType ROAD_EW_COBBLESTONE = register(Road_EW_Cobblestone.class, Road_EW_Cobblestone::new);
    public static final IStructurePieceType ROAD_SN_COBBLESTONE = register(Road_SN_Cobblestone.class, Road_SN_Cobblestone::new);
    public static final IStructurePieceType SHOP_A_E_PIECE = register(Shop_a_e_piece.class, Shop_a_e_piece::new);
    public static final IStructurePieceType HOUSE_A_E_PIECE = register(House_a_e_piece.class, House_a_e_piece::new);
    public static final IStructurePieceType PLANK_ROAD_PIECE = register(PlankRoad_EW_Piece.class, PlankRoad_EW_Piece::new);

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
/*
        for (Biome biome : ForgeRegistries.BIOMES) {
            biome.addStructure(CivilizatusFeature.CITY.withConfiguration(NoFeatureConfig.NO_FEATURE_CONFIG));
            biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, CivilizatusFeature.CITY.withConfiguration(NoFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
        }*/
    }


    public static <T> T register(Class<? extends StructurePiece> cls, T structure) {
        return (T) ((MutableRegistry) Registry.STRUCTURE_PIECE).register(new ResourceLocation(cls.getSimpleName().toLowerCase()), structure);
    }
}