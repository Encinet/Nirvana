package org.encinet.nirvana.until;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.encinet.nirvana.gui.clickgui.ClickGui;
import org.lwjgl.input.Keyboard;

import java.util.*;

public class KeyManager {
    public static final Map<Keys, KeyBinding> KB = new HashMap<>();
    public KeyManager() {
        Keys[] keys = Keys.values();
        for (Keys key : keys) {
            KeyBinding binding = new KeyBinding(key.getText(), key.getKey(), "Nirvana");
            // key binding
            ClientRegistry.registerKeyBinding(binding);
            KB.put(key, binding);
        }
    }

    public static void event() {
        for (Map.Entry<Keys, KeyBinding> entry : KB.entrySet()) if (entry.getValue().isPressed()) {
            // 按键捕获写在这里
            Keys keyEnum = entry.getKey();
            if (keyEnum == Keys.OPEN_CLICK_GUI) {
                System.out.println("Open Menu");
                Minecraft.getMinecraft().displayGuiScreen(new ClickGui());
            }
            return;
        }
    }
}

enum Keys {
    // 这里不会作为按键绑定主体, 按键绑定控制主要使用mod内部的控制器
    OPEN_CLICK_GUI("click_gui.open", Keyboard.KEY_RSHIFT);


    // 按键介绍文本
    private final String TEXT;
    // 默认按键
    private final int DEFAULT_KEY;

    Keys(String text, int key) {
        this.TEXT = text;
        this.DEFAULT_KEY = key;
    }
    public String getText() {
        return TEXT;
    }
    public int getKey() {
        return DEFAULT_KEY;
    }
}
