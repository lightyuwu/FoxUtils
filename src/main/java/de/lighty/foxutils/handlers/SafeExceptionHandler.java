package de.lighty.foxutils.handlers;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;

import java.util.Arrays;

public class SafeExceptionHandler {
    public static void withChat(Player player, ISafeExceptionHandler handler) {
        try {
            handler.handle();
        }catch(Exception e) {
            player.displayClientMessage(
                    Component.literal("An Exception Occurred!\n").withStyle(ChatFormatting.RED)
                            .append("Message: " + e.getMessage())
                            .append("\nTrace[0]: " + e.getStackTrace()[0]),
                    false
            );
            e.printStackTrace();
        }
    }

    public static void withChat(ServerPlayer player, ISafeExceptionHandler handler) {
        try {
            handler.handle();
        }catch(Exception e) {
            player.sendSystemMessage(
                    Component.literal("An Exception Occurred!\n").withStyle(ChatFormatting.RED)
                            .append("Message: " + e.getMessage())
                            .append("\nTrace[0]: " + e.getStackTrace()[0]),
                    false
            );
        }
    }

    public static void withLogger(Logger logger, ISafeExceptionHandler handler){
        try{
            handler.handle();
        }catch(Exception e) {
            logger.error(" ==== An Exception Ocurred! ====");
            logger.error("Message: {}", e.getMessage());
            logger.error(" ========= Stack Trace =========");
            for (StackTraceElement el : e.getStackTrace()) {
                logger.error(el.toString());
            }
            logger.error(" ===============================");
        }
    }
}
