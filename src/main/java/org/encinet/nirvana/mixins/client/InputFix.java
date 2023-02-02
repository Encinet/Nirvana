package org.encinet.nirvana.mixins.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(GuiScreen.class)
public abstract class InputFix {
    @Shadow protected abstract void keyTyped(char p_keyTyped_1_, int p_keyTyped_2_) throws IOException;

    @Shadow public Minecraft mc;

    @Inject(
            method = "handleKeyboardInput",
            at = @At(value = "HEAD", shift = At.Shift.BEFORE)
    )
    private void handleKeyboardInput(CallbackInfo ci) throws IOException {
        char eventCharacter = Keyboard.getEventCharacter();
        int eventKey = Keyboard.getEventKey();
        if (Keyboard.getEventKeyState() || eventCharacter >= ' ' && eventKey == 0) {
            this.keyTyped(eventCharacter, eventKey);
        }
        this.mc.dispatchKeypresses();
    }
}
