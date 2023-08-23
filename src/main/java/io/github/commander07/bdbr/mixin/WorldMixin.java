package io.github.commander07.bdbr.mixin;

import net.minecraft.block.BarrierBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = World.class)
public abstract class WorldMixin {
    @Inject(at = @At("HEAD"), method = "getTopY", cancellable = true)
    public void getTopPosition(Heightmap.Type heightmap, int x, int z, CallbackInfoReturnable<Integer> cir) {
        if (heightmap == Heightmap.Type.MOTION_BLOCKING) {
            int i = -65;
            for (int y = 0; y!=255; y++) {
                BlockState state = MinecraftClient.getInstance().world.getBlockState(new BlockPos(x, y, z));
                if ((state.blocksMovement() || !state.getFluidState().isEmpty()) && !(state.getBlock() instanceof BarrierBlock)) {
                    i = y;
                }
            }
            cir.setReturnValue(i);
        }
    }
}
