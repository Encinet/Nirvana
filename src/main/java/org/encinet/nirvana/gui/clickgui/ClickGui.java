package org.encinet.nirvana.gui.clickgui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.encinet.nirvana.gui.Theme;
import org.encinet.nirvana.until.GuiDraw;
import org.encinet.nirvana.until.MouseXYType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClickGui extends GuiScreen {
    public static List<CategoryButton> categoryButton = new ArrayList<>();
    ScaledResolution sr;
    // 中心点位置
    int centerW, centerH;
    // background大小
    int backW = 400;
    int backH = 280;
    // background坐标点
    int x, y, x1, y1;

    @Override
    public void initGui() {
        mc.entityRenderer.loadShader(new ResourceLocation("minecraft:shaders/post/blur.json"));
        sr = new ScaledResolution(mc);
        // 中心点计算
        centerW = sr.getScaledWidth() / 2;
        centerH = sr.getScaledHeight() / 2;
        // background坐标点坐标点计算 初始
        int halfBackW = backW / 2;
        int halfBackH = backH / 2;
        x = centerW - halfBackW;
        y = centerH - halfBackH;
        x1 = x + backW;// 也=centerW + halfBackW
        y1 = y + backH;// 也=centerH + halfBackH
        // category
        // categoryButton.add(new CategoryButton());
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1) {
            mc.entityRenderer.stopUseShader();
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        // 点击事件
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    int LastMouseX, LastMouseY = 0;

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        if (timeSinceLastClick >= 1000) {
            // 如果上次点击>=100毫秒就认为第一次点击
            // 计算mouseXY与xy的相对坐标(首次)
            LastMouseX = mouseX;
            LastMouseY = mouseY;
            return;
        }

        MouseXYType type = MouseXYType.getType(mouseX, mouseY, x, y, x1, y1);
        // 给各个数值备份 以方便检测是否超出窗口
        int temp_x = x;
        int temp_y = y;
        int temp_x1 = x1;
        int temp_y1 = y1;
        switch (type) {
            case OUT:// 在窗口和检测区的外部
                // 可以加入一些点击的粒子效果
                break;
            case IN:// 窗口内部 但不在检测区内 不是控件就是拖动
                // !!! 目前尚未有控件检测 !!!
                // 计算拖动后的坐标
                temp_x = x + (mouseX - LastMouseX);
                temp_y = y + (mouseY - LastMouseY);
                temp_x1 = x + backW;// 也=centerW + halfBackW
                temp_y1 = y + backH;// 也=centerH + halfBackH
                break;
            case RESIZE_L:// 水平调整大小 左
                temp_x = x + (mouseX - LastMouseX);
                break;
            case RESIZE_R:// 水平调整大小 右
                temp_x1 = x1 + (mouseX - LastMouseX);
                break;
            case RESIZE_U:// 垂直调整大小 上
                temp_y = y + (mouseY - LastMouseY);
                break;
            case RESIZE_D:// 垂直调整大小 下
                temp_y1 = y1 + (mouseY - LastMouseY);
                break;
            case RESIZE_LU:// 沿对角线调整大小 左上
                temp_x = x + (mouseX - LastMouseX);
                temp_y = y + (mouseY - LastMouseY);
                break;
            case RESIZE_LD:// 沿对角线调整大小 左下
                temp_x = x + (mouseX - LastMouseX);
                temp_y1 = y1 + (mouseY - LastMouseY);
                break;
            case RESIZE_RU:// 沿对角线调整大小 右上
                temp_x1 = x1 + (mouseX - LastMouseX);
                temp_y = y + (mouseY - LastMouseY);
                break;
            case RESIZE_RD:// 沿对角线调整大小 右下
                temp_x1 = x1 + (mouseX - LastMouseX);
                temp_y1 = y1 + (mouseY - LastMouseY);
                break;
        }
        // 超出窗口保护
        x = Math.max(temp_x, 0);
        y = Math.max(temp_y, 0);
        x1 = Math.min(temp_x1, this.width);
        y1 = Math.min(temp_y1, this.height);
        // 储存
        LastMouseX = mouseX;
        LastMouseY = mouseY;
        // background
        GuiDraw.drawRoundedRect(x, y, x1, y1, 8, Theme.mode ? Theme.DARK_MAIN : Theme.LIGHT_MAIN);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        // 实时更新中心点 屏占比可能会发生变化
        centerW = sr.getScaledWidth() / 2;
        centerH = sr.getScaledHeight() / 2;
        // background
        GuiDraw.drawRoundedRect(x, y, x1, y1, 8, Theme.mode ? Theme.DARK_MAIN : Theme.LIGHT_MAIN);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
