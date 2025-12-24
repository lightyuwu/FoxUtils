package de.lighty.foxutils.events.block.handlers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@FunctionalInterface
public interface IBlockPlacedHandler {
    void handle(LivingEntity entity, Level world, BlockState block, BlockPos pos);
}
