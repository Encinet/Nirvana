package org.encinet.nirvana.until;

/**
 * 鼠标在click gui的不同状态
 */
public enum MouseXYType {
    OUT,// 在窗口和检测区的外部
    IN,// 窗口内部 但不在检测区内 不是控件就是拖动
    RESIZE_L,// 水平调整大小 左
    RESIZE_R,// 水平调整大小 右
    RESIZE_U,// 垂直调整大小 上
    RESIZE_D,// 垂直调整大小 下
    RESIZE_LU,// 沿对角线调整大小 左上
    RESIZE_LD,// 沿对角线调整大小 左下
    RESIZE_RU,// 沿对角线调整大小 右上
    RESIZE_RD;// 沿对角线调整大小 右下

    // 由边延长range 为检测范围
    static final int range = 10;

    // ------------------------------------
    // |   None                           |
    // |           ---NS-------           |
    // |           EW         |           |
    // |           ----------NWSE         |
    // ------------------------------------

    /**
     * 鼠标类型
     * x <= x1
     * y <= y1
     * @param mouseX 鼠标x
     * @param mouseY 鼠标y
     * @param x 矩形起始x
     * @param y 矩形起始y
     * @param x1 矩形终点x
     * @param y1 矩形终点y
     * @return 鼠标类型
     */
    public static MouseXYType getType(int mouseX, int mouseY, int x, int y, int x1, int y1) {
        if (mouseX >= x + range && mouseX <= x1 - range
                   && mouseY >= y + range && mouseY <= y1 - range) {
            // 处在click gui内部 但不在边的检测范围
            return IN;
        } else if (mouseX >= x - range && mouseX <= x1 + range
                   && mouseY >= y - range && mouseY <= y1 + range) {
            // 存在四周边的检测范围
            // 矩形的四个点
            if (inPointRange(mouseX, mouseY, x, y)) {
                // 角 左上
                return RESIZE_LU;
            } else if (inPointRange(mouseX, mouseY, x, y1)) {
                // 角 左下
                return RESIZE_LD;
            } else if (inPointRange(mouseX, mouseY, x1, y)) {
                // 角 右上
                return RESIZE_RU;
            } else if (inPointRange(mouseX, mouseY, x1, y1)) {
                // 角 右下
                return RESIZE_RD;
            }

            // 边
            int toLeft = Math.abs(mouseX - x);
            int toRight = Math.abs(mouseX - x1);
            int toUp = Math.abs(mouseY - y);
            int toDown = Math.abs(mouseY - y1);

            int min = Math.min(Math.min(toLeft, toRight), Math.min(toUp, toDown));

            if (min == toLeft) {
                return RESIZE_L;
            } else if (min == toRight) {
                return RESIZE_R;
            } else if (min == toUp) {
                return RESIZE_U;
            } else {
                return RESIZE_D;
            }
        } else return OUT;
    }

    /**
     * 是否处于矩形的角的检测范围内
     * x <= x1
     * y <= y1
     * @param mouseX 鼠标x
     * @param mouseY 鼠标y
     * @param x 矩形的角x
     * @param y 矩形的角y
     * @return 是否处于矩形的角的检测范围内
     */
    public static boolean inPointRange(int mouseX, int mouseY, int x, int y) {
        return mouseX >= x - range && mouseX <= x + range
               && mouseY >= y - range && mouseY <= y + range;
    }
}