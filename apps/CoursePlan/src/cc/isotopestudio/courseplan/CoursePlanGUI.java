package cc.isotopestudio.courseplan;
/*
 * Created by david on 9/4/2017.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class CoursePlanGUI {

    public static void main(String[] args) {
        new CoursePlanGUI();
    }

    private CoursePlanGUI() {
        JFrame frame = new JFrame("CoursePlanGUI");
        frame.setContentPane(mainPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        Toolkit toolkit = frame.getToolkit();
        Dimension size = toolkit.getScreenSize(); // resolution of the monitor
        frame.setLocation(size.width / 2 - frame.getWidth() / 2, size.height / 2 - frame.getHeight() / 2);
        frame.setVisible(true);
        comboboxLabelMap.put(cb1, creditLabel1);
        comboboxLabelMap.put(cb2, creditLabel2);
        comboboxLabelMap.put(cb3, creditLabel3);
        comboboxLabelMap.put(cb4, creditLabel4);
        comboboxLabelMap.put(cb5, creditLabel5);
        comboboxLabelMap.put(cb6, creditLabel6);
        comboboxLabelMap.put(cb7, creditLabel7);
        comboboxLabelMap.put(cb8, creditLabel8);
        cb.forEach(jComboBox -> {
            jComboBox.addItem(" - ");
            remainCourses.forEach(jComboBox::addItem);
        });
        labels.forEach(jLabel -> jLabel.setText("Credit(s): 0"));
        cbs.forEach(semester -> semester.forEach(
                box -> box.addActionListener(e -> {
                    if (box.getSelectedItem() instanceof Course) {
                        update(box, ((Course) box.getSelectedItem()));
                    } else {
                        update(box, null);
                    }
                    comboboxLabelMap.get(semester).setText("Credit(s): " + semester.stream().mapToInt(jComboBox -> {
                        if (jComboBox.getSelectedItem() instanceof Course) {
                            assert jComboBox.getSelectedItem() != null;
                            return ((Course) jComboBox.getSelectedItem()).credit;
                        } else {
                            return 0;
                        }
                    }).sum());
                })
        ));
        remainCreditLabel.setText("REMAINING CREDITS: " + remainCourses.stream().mapToInt(course -> course.credit).sum());
    }

    private void update(JComboBox box, Course course) {
        if (selctionMap.containsKey(box)) {
            Course oldCourse = selctionMap.remove(box);
            remainCourses.add(oldCourse);
            cb.stream().filter(jComboBox -> !Objects.equals(jComboBox, box)).forEach(jComboBox -> jComboBox.addItem(oldCourse));
        }
        if (course != null) {
            cb.stream().filter(jComboBox -> !Objects.equals(jComboBox, box)).forEach(jComboBox -> jComboBox.removeItem(course));
            selctionMap.put(box, course);
            remainCourses.remove(course);
        }
        remainCreditLabel.setText("REMAINING CREDITS: " + remainCourses.stream().mapToInt(single -> single.credit).sum());
    }

    private java.util.List<Course> remainCourses = new ArrayList<>(Arrays.asList(Course.values()));
    private Map<JComboBox, Course> selctionMap = new HashMap<>();

    private JPanel panel20173;
    private JPanel panel20181;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel mainPane;
    private JComboBox c1_1;
    private JComboBox c1_2;
    private JComboBox c1_3;
    private JComboBox c1_4;
    private JComboBox c1_5;
    private JComboBox c2_1;
    private JComboBox c2_2;
    private JComboBox c2_3;
    private JComboBox c2_4;
    private JComboBox c2_5;
    private JComboBox c3_1;
    private JComboBox c3_2;
    private JComboBox c3_3;
    private JComboBox c3_4;
    private JComboBox c3_5;
    private JComboBox c4_1;
    private JComboBox c4_2;
    private JComboBox c4_3;
    private JComboBox c4_4;
    private JComboBox c4_5;
    private JComboBox c5_1;
    private JComboBox c5_2;
    private JComboBox c5_3;
    private JComboBox c5_4;
    private JComboBox c5_5;
    private JComboBox c6_1;
    private JComboBox c6_2;
    private JComboBox c6_3;
    private JComboBox c6_4;
    private JComboBox c6_5;
    private JComboBox c7_1;
    private JComboBox c7_2;
    private JComboBox c7_3;
    private JComboBox c7_4;
    private JComboBox c7_5;
    private JComboBox c8_1;
    private JComboBox c8_2;
    private JComboBox c8_3;
    private JComboBox c8_4;
    private JComboBox c8_5;
    private JLabel creditLabel1;
    private JLabel creditLabel2;
    private JLabel creditLabel3;
    private JLabel creditLabel4;
    private JLabel creditLabel5;
    private JLabel creditLabel6;
    private JLabel creditLabel7;
    private JLabel creditLabel8;
    private JLabel remainCreditLabel;
    private JTree tree1;

    private final java.util.List<JComboBox> cb = Arrays.asList(c1_1, c1_2, c1_3, c1_4, c1_5,
            c2_1, c2_2, c2_3, c2_4, c2_5,
            c3_1, c3_2, c3_3, c3_4, c3_5,
            c4_1, c4_2, c4_3, c4_4, c4_5,
            c5_1, c5_2, c5_3, c5_4, c5_5,
            c6_1, c6_2, c6_3, c6_4, c6_5,
            c7_1, c7_2, c7_3, c7_4, c7_5,
            c8_1, c8_2, c8_3, c8_4, c8_5);
    private final java.util.List<JComboBox> cb1 = Arrays.asList(c1_1, c1_2, c1_3, c1_4, c1_5);
    private final java.util.List<JComboBox> cb2 = Arrays.asList(c2_1, c2_2, c2_3, c2_4, c2_5);
    private final java.util.List<JComboBox> cb3 = Arrays.asList(c3_1, c3_2, c3_3, c3_4, c3_5);
    private final java.util.List<JComboBox> cb4 = Arrays.asList(c4_1, c4_2, c4_3, c4_4, c4_5);
    private final java.util.List<JComboBox> cb5 = Arrays.asList(c5_1, c5_2, c5_3, c5_4, c5_5);
    private final java.util.List<JComboBox> cb6 = Arrays.asList(c6_1, c6_2, c6_3, c6_4, c6_5);
    private final java.util.List<JComboBox> cb7 = Arrays.asList(c7_1, c7_2, c7_3, c7_4, c7_5);
    private final java.util.List<JComboBox> cb8 = Arrays.asList(c8_1, c8_2, c8_3, c8_4, c8_5);
    private final java.util.List<JLabel> labels = Arrays.asList(creditLabel1, creditLabel2, creditLabel3, creditLabel4,
            creditLabel5, creditLabel6, creditLabel7, creditLabel8);

    private final java.util.List<List<JComboBox>> cbs = Arrays.asList(cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8);
    private final Map<List<JComboBox>, JLabel> comboboxLabelMap = new HashMap<>();
}