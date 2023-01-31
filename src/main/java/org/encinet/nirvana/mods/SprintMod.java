package org.encinet.nirvana.mods;

import net.minecraft.client.Minecraft;
import org.encinet.nirvana.until.Mod;

public class SprintMod extends Mod {

    public SprintMod() {
        super("Sprint", "Automatic sprint", true);
    }

    @Override
    public void tick() {
        if (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown()) {
            Minecraft.getMinecraft().thePlayer.setSprinting(true);
        }
    }
}
