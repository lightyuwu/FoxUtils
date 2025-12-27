package de.lighty.foxutils.events.player;

import de.lighty.foxutils.events.player.handlers.IPlayerChatEventHandler;

@SuppressWarnings("ALL") // This is fine here as it's just because it's not being used.
public class PlayerEventRegistry {
    public static class Chat {
        public static void register(IPlayerChatEventHandler handler) {
            PlayerChatEventRegistryImpl.register(handler);
        }
    }
}
