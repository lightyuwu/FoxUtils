package de.lighty.foxutils.events.entity;

import de.lighty.foxutils.events.entity.handlers.IEntityInteractHandler;
import net.minecraft.world.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class EntityInteractEventRegistryImpl {
    private static final List<IEntityInteractHandler> blockInteractHandlers = new ArrayList<>();

    protected static void register(IEntityInteractHandler handler){
        blockInteractHandlers.add(handler);
    }

    public static InteractionResult execute(Entity target, Player player, InteractionHand hand) {
        AtomicReference<InteractionResult> result = new AtomicReference<>(null);
        blockInteractHandlers.forEach(handler -> {
            var res = handler.handle(target, player, hand);
            if(res != null) result.set(res);
        });

        return result.get();
    }
}
