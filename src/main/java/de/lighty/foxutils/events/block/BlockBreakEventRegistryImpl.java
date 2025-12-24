package de.lighty.foxutils.events.block;

import de.lighty.foxutils.events.block.handlers.IBlockBrokenHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class BlockBreakEventRegistryImpl {

    private static final List<IBlockBrokenHandler> blockInteractHandlers = new ArrayList<>();

    protected static void register(IBlockBrokenHandler handler){
        blockInteractHandlers.add(handler);
    }

    public static void execute(LevelAccessor levelAccess, BlockState block, BlockPos pos) {
        blockInteractHandlers.forEach(handler -> handler.handle(levelAccess, block, pos));
    }

}
