package de.lighty.foxutils.events.block;

import de.lighty.foxutils.events.block.handlers.IBlockBrokenHandler;
import de.lighty.foxutils.events.block.handlers.IBlockPlacedHandler;

public class BlockEventRegistry {
    public static class Place {
        public static void register(IBlockPlacedHandler handler) {
            BlockPlacedEventRegistryImpl.register(handler);
        }
    }

    public static class Break {
        public static void register(IBlockBrokenHandler handler) {
            BlockBreakEventRegistryImpl.register(handler);
        }
    }

}
