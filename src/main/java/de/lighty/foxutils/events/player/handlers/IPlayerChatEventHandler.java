package de.lighty.foxutils.events.player.handlers;

import de.lighty.foxutils.events.player.results.PlayerChatEventResult;
import net.minecraft.server.level.ServerPlayer;

@FunctionalInterface
public interface IPlayerChatEventHandler {
    PlayerChatEventResult handle(ServerPlayer player, String message);
}