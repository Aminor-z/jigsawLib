package cn.aminorz.jigsaw.item;

import cn.aminorz.jigsaw.item.items.Select_Tool_Pos1;
import cn.aminorz.jigsaw.item.items.Select_Tool_Pos2;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JigsawItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "jigsaw");
    public static final ItemGroup itemGroup = new ItemGroup(ItemGroup.GROUPS.length, "jigsaw_group") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Blocks.STRUCTURE_BLOCK);
        }
    };

    static {
        register(new Select_Tool_Pos1());
        register(new Select_Tool_Pos2());
    }

    public static <T extends Item> RegistryObject<Item> register(T item) {
        return ITEMS.register(item.getClass().getSimpleName().toLowerCase(), () -> item);
    }
}
