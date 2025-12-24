package de.lighty.foxutils;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

@Environment(EnvType.CLIENT)
public class FoxUtilsGameModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> Game.CLIENT = client);
        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> Game.CLIENT = null);

        ClientTickEvents.START_CLIENT_TICK.register(client -> Game.LOCAL_PLAYER = client.player);
    }
}
