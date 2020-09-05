package cn.aminorz.jigsaw.command;

import cn.aminorz.jigsaw.command.command.Command_Structure_List;
import cn.aminorz.jigsaw.command.command.Command_Structure_Summon;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.arguments.ResourceLocationArgument;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.registries.GameData;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.command.Commands.argument;
import static net.minecraft.command.Commands.literal;
import static net.minecraft.command.ISuggestionProvider.suggest;

@Mod.EventBusSubscriber
public class JigsawCommands {
    @SubscribeEvent
    public static void onServerStaring(FMLServerStartingEvent event) {
        CommandDispatcher<CommandSource> dispatcher = event.getCommandDispatcher();
        dispatcher.register(
                literal("jigsaw").then(
                        literal("structure").then(
                                literal("list")
                                        .requires((commandSource) -> {
                                            return commandSource.hasPermissionLevel(0);
                                        })
                                        .executes(Command_Structure_List.instance))));
        dispatcher.register(
                literal("jigsaw").then(
                        literal("structure").then(
                                literal("summon").then(
                                        argument("structure_type", ResourceLocationArgument.resourceLocation())
                                                .suggests(new SuggestionProvider<CommandSource>() {
                                                    @Override
                                                    public CompletableFuture<Suggestions> getSuggestions(CommandContext<CommandSource> commandSourceCommandContext, SuggestionsBuilder suggestionsBuilder) {
                                                        return suggest(GameData.getStructureFeatures().keySet().stream().map(ResourceLocation::toString), suggestionsBuilder);
                                                    }
                                                })
                                                .executes(Command_Structure_Summon.instance)))));
    }
}
