package de.lighty.foxutils.mixin;

import de.lighty.foxutils.events.block.BlockBreakEventRegistryImpl;
import de.lighty.foxutils.events.block.BlockPlacedEventRegistryImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class FoxUtilsBlockMixin {
    @Inject(method = "setPlacedBy", at=@At("HEAD"))
    public void onPlace(Level level,
                        BlockPos pos, BlockState state,
                        LivingEntity entity, ItemStack item,
                        CallbackInfo ci){
        BlockPlacedEventRegistryImpl.execute(entity, level, state, pos);
    }


    @Inject(method = "destroy", at=@At("HEAD"))
    public void onBreak(LevelAccessor levelAccess,
                        BlockPos pos,
                        BlockState state,
                        CallbackInfo ci){
        BlockBreakEventRegistryImpl.execute(levelAccess, state, pos);
    }
}
