package cn.aminorz.jigsaw.command.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;

public class Command_Multithreading {
    public static ON ON = new ON();
    public static OFF OFF = new OFF();
    public static SHOW SHOW = new SHOW();
    private static boolean state = false;

    public static boolean state() {
        return state;
    }

    public static class ON implements Command<CommandSource> {
        @Override
        public int run(CommandContext<CommandSource> context) {
            state = true;
            context.getSource().sendFeedback(
                    new StringTextComponent("[JigsawLib]: use_Command_Multithreading ->  ").appendText(String.valueOf(state)), false);
            return 0;
        }
    }

    public static class OFF implements Command<CommandSource> {
        @Override
        public int run(CommandContext<CommandSource> context) {
            state = false;
            context.getSource().sendFeedback(
                    new StringTextComponent("[JigsawLib]: use_Command_Multithreading ->  ").appendText(String.valueOf(state)), false);
            return 0;
        }
    }

    public static class SHOW implements Command<CommandSource> {
        @Override
        public int run(CommandContext<CommandSource> context) {
            context.getSource().sendFeedback(
                    new StringTextComponent("[JigsawLib]: use_Command_Multithreading --  ").appendText(String.valueOf(state)), false);
            return 0;
        }
    }
}
