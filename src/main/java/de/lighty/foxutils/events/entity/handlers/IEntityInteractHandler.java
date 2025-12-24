package de.lighty.foxutils.events.entity.handlers;

import net.minecraft.world.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

@FunctionalInterface
public interface IEntityInteractHandler {
    InteractionResult handle(Entity target, Player player, InteractionHand hand);
}
