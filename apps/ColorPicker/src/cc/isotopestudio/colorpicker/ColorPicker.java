package cc.isotopestudio.colorpicker;
/*
 * Created by david on 2017/1/6.
 * Copyright ISOTOPE Studio
 */

import java.awt.*;
import java.awt.image.BufferedImage;

public class ColorPicker {

    private Robot rb;
    private Toolkit tk;
    private Dimension di;
    private Rectangle rec;

    public ColorPicker() {
        try {
            rb = new Robot();
            tk = Toolkit.getDefaultToolkit(); // 获取缺省工具包
            di = tk.getScreenSize(); //屏幕尺寸规格
            rec = new Rectangle(0, 0, di.width, di.height);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public int getPixel(int x, int y) {
        BufferedImage bi = rb.createScreenCapture(rec);
        int pixelColor = bi.getRGB(x, y);//获得自定坐标的像素值
        // pixelColor的值为负，经过实践得出：加上颜色最大值就是实际颜色值。
        return 16777216 + pixelColor;
    }
}
