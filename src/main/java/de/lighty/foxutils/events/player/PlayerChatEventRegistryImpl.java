package de.lighty.foxutils.events.player;

import de.lighty.foxutils.events.player.handlers.IPlayerChatEventHandler;
import de.lighty.foxutils.events.player.results.PlayerChatEventResult;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PlayerChatEventRegistryImpl {
    private static final List<IPlayerChatEventHandler> eventHandlers = new ArrayList<>();

    protected static void register(IPlayerChatEventHandler handler){
        eventHandlers.add(handler);
    }

    public static PlayerChatEventResult execute(ServerPlayer player, String message) {
        var endResult = new AtomicReference<>(new PlayerChatEventResult(message, false)); // "Default"
        eventHandlers.forEach(handler -> {
            var result = handler.handle(player, message);
            if(result != null) endResult.set(result);
        });

        return endResult.get();
    }
}
