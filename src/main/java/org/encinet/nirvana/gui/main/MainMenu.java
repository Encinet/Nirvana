package org.encinet.nirvana.gui.main;

import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.encinet.nirvana.gui.Theme;

import java.io.IOException;

public class MainMenu extends GuiScreen {
    @Override
    public void initGui() {
        int j = this.height / 4 + 48;
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, j + 72 + 12, 98, 20, I18n.format("menu.options")));
        this.buttonList.add(new GuiButton(4, this.width / 2 + 2, j + 72 + 12, 98, 20, I18n.format("menu.quit")));
        this.buttonList.add(new GuiButtonLanguage(5, this.width / 2 - 124, j + 72 + 12));
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {

        if (button.id == 0)
        {
            // 设置
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }

        if (button.id == 5)
        {
            // 语言
            this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
        }

        if (button.id == 1)
        {
            // 单人游戏
            this.mc.displayGuiScreen(new GuiSelectWorld(this));
        }

        if (button.id == 2)
        {
            // 多人游戏
            this.mc.displayGuiScreen(new GuiMultiplayer(this));
        }

        if (button.id == 4)
        {
            // 关闭
            this.mc.shutdown();
        }

        if (button.id == 6)
        {
            // mods
            this.mc.displayGuiScreen(new net.minecraftforge.fml.client.GuiModList(this));
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        // background
        int main = Theme.mode ? Theme.DARK_MAIN : Theme.LIGHT_MAIN;
        this.drawGradientRect(0, 0, this.width, this.height, main, main);
        this.drawDefaultBackground();
        // logo
        ResourceLocation logo = new ResourceLocation("nirvana:textures/logo/"
                                                     + (Theme.mode ? "white.png" : "blue.png"));
        mc.getTextureManager().bindTexture(logo);
        this.drawTexturedModalRect(this.width / 2 - 128, this.height / 2 - 128, 0, 0,100, 100);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
