package cn.aminorz.jigsaw.command.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import it.unimi.dsi.fastutil.longs.LongSet;
import net.minecraft.command.CommandSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.StringTextComponent;

import java.util.Map;

import static cn.aminorz.jigsaw.command.lib.JigsawCommandLib.getCurrentBlockPos;

public class Command_Structure_List implements Command<CommandSource> {
    public static cn.aminorz.jigsaw.command.command.Command_Structure_List instance = new cn.aminorz.jigsaw.command.command.Command_Structure_List();
    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        BlockPos currentBlockPos=getCurrentBlockPos(context);
        ChunkPos currentChunkPos = new ChunkPos(currentBlockPos.getX() >> 4,currentBlockPos.getZ() >> 4);
        Map<String, LongSet> map =
                context.getSource().getWorld()
                        .getChunk(currentChunkPos.x,
                                currentChunkPos.z)
                        .getStructureReferences();
        StringTextComponent stc = new StringTextComponent("Chunk Structure info:\n");
        boolean hasElement = false;
        for (Map.Entry<String, LongSet> stringgetStructureReferencesEntry : map.entrySet()) {
            if (stringgetStructureReferencesEntry.getValue().size() != 0) {
                hasElement = true;
                stc.appendText("\t"+stringgetStructureReferencesEntry.getKey() + " " + stringgetStructureReferencesEntry.getValue() + ",\n");
            }
        }
        if (!hasElement) stc.appendText("No Structure here.\n");
        stc.appendText("   At Chunk Pos:"+currentChunkPos.toString()+ "\n");
        stc.appendText("   At Biome :" + context.getSource().getWorld().getBiome(currentBlockPos).getRegistryName());
        context.getSource().sendFeedback(stc, false);
        return 0;
    }
}

