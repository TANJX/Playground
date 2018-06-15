package com.marstanjx.usccoursetracker;
/*
 * Created by david on 11/7/2017.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Course {
    SemesterGUI semesterGUI;
    private final String id;
    private final String department;
    private final String name;
    private final String instructor;
    private final String days;
    private final String start;
    private final String end;
    private int registered;
    private int available;
    private final JPanel coursePane = new JPanel();
    private JLabel r;
    private JLabel a;
    private JLabel status;
    JCheckBox select;
    private java.util.List<JLabel> infoLabels = new ArrayList<>();

    private static final FlowLayout experimentLayout = new FlowLayout();

    public Course(SemesterGUI semesterGUI, String id, String department, String name, String instructor, String days, String start, String end, int registered, int available) {
        this.semesterGUI = semesterGUI;
        this.id = id;
        this.department = department;
        this.name = name;
        this.instructor = instructor;
        this.days = days;
        this.start = start;
        this.end = end;
        this.registered = registered;
        this.available = available;
        coursePane.setLayout(experimentLayout);
        experimentLayout.setAlignment(FlowLayout.TRAILING);
        JLabel namel = new JLabel("<html><font color='green'>" + getName() + "</font></html>");
        namel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        coursePane.add(namel);
        infoLabels.add(namel);

        JLabel idl = new JLabel("<html><font color='green'>" + "(" + getId() + ")" + "</font></html>");
        idl.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        coursePane.add(idl);
        infoLabels.add(idl);

        JLabel ins = new JLabel("<html><font color='green'>" + getInstructor() + "</font></html>");
        ins.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        coursePane.add(ins);
        infoLabels.add(ins);

        JLabel tl = new JLabel("<html><font color='green'>" + getDays() + " " + getStart() + "-" + getEnd() + "</font></html>");
        tl.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        coursePane.add(tl);
        infoLabels.add(tl);

        r = new JLabel("<html><font color='blue'>" + registered + "</font></html>");
        r.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        coursePane.add(r);

        a = new JLabel("<html><font color='blue'>" + available + "</font></html>");
        a.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        coursePane.add(a);

        status = new JLabel(" ");
        coursePane.add(status);

        select = new JCheckBox();
        select.addActionListener(e -> {
            markConflict();
            if (!select.isSelected()) {
                status.setText("");
            }
            semesterGUI.coursePreview.paint();
        });
        coursePane.add(select);

        update();

        semesterGUI.courseList.add(this);

        semesterGUI.courseTrackerGUI.mainPane.add(coursePane);
    }

    public String getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getDays() {
        return days;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getRegistered() {
        return registered;
    }

    private void update() {
        if (registered >= available) {
            for (JLabel infoLabel : infoLabels) {
                infoLabel.setText(infoLabel.getText().replaceFirst("green", "gray"));
            }
        } else {
            for (JLabel infoLabel : infoLabels) {
                infoLabel.setText(infoLabel.getText().replaceFirst("gray", "green"));
            }
        }
        semesterGUI.courseTrackerGUI.mainPane.updateUI();
    }

    public int getAvailable() {
        return available;
    }

    public void setRegistered(int registered) {
        if (registered != this.registered) {
            this.registered = registered;
            r.setText("<html><font color='red'>" + registered + "</font></html>");
            update();
        }
    }

    public void setAvailable(int available) {
        if (available != this.available) {
            this.available = available;
            a.setText("<html><font color='red'>" + available + "</font></html>");
            update();
        }
    }

    public static Course get(SemesterGUI s, String id) {
        Optional<Course> first = s.courseList.stream().filter(course -> course.id.equals(id)).findFirst();
        return first.orElse(null);
    }

    public boolean markConflict() {
        int i = 0;
        boolean con = false;
        List<Course> selectedCourses = semesterGUI.courseList.stream().filter(course -> course.select.isSelected()).collect(Collectors.toList());
        Set<Course> confliced = new HashSet<>();
        for (Course c1 : selectedCourses) {
            if (confliced.contains(c1)) continue;
            boolean cc = false;
            loop:
            for (int j = i + 1; j < selectedCourses.size(); j++) {
                Course c2 = selectedCourses.get(j);
                if (c1 == c2) continue;
                for (char d : c1.days.toCharArray()) {
                    if (c2.days.contains(d + "")) {
                        String[] split = c1.start.split(":");
                        int c1s = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
                        split = c2.end.split(":");
                        int c2e = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
                        split = c1.end.split(":");
                        int c1e = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
                        split = c2.start.split(":");
                        int c2s = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
                        if ((c1s <= c2s && c2s <= c1e) || (c2s <= c1e && c1e <= c2e)) {
                            con = true;
                            cc = true;
                            confliced.add(c1);
                            confliced.add(c2);
                            c1.status.setText("<html><font color='red'>CON</font></html>");
                            c2.status.setText("<html><font color='red'>CON</font></html>");
//                            break loop;
                        }
                    }
                }
            }
            if (!cc) {
                c1.status.setText("OK");
            }
            i++;
        }
        return con;
    }


    @Override
    public String toString() {
        return "Course{" + "id='" + id + '\'' +
                ", registered=" + registered +
                ", available=" + available +
                "}\n";
    }
}
