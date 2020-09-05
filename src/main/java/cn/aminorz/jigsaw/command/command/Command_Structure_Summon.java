package cn.aminorz.jigsaw.command.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

import java.util.Locale;

import static cn.aminorz.jigsaw.command.lib.CommandLib.getCurrentBlockPos;

public class Command_Structure_Summon implements Command<CommandSource> {
    public static Command_Structure_Summon instance = new Command_Structure_Summon();

    //TODO:完成指令生成建筑
    @Override
    public int run(CommandContext<CommandSource> context) {
        fun(context);
        return 0;
    }

    private void fun(CommandContext<CommandSource> context) {

            CommandSource source = context.getSource();
            BlockPos currentBlockPos = getCurrentBlockPos(context);
            ChunkPos currentChunkPos = new ChunkPos(currentBlockPos.getX() >> 4, currentBlockPos.getZ() >> 4);
            String[] target_string_array = context.getArgument("structure_type", ResourceLocation.class).toString().toLowerCase(Locale.ROOT).split(":");
            String target = target_string_array.length > 1 ? target_string_array[1] : target_string_array[0];
            Structure<?> structure = Feature.STRUCTURES.get(target);
            StringTextComponent stc;
            if (structure == null) {
                stc = new StringTextComponent("Structure not find.");
                source.sendFeedback(stc, false);
                return;
            }
            stc = new StringTextComponent("Start generate [");
            stc.appendText(target).appendText("] At ChunkPos").appendText(currentChunkPos.toString());
            source.sendFeedback(stc, false);
            long startTime = System.currentTimeMillis();
            ServerWorld world = source.getWorld();
            Chunk chunk = world.getChunk(currentChunkPos.x, currentChunkPos.z);
            ChunkGenerator<?> chunkGenerator = world.getChunkProvider().getChunkGenerator();
            TemplateManager templateManager = world.getStructureTemplateManager();
            Biome biome = world.getBiome(currentBlockPos);
            StructureStart t = chunk.getStructureStart(structure.getStructureName());
            int i = t != null ? t.getRefCount() : 0;
            SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
            StructureStart structureStart = structure.getStartFactory().create(structure, currentChunkPos.x, currentChunkPos.z, MutableBoundingBox.getNewBoundingBox(), i, chunkGenerator.getSeed());
            structureStart.init(chunkGenerator, templateManager, currentChunkPos.x, currentChunkPos.z, biome);
            StructureStart resultStructureStart = structureStart.isValid() ? structureStart : StructureStart.DUMMY;
            chunk.putStructureStart(structure.getStructureName(), resultStructureStart);
            if (resultStructureStart != StructureStart.DUMMY) {
                structureStart.generateStructure(world, chunkGenerator, sharedseedrandom, structureStart.getBoundingBox(), currentChunkPos);
                long endTime = System.currentTimeMillis();
                long time = endTime - startTime;
                stc = new StringTextComponent("Success.\n");
                stc.appendText("Finished in " + time + " ms");
                source.sendFeedback(stc, false);
            } else {
                long endTime = System.currentTimeMillis();
                long time = endTime - startTime;
                stc = new StringTextComponent("Can not generate [" + target + "] for certain reason.\n");
                stc.appendText("Finished in " + time + " ms");
                source.sendFeedback(stc, false);

        }
    }
}

