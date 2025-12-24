package de.lighty.foxutils.events.block;

import de.lighty.foxutils.events.block.handlers.IBlockPlacedHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import java.util.ArrayList;
import java.util.List;

public class BlockPlacedEventRegistryImpl {

    private static final List<IBlockPlacedHandler> blockInteractHandlers = new ArrayList<>();

    protected static void register(IBlockPlacedHandler handler){
        blockInteractHandlers.add(handler);
    }

    public static void execute(LivingEntity entity, Level level, BlockState block, BlockPos pos) {
        blockInteractHandlers.forEach(handler -> handler.handle(entity, level, block, pos));
    }

}
