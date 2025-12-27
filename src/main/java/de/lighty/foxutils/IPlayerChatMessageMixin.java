package de.lighty.foxutils;

import de.lighty.foxutils.events.player.results.PlayerChatEventResult;

public interface IPlayerChatMessageMixin {
    void foxutils$setResult(PlayerChatEventResult result);
    PlayerChatEventResult foxutils$getResult();
}
