package com.marstanjx.usccoursetracker;
/*
 * Created by david on 11/7/2017.
 * Copyright ISOTOPE Studio
 */

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;

public class CoursePreview extends JPanel {

    private static final int marginX = 125;
    private static final int marginY = 20;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Mon", marginX + 75, (int) (0.7 * marginY));
        g.drawString("Tue", marginX + 150 + 75, (int) (0.7 * marginY));
        g.drawString("Wed", marginX + 150 * 2 + 75, (int) (0.7 * marginY));
        g.drawString("Thu", marginX + 150 * 3 + 75, (int) (0.7 * marginY));
        g.drawString("Fri", marginX + 150 * 4 + 75, (int) (0.7 * marginY));
        for (int i = 0; i < 12; i++) {
            g.drawString((8 + i) + ":00", marginX / 2, marginY + 60 * i + 5);
            g.drawRect(marginX, i * 60 + marginY, 150 * 5, 0);
        }
        List<Course> courses = semesterGUI.courseList.stream().filter(course -> course.select.isSelected()).collect(Collectors.toList());
        for (Course c : courses) {
            String[] split = c.getStart().split(":");
            int shour = Integer.parseInt(split[0]);
            int smin = Integer.parseInt(split[1]);
            split = c.getEnd().split(":");
            int ehour = Integer.parseInt(split[0]);
            int emin = Integer.parseInt(split[1]);
            int length = ehour * 60 + emin - (shour * 60 + smin);
            for (char day : c.getDays().toCharArray()) {
                int days = 0;
                switch (day) {
                    case ('M'):
                        days = 1;
                        break;
                    case ('T'):
                        days = 2;
                        break;
                    case ('W'):
                        days = 3;
                        break;
                    case ('H'):
                        days = 4;
                        break;
                    case ('F'):
                        days = 5;
                        break;
                }
                switch (c.getDepartment()) {
                    case ("CSCI"):
                        g.setColor(new Color(203, 75, 64));
                        break;
                    case ("COMM"):
                        g.setColor(new Color(203, 76, 107));
                        break;
                    case ("ASCJ"):
                        g.setColor(new Color(203, 76, 107));
                        break;
                    case ("WRIT"):
                        g.setColor(new Color(203, 200, 43));
                        break;
                    case ("ACAD"):
                        g.setColor(new Color(203, 163, 77));
                        break;
                    case ("MUCO"):
                        g.setColor(new Color(46, 203, 76));
                        break;
                    default:
                        g.setColor(new Color(67, 175, 203));
                        break;
                }
                for (int i = 0; i < length; i++) {
                    g.drawRect(marginX + days * 150 - 125, i + marginY + (shour - 8) * 60 + smin, 100, 1);
                }
                g.drawString(c.getName(), marginX + days * 150 - 125, marginY + (shour - 8) * 60 + smin);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // so that our GUI is big enough
        return new Dimension(1000, 700);
    }

    private JFrame frame;
    private SemesterGUI semesterGUI;

    CoursePreview(String term, SemesterGUI semesterGUI) {
        frame = new JFrame("Course Preview(tm) (" + term + ")");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.setVisible(true);
        this.semesterGUI = semesterGUI;
    }

    void paint() {
        frame.repaint();
    }

}
