package org.encinet.nirvana.mod.mods.movement;


import net.minecraft.client.Minecraft;
import org.encinet.nirvana.mod.mods.Mod;

public class SprintMod extends Mod {
    public SprintMod() {
        super("Sprint", "Auto sprint", false);
    }
    @Override
    public void update() {

        if (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown()) {
            Minecraft.getMinecraft().thePlayer.setSprinting(true);
        }
    }
}



