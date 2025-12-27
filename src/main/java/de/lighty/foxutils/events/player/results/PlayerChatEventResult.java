package de.lighty.foxutils.events.player.results;

public class PlayerChatEventResult {
    public final String message;
    public final boolean cancelled;

    public PlayerChatEventResult(String message, boolean cancelled) {
        this.message = message;
        this.cancelled = cancelled;
    }

    /// Generate a Player Chat Event Result that does not cancel the Chat Message
    @SuppressWarnings("ALL") // This is fine here as it's just because it's not being used.
    public PlayerChatEventResult(String message) {
        this.message = message;
        this.cancelled = false;
    }

    @SuppressWarnings("ALL") // This is fine here as it's just because it's not being used.
    public static PlayerChatEventResult CANCEL = new PlayerChatEventResult(null, true);
}
