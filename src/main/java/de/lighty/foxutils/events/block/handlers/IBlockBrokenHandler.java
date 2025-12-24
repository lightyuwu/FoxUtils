package de.lighty.foxutils.events.block.handlers;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

@FunctionalInterface
public interface IBlockBrokenHandler {
    void handle(LevelAccessor worldAccess, BlockState block, BlockPos pos);
}
