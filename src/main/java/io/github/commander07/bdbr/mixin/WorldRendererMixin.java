package io.github.commander07.bdbr.mixin;

import net.minecraft.block.AirBlock;
import net.minecraft.block.BarrierBlock;
import net.minecraft.block.Block;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(value = WorldRenderer.class, priority = 1001)
public abstract class WorldRendererMixin {
    @Redirect(at = @At(value="INVOKE", target = "Lnet/minecraft/world/World;getTopY(Lnet/minecraft/world/Heightmap$Type;II)I"), method = "renderWeather")
    private int init(World instance, Heightmap.Type heightmap, int x, int z) {
        int i = -1;
        for (int y = 0; y!=255; y++) {
            Block block = instance.getBlockState(new BlockPos(x, y, z)).getBlock();
            if (!(block instanceof AirBlock) && !(block instanceof BarrierBlock)) {
                i = y;
            }
        }
        return i;
    }
}