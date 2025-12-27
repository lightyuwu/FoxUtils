package de.lighty.foxutils;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class FoxUtilsGameMod implements ModInitializer {
	//public static final String MOD_ID = "foxutils";

	//protected static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ServerLifecycleEvents.SERVER_STARTED.register(srv -> Game.SERVER = srv);
		ServerLifecycleEvents.SERVER_STOPPED.register(srv -> Game.SERVER = null);

		// Tests
		/*
		PlayerEventRegistry.Chat.register((player, message) -> {
			LOGGER.info(message);
			if(message.startsWith("!")){
				return PlayerChatEventResult.CANCEL;
			}
			return new PlayerChatEventResult(message.replace("floof", "#####"));

		});*/
	}
}