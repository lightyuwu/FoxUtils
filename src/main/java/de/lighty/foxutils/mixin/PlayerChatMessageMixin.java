package de.lighty.foxutils.mixin;

import de.lighty.foxutils.IPlayerChatMessageMixin;
import de.lighty.foxutils.events.player.results.PlayerChatEventResult;
import net.minecraft.network.chat.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerChatMessage.class)
public abstract class PlayerChatMessageMixin implements IPlayerChatMessageMixin {

    @Shadow
    public abstract String signedContent();

    @Shadow
    @Final
    private SignedMessageLink link;


    @Shadow
    @Final
    private SignedMessageBody signedBody;


    @Shadow
    @Final
    private MessageSignature signature;

    @Shadow
    @Final
    private FilterMask filterMask;

    @Unique
    PlayerChatEventResult foxutils$result = new PlayerChatEventResult(null, false);

    @Override
    public PlayerChatEventResult foxutils$getResult() {
        return foxutils$result;
    }

    @Override
    public void foxutils$setResult(PlayerChatEventResult result) {
        foxutils$result = result;
    }


    @Inject(method = "withUnsignedContent", at=@At("HEAD"), cancellable = true)
    public void withUnsignedContent(Component component, CallbackInfoReturnable<PlayerChatMessage> cir) {
        Component otherComponent = component.equals(Component.literal(signedContent())) ? null : component;
        var ret = new PlayerChatMessage(link, signature, signedBody, otherComponent, filterMask);
        ((IPlayerChatMessageMixin)(Object)ret).foxutils$setResult(foxutils$result); // Modify result BEFORE returning
        cir.setReturnValue(ret);
    }
}
