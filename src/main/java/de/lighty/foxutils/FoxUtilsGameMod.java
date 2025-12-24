package de.lighty.foxutils;

import de.lighty.foxutils.config.ModConfig;
import de.lighty.foxutils.events.block.BlockEventRegistry;
import de.lighty.foxutils.events.entity.EntityEventRegistry;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FoxUtilsGameMod implements ModInitializer {
	public static final String MOD_ID = "foxutils";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	protected static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ServerLifecycleEvents.SERVER_STARTED.register(srv -> Game.SERVER = srv);
		ServerLifecycleEvents.SERVER_STOPPED.register(srv -> Game.SERVER = null);

		var cfg = new ModConfig(MOD_ID);
	}
}