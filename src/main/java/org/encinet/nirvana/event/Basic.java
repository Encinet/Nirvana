package org.encinet.nirvana.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.encinet.nirvana.gui.main.MainMenu;
import org.encinet.nirvana.until.KeyManager;

public class Basic {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        // 按键检测
        KeyManager.event();
    }

    @SubscribeEvent
    public void onTickClient(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            // 应用自定义主菜单
            Minecraft mc = Minecraft.getMinecraft();
            GuiScreen currentScreen = mc.currentScreen;
            if (currentScreen instanceof GuiMainMenu) {
                MainMenu customMenu = new MainMenu();
                mc.displayGuiScreen(customMenu);
            }
        }
    }
    @SubscribeEvent
    public void onPlayerSendMsg() {

    }
}
