package cn.aminorz.jigsaw.item.items;

import cn.aminorz.jigsaw.command.command.Command_Pattern;
import cn.aminorz.jigsaw.item.JigsawItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class Select_Tool_Pos2 extends Item {

    public Select_Tool_Pos2() {
        super(new Properties().group(JigsawItems.itemGroup));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack heldItem = playerEntity.getHeldItem(hand);
        RayTraceResult rayTraceResult = rayTrace(world, playerEntity, RayTraceContext.FluidMode.SOURCE_ONLY);
        if (!world.isRemote && rayTraceResult.getType() == RayTraceResult.Type.BLOCK) {
            BlockRayTraceResult traceResult = (BlockRayTraceResult) rayTraceResult;
            BlockPos blockPos = traceResult.getPos().subtract(traceResult.getFace().getDirectionVec());
            Command_Pattern.p1 = blockPos;
            playerEntity.sendMessage(new StringTextComponent(blockPos.toString()));
        }
        return ActionResult.resultPass(heldItem);
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState p_195938_1_, World p_195938_2_, BlockPos p_195938_3_, PlayerEntity p_195938_4_) {
        return false;
    }
}
