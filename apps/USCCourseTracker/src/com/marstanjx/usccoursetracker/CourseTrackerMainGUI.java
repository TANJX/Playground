package com.marstanjx.usccoursetracker;
/*
 * Created by david on 2018/06/12.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import java.awt.*;

public class CourseTrackerMainGUI extends JFrame {

    public static void main(String[] a) {
        new CourseTrackerMainGUI("Course Tracker");
    }

    private static void add(String term) {
        new Thread(() -> {
            SemesterGUI courseUpdater = new SemesterGUI(term);
            while (true) {
                courseUpdater.updateCourses();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }).run();
    }

    private CourseTrackerMainGUI(String name) {
        super(name);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Set up the content pane.
        addComponentsToPane(getContentPane());
        //Display the window.
        pack();
        setSize(150, 100);
        setVisible(true);
        btn.addActionListener(e -> {
            SwingWorker worker = new SwingWorker() {
                protected String doInBackground() {
                    add(input.getText());
                    return null;
                }
            };
            worker.execute();
        });
    }

    private static JButton btn;
    private static JTextField input;

    private void addComponentsToPane(final Container pane) {
        JPanel mainPane = new JPanel();
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));
        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout());
        input = new JTextField();
        mainPane.add(input);
        btn = new JButton("Continue...");
        mainPane.add(btn);
        mainPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pane.add(mainPane, BorderLayout.CENTER);
    }

}
