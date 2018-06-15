package com.marstanjx.usccoursetracker;
/*
 * Created by david on 11/7/2017.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import java.awt.*;

class CourseTrackerGUI extends JFrame {

    CourseTrackerGUI(String term, SemesterGUI semesterGUI) {
        super("Course Tracker (" + term + ")");
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
        setSize(650, 1000);
        setVisible(true);
    }

    JPanel mainPane;
    JTextArea courseinput;

    private void addComponentsToPane(final Container pane) {
        mainPane = new JPanel();
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));
        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout());
        mainPane.add(new JLabel("Tracker"));
        mainPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pane.add(mainPane, BorderLayout.CENTER);
        courseinput = new JTextArea();
        courseinput.setSize(650,200);
        mainPane.add(courseinput);
    }

}