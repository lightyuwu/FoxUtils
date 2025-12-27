package de.lighty.foxutils.mixin;

import de.lighty.foxutils.IPlayerChatMessageMixin;
import de.lighty.foxutils.events.player.PlayerChatEventRegistryImpl;
import net.minecraft.network.chat.*;
import net.minecraft.network.protocol.game.ServerboundChatPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerGamePacketListenerImplMixin {
    @Shadow
    private SignedMessageChain.Decoder signedMessageDecoder;

    @Shadow
    public ServerPlayer player;

    @Inject(method = "broadcastChatMessage", at = @At("HEAD"), cancellable = true)
    private void cancelChat(PlayerChatMessage playerChatMessage, CallbackInfo ci) {
        var cast = (IPlayerChatMessageMixin)(Object)playerChatMessage;
        if(cast == null) return;
        var result = cast.foxutils$getResult();
        if(result != null && result.cancelled){
            ci.cancel();
        }
    }


    @Inject(method="getSignedMessage", at=@At("HEAD"), cancellable = true)
    private void getSignedMessageInj(ServerboundChatPacket packet, LastSeenMessages lastSeenMessages, CallbackInfoReturnable<PlayerChatMessage> cir) throws SignedMessageChain.DecodeException {
        var msg = packet.message();
        // Handle PlayerChatEventResults here
        var result = PlayerChatEventRegistryImpl.execute(player, msg);

        if(result != null && result.message != null){
            msg = result.message;
        }

        var msgBody = new SignedMessageBody(msg,
                packet.timeStamp(),
                packet.salt(),
                lastSeenMessages);

        var decoded = signedMessageDecoder.unpack(packet.signature(), msgBody);

        ((IPlayerChatMessageMixin) (Object) decoded).foxutils$setResult(result);

        cir.setReturnValue(decoded);
    }

}
