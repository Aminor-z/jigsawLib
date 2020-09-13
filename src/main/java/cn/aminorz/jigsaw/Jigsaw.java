package cn.aminorz.jigsaw;

import cn.aminorz.jigsaw.item.JigsawItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("jigsaw")
public class Jigsaw {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public Jigsaw() {
        MinecraftForge.EVENT_BUS.register(this);
        JigsawItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        LOGGER.info("Aminor_z's jigsaw lib launched.");
    }
}
