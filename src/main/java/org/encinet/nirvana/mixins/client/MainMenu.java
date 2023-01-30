package org.encinet.nirvana.mixins.client;

import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.encinet.nirvana.gui.Theme;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMainMenu.class)
public abstract class MainMenu extends GuiScreen {
    @Inject(method = "initGui", at = @At("HEAD"), cancellable = true)
    public void initGui(CallbackInfo ci) {
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

        ci.cancel();
    }

    @Inject(method = "actionPerformed", at = @At("HEAD"), cancellable = true)
    protected void actionPerformed(GuiButton p_actionPerformed_1_, CallbackInfo ci) {
        switch (p_actionPerformed_1_.id) {
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
        ci.cancel();
    }

    @Inject(method = "drawScreen", at = @At("HEAD"), cancellable = true)
    public void drawScreen(int p_drawScreen_1_, int p_drawScreen_2_, float p_drawScreen_3_, CallbackInfo ci) {
        GlStateManager.enableAlpha();
        // theme
        themeApply();
        // super
        super.drawScreen(p_drawScreen_1_, p_drawScreen_2_, p_drawScreen_3_);
        // cancel
        ci.cancel();
    }

    /**
     * 主题设置
     */
    private void themeApply() {
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
