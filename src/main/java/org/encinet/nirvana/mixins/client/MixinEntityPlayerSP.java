package org.encinet.nirvana.mixins.client;

import net.minecraft.client.entity.EntityPlayerSP;
import org.encinet.nirvana.Nirvana;
import org.encinet.nirvana.mod.Mod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP {
    @Inject(
            method = "onUpdate",
            at = @At("HEAD")
    )
    private void onUpdate(CallbackInfo ci) {
        Nirvana.modManager.getEnableMods().forEach(Mod::update);
    }
}
