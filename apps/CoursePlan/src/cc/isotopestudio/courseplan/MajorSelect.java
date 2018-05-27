package cc.isotopestudio.courseplan;
/*
 * Created by david on 2/18/2018.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static cc.isotopestudio.courseplan.CourseReader.majors;

public class MajorSelect {
    private JPanel mainPane;
    private JComboBox major1Select;
    private JComboBox major2Select;
    private JButton doneButton;
    private JComboBox major3Select;


    public MajorSelect() {
        JFrame frame = new JFrame("MajorSelect");
        frame.setContentPane(mainPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        major1Select.addItem(" - ");
        major2Select.addItem(" - ");
        major3Select.addItem(" - ");
        Set<String> majors = new HashSet<>(CourseReader.majors.keySet());
        Set<String> remainMajors = new HashSet<>(CourseReader.majors.keySet());
        majors.remove("ge");
        remainMajors.remove("ge");
        for (String major : majors) {
            major1Select.addItem(major);
            major2Select.addItem(major);
            major3Select.addItem(major);
        }
        major1Select.addActionListener(e -> {
            if (!major1Select.getSelectedItem().equals(" - ")) {
                major2Select.removeItem(major1Select.getSelectedItem());
            }
        });
        major2Select.addActionListener(e -> {
            if (!major2Select.getSelectedItem().equals(" - ")) {
                major1Select.removeItem(major2Select.getSelectedItem());
            }
        });
        doneButton.addActionListener(e -> {
            System.out.println(CourseReader.majors.keySet());
            List<Course> courses = new ArrayList<>(CourseReader.majors.get("ge"));
            System.out.println(major1Select.getSelectedItem());
            System.out.println(major1Select.getSelectedItem().getClass().getName());
            if (!major1Select.getSelectedItem().equals(" - "))
                courses.addAll(CourseReader.majors.get(major1Select.getSelectedItem().toString()));
            if (!major2Select.getSelectedItem().equals(" - "))
                courses.addAll(CourseReader.majors.get(major2Select.getSelectedItem().toString()));
            if (!major3Select.getSelectedItem().equals(" - "))
                courses.addAll(CourseReader.majors.get(major3Select.getSelectedItem().toString()));
            new CoursePlanGUI(courses);
        });
    }

    public static void main(String[] args) {
        CourseReader.init();
        System.out.println(majors);
        new MajorSelect();
    }

}
