package cn.aminorz.jigsaw.command.command;

import cn.aminorz.jigsaw.command.thread.CommandThread;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.gen.feature.template.IntegrityProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

import static cn.aminorz.jigsaw.command.lib.JigsawCommandLib.getCurrentBlockPos;
import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class Command_Pattern {
    public static final Pos_1 POS_1 = new Pos_1();
    public static final Pos_2 POS_2 = new Pos_2();
    public static final Pos_1_Set POS_1_SET = new Pos_1_Set();
    public static final Pos_2_Set POS_2_SET = new Pos_2_Set();
    public static final Create CREATE = new Create();
    public static final Load LOAD = new Load();
    public static BlockPos p1 = null;
    public static BlockPos p2 = null;

    private static void _Load(CommandContext<CommandSource> context) {
        ResourceLocation name = context.getArgument("name", ResourceLocation.class);
        context.getSource().sendFeedback(
                new StringTextComponent(
                        "[Pattern-Load]: Now start create pattern [" + name.toString() + "] ..."
                ), false
        );
        long startTime = System.currentTimeMillis();
        ServerWorld serverWorld = context.getSource().getWorld();
        TemplateManager templateManager = serverWorld.getStructureTemplateManager();
        Template template;
        template = templateManager.getTemplate(name);
        BlockPos sourcePos = getCurrentBlockPos(context);
        BlockPos size = template.getSize();
        long v = size.getX() * size.getY() * size.getZ();
        if (v > 32768) {
            context.getSource().sendFeedback(
                    new StringTextComponent("")
                            .applyTextStyle(TextFormatting.RED).appendText("[Warning]: The size is reach upper to ")
                            .applyTextStyle(TextFormatting.GOLD).appendText(String.valueOf(v))
                            .applyTextStyle(TextFormatting.RED).appendText(". It might take some time..."
                    ), false
            );
        }
        BlockState blockState = serverWorld.getBlockState(size);
        serverWorld.notifyBlockUpdate(size, blockState, blockState, 3);
        PlacementSettings placementSettings = new PlacementSettings();
        placementSettings.clearProcessors().addProcessor(new IntegrityProcessor(MathHelper.clamp(1.0F, 0.0F, 1.0F)));
        template.addBlocksToWorldChunk(serverWorld, sourcePos, placementSettings);
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        context.getSource().sendFeedback(new StringTextComponent("").applyTextStyle(TextFormatting.GREEN).appendText("[Pattern-Load]: Success."), false);
        context.getSource().sendFeedback(new StringTextComponent("[Pattern-Load]: Finished in " + time + " ms"), false
        );
    }

    public static class Load implements Command<CommandSource> {

        @Override
        public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
            if (Command_Multithreading.state())
                CommandThread.submit(() -> {
                    _Load(context);
                });
            else
                _Load(context);
            return 0;
        }
    }

    public static class Pos_1 implements Command<CommandSource> {

        @Override
        public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
//            p1 = getCurrentBlockPos(context);
            context.getSource().sendFeedback(
                    new StringTextComponent("Pattern pos_1 is ")
                            .appendText(p1.toString())
                            .appendText("."), false
            );
            return 0;
        }
    }

    public static class Pos_1_Set implements Command<CommandSource> {

        @Override
        public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
            int x = context.getArgument("x", int.class);
            int y = context.getArgument("y", int.class);
            int z = context.getArgument("z", int.class);
            p1 = new BlockPos(x, y, z);
            context.getSource().sendFeedback(
                    new StringTextComponent("Pattern pos_1 set to ")
                            .appendText(p1.toString())
                            .appendText("."), false
            );
            return 0;
        }
    }

    public static class Pos_2 implements Command<CommandSource> {

        @Override
        public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
//            p2 = getCurrentBlockPos(context);
            context.getSource().sendFeedback(
                    new StringTextComponent("Pattern pos_2 is ")
                            .appendText(p2.toString())
                            .appendText("."), false
            );
            return 0;
        }
    }

    public static class Pos_2_Set implements Command<CommandSource> {

        @Override
        public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
            int x = context.getArgument("x", int.class);
            int y = context.getArgument("y", int.class);
            int z = context.getArgument("z", int.class);
            p2 = new BlockPos(x, y, z);
            context.getSource().sendFeedback(
                    new StringTextComponent("Pattern pos_2 set to ")
                            .appendText(p2.toString())
                            .appendText("."), false
            );
            return 0;
        }
    }

    public static class Create implements Command<CommandSource> {

        @Override
        public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
            if (Command_Multithreading.state())
                CommandThread.submit(() -> {
                    _Create(context);
                });
            else
                _Create(context);
            return 0;
        }
    }
    private static void _Create(CommandContext<CommandSource> context)
    {
        ResourceLocation name = context.getArgument("name", ResourceLocation.class);
        if (name == null) {
            context.getSource().sendErrorMessage(
                    new StringTextComponent("[Pattern-Create]: Pattern name unset or invalid.")
            );
            return;
        }
        if (p1 == null) {
            context.getSource().sendErrorMessage(
                    new StringTextComponent("[Pattern-Create]: Pattern pos_1 unset.")
            );
            return;
        }
        if (p2 == null) {
            context.getSource().sendErrorMessage(
                    new StringTextComponent("[Pattern-Create]: Pattern pos_2 unset.")
            );
            return;
        }
        BlockPos sourcePos = new BlockPos(min(p1.getX(), p2.getX()), min(p1.getY(), p2.getY()), min(p1.getZ(), p2.getZ()));
        BlockPos size = new BlockPos(
                max(p1.getX(), p2.getX()) - min(p1.getX(), p2.getX()) + 1,
                max(p1.getY(), p2.getY()) - min(p1.getY(), p2.getY()) + 1,
                max(p1.getZ(), p2.getZ()) - min(p1.getZ(), p2.getZ()) + 1
        );
        p1 = null;
        p2 = null;
        long v = size.getX() * size.getY() * size.getZ();
        context.getSource().sendFeedback(
                new StringTextComponent("[Pattern-Create]: ")
                        .appendText("From: " + sourcePos.toString() + " To: " + sourcePos.add(size.getX(), size.getY(), size.getZ()).toString() + " Size: " + v)
                , false
        );
        if (v > 32768) {
            context.getSource().sendFeedback(
                    new StringTextComponent("")
                            .applyTextStyle(TextFormatting.RED).appendText("[Pattern-Warning]: The size is reach upper to (")
                            .applyTextStyle(TextFormatting.GOLD).appendText(String.valueOf(v))
                            .applyTextStyle(TextFormatting.RED).appendText(")\nIt might take some time..."
                    ), false
            );
        }
        context.getSource().sendFeedback(
                new StringTextComponent(
                        "[Pattern-Create]: Now start create pattern [" + name.toString() + "] ..."
                ), false
        );
        long startTime = System.currentTimeMillis();
        ServerWorld serverWorld = context.getSource().getWorld();
        TemplateManager templateManager = serverWorld.getStructureTemplateManager();
        Template template;
        template = templateManager.getTemplateDefaulted(name);
        template.takeBlocksFromWorld(serverWorld, sourcePos, size, true, Blocks.STRUCTURE_VOID);
        template.setAuthor(context.getSource().getEntity().getDisplayName().toString());
        templateManager.writeToFile(name);
        context.getSource().sendFeedback(new StringTextComponent("").applyTextStyle(TextFormatting.GREEN).appendText("[Generate]: Success."), false);
        context.getSource().sendFeedback(new StringTextComponent("[Pattern-Create]: Finished in " + (System.currentTimeMillis() - startTime) + " ms"), false);
    }
}

