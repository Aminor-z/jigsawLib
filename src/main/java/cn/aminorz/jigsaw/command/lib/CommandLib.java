package cn.aminorz.jigsaw.command.lib;

import com.mojang.brigadier.context.CommandContext;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.command.CommandSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class CommandLib {
    public static BlockPos getCurrentBlockPos(CommandContext<CommandSource> context)
    {
        Vec3d vec3d=context.getSource().getPos();
        return new BlockPos((int)vec3d.x,(int)vec3d.y,(int)vec3d.z) ;
    }
}
