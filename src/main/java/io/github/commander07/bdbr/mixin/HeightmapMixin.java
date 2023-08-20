package io.github.commander07.bdbr.mixin;

import net.minecraft.block.BarrierBlock;
import net.minecraft.block.BlockState;
import net.minecraft.world.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(Heightmap.Type.class)
public abstract class HeightmapMixin {
	@Shadow public abstract String getName();

	@Inject(at = @At(value="HEAD"), method = "getBlockPredicate", cancellable = true)
	private void init(CallbackInfoReturnable<Predicate<BlockState>> cir) {
		if (this.getName().equals("MOTION_BLOCKING")) {
			cir.setReturnValue((state) -> (state.blocksMovement() || !state.getFluidState().isEmpty()) && !(state.getBlock() instanceof BarrierBlock));
		}
	}
}