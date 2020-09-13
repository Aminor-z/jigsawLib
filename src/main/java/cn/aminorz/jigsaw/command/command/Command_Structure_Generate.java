package cn.aminorz.jigsaw.command.command;

import cn.aminorz.jigsaw.command.thread.CommandThread;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import static cn.aminorz.jigsaw.command.lib.JigsawCommandLib.getCurrentBlockPos;

public class Command_Structure_Generate implements Command<CommandSource> {
    public static Command_Structure_Generate instance = new Command_Structure_Generate();

    //TODO:完成指令生成建筑
    @Override
    public int run(CommandContext<CommandSource> context) {
        if (Command_Multithreading.state())
            CommandThread.submit(() -> {
                fun(context);
            });
        else
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
            stc = new StringTextComponent("[Generate-Error]: Structure not find.");
            source.sendErrorMessage(stc);
            return;
        }
        stc = new StringTextComponent("[Generate]: Start generate [");
        stc.appendText(target).appendText("] At ChunkPos ").appendText(currentChunkPos.toString());
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
        context.getSource().sendFeedback(new StringTextComponent("[Generate-Step 1]Init structure..."), false);
        StructureStart structureStart = structure.getStartFactory().create(structure, currentChunkPos.x, currentChunkPos.z, MutableBoundingBox.getNewBoundingBox(), i, chunkGenerator.getSeed());
        structureStart.init(chunkGenerator, templateManager, currentChunkPos.x, currentChunkPos.z, biome);
        StructureStart resultStructureStart = structureStart.isValid() ? structureStart : StructureStart.DUMMY;
        long step1_endtime=System.currentTimeMillis();
        context.getSource().sendFeedback(new StringTextComponent("[Generate-Step 1]: Finish in "+String.valueOf(step1_endtime-startTime)+" ms."), false);
        if (resultStructureStart != StructureStart.DUMMY) {
            int size = resultStructureStart.getComponents().size();
            if (size > 30) {
                context.getSource().sendFeedback(
                        new StringTextComponent("")
                                .applyTextStyle(TextFormatting.RED).appendText("[Generate-Warning]: The amount of pieces is reach upper to ")
                                .applyTextStyle(TextFormatting.GOLD).appendText(String.valueOf(size))
                                .applyTextStyle(TextFormatting.RED).appendText(". It might take some time..."
                        ), false
                );
            }
            context.getSource().sendFeedback(new StringTextComponent("[Generate-Step 2]: Strat put blocks to world..."), false);
            generateStructure(structureStart.getComponents(), world, chunkGenerator, sharedseedrandom, structureStart.getBoundingBox(), currentChunkPos);
            context.getSource().sendFeedback(new StringTextComponent("[Generate-Step 2]: Finish in "+String.valueOf(System.currentTimeMillis()-step1_endtime)+" ms."), false);
            context.getSource().sendFeedback(new StringTextComponent("").applyTextStyle(TextFormatting.GREEN).appendText("[Generate]: Success."),false) ;

        } else {
            context.getSource().sendErrorMessage(new StringTextComponent("[Generate-Error]: Can not generate [" + target + "] because the structure think the current position is invalid."));
        }
        context.getSource().sendFeedback(new StringTextComponent("[Generate]: Finished in " +(System.currentTimeMillis()-startTime) + " ms"),false);
        source.sendFeedback(stc, false);
    }

    private void generateStructure(List<StructurePiece> components, ServerWorld serverWorld, ChunkGenerator<?> chunkGenerator, Random random, MutableBoundingBox mutableBoundingBox, ChunkPos chunkPos) {
        //synchronized (components) {
        for (StructurePiece structurepiece : components)
            structurepiece.create(serverWorld, chunkGenerator, random, mutableBoundingBox, chunkPos);
        // }
    }
}

