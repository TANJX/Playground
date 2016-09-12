import javax.swing.*;
import java.awt.*;

public class MouseMove extends JFrame {
    public static JLabel lab = new JLabel();

    public MouseMove() {
    }

    public static void main(String[] args) {
        // MouseMove fm=new MouseMove("鼠标坐标测试");
        JFrame fm = new JFrame("鼠标坐标测试");
        JPanel fp = new JPanel();
        fp.addMouseMotionListener(new myMouseListener());// 对在面板上的鼠标移动进行监听。
        Container con = fm.getContentPane();
        fp.add(lab);
        con.add(fp);
        fm.setSize(200, 80);
        fm.setVisible(true);
        fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        while (true) {
            Point point = MouseInfo.getPointerInfo().getLocation();
            MouseMove.lab.setText("X: " + (int) point.getX() + ", Y: " + (int) point.getY());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e1) {
            }
        }
    }

}