package de.lighty.foxutils.mixin;

import de.lighty.foxutils.events.entity.EntityEventRegistry;
import de.lighty.foxutils.events.entity.EntityInteractEventRegistryImpl;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class FoxUtilsEntityMixin {
    @Inject(method="interact", at=@At("HEAD"), cancellable = true)
    public void onInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir){
        var result = EntityInteractEventRegistryImpl.execute((Entity)(Object)this, player, hand);
        if(result != null) cir.setReturnValue(result);
    }
}
