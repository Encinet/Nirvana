package org.encinet.nirvana.gui.main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.encinet.nirvana.gui.Theme;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class MainMenu extends GuiScreen {
    @Override
    public void initGui() {
//        int j = this.height / 4 + 48;
//        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, j + 72 + 12, 98, 20, I18n.format("menu.options")));
//        this.buttonList.add(new GuiButton(4, this.width / 2 + 2, j + 72 + 12, 98, 20, I18n.format("menu.quit")));
//        this.buttonList.add(new GuiButtonLanguage(5, this.width / 2 - 124, j + 72 + 12));

        int defaultHeight = height / 4 + 48;
        buttonList.add(new GuiButton(0, width / 2 - 100, defaultHeight, 98, 20, I18n.format("menu.singleplayer")));
        buttonList.add(new GuiButton(1, width / 2 + 2, defaultHeight, 98, 20, I18n.format("menu.multiplayer")));
        buttonList.add(new GuiButton(2, width / 2 - 100, defaultHeight + 24, 98, 20, "Theme"));
        buttonList.add(new GuiButton(103, width / 2 + 2, defaultHeight + 24, 98, 20, "Mods"));
        buttonList.add(new GuiButton(101, width / 2 - 100, defaultHeight + 24 * 2, 98, 20, "Server Status"));
        buttonList.add(new GuiButton(102, width / 2 + 2, defaultHeight + 24 * 2, 98, 20, "Background"));

        // Minecraft Realms
        //		this.buttonList.add(new GuiButton(14, this.width / 2 - 100, j + 24 * 2, I18n.format("menu.online", new Object[0])));

        buttonList.add(new GuiButton(108, width / 2 - 100, defaultHeight + 24 * 3, "Contributors"));
        buttonList.add(new GuiButton(0, width / 2 - 100, defaultHeight + 24 * 4, 98, 20, I18n.format("menu.options")));
        buttonList.add(new GuiButton(4, width / 2 + 2, defaultHeight + 24 * 4, 98, 20, I18n.format("menu.quit")));
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1) {
            Minecraft.getMinecraft().displayGuiScreen(this);
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                // 单人游戏
                this.mc.displayGuiScreen(new GuiSelectWorld(this));
                break;
            case 1:
                // 多人游戏
                this.mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            case 2:
                Theme.mode = !Theme.mode;
                themeApply();
                break;
        }
        // 设置
        //this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        // 语言
        //this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
        // 关闭
        // this.mc.shutdown();
        // mods
        //this.mc.displayGuiScreen(new net.minecraftforge.fml.client.GuiModList(this));
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.enableAlpha();
        // theme
        themeApply();
        // super
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    /**
     * 主题设置
     */
    public void themeApply() {
        // background
        int main = (Theme.mode ? Theme.DARK_MAIN : Theme.LIGHT_MAIN).getRGB();
        drawRect(0, 0, this.width, this.height, main);
        // logo
        ResourceLocation logo = new ResourceLocation("nirvana:textures/logo/"
                                                     + (Theme.mode ? "white.png" : "blue.png"));
        mc.getTextureManager().bindTexture(logo);

        this.drawTexturedModalRect(this.width / 2 - 64, this.height / 2 - 64, 0, 0, this.width / 3, this.height / 3);
    }
}
