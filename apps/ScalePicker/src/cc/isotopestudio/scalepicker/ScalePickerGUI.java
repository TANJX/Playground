package cc.isotopestudio.scalepicker;/*
 * Created by Mars on 9/12/2016.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScalePickerGUI {
    private JList<String> list1;
    private JButton scale1btn;
    private JButton scales3btn;
    private JButton scales6btn;
    private JButton legato3btn;
    private JButton cs3abtn;
    private JButton cs3btn;
    private JButton wholeTonebtn;
    private JButton arpeggiosbtn;
    private JButton generateAllButton;
    private JPanel MainPane;
    private JScrollPane listScroll;
    private JSlider numSlider;
    private JToolBar toolbar;
    private JButton infobtn;
    private JButton exitbtn;
    private JButton button1;
    private JButton button2;

    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("ScalePickerGUI 1.0.0");
        frame.setContentPane(new ScalePickerGUI().MainPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(1000, 500));
        frame.setVisible(true);
    }

    private DefaultListModel<String> listModel;
    private int count = 0;

    public ScalePickerGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Windows Look and feel
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        listModel = new DefaultListModel<>();
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list1.setModel(listModel);

        JScrollBar sBar = listScroll.getVerticalScrollBar();

        scale1btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numSlider.getValue(); i++) {
                    listModel.addElement((++count + ": " + ScaleResult.genRandom(ScaleType.SCALE1)));
                }
                sBar.setValue(sBar.getMaximum());
            }
        });
        scales3btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numSlider.getValue(); i++) {
                    listModel.addElement((++count + ": " + ScaleResult.genRandom(ScaleType.SCALE3)));
                }
                sBar.setValue(sBar.getMaximum());
            }
        });
        scales6btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numSlider.getValue(); i++) {
                    listModel.addElement((++count + ": " + ScaleResult.genRandom(ScaleType.SCALE6)));
                }
                sBar.setValue(sBar.getMaximum());
            }
        });
        legato3btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numSlider.getValue(); i++) {
                    listModel.addElement((++count + ": " + ScaleResult.genRandom(ScaleType.SCALEIN3)));
                }
                sBar.setValue(sBar.getMaximum());
            }
        });
        cs3abtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numSlider.getValue(); i++) {
                    listModel.addElement((++count + ": " + ScaleResult.genRandom(ScaleType.CHROMATICSCALESAPART)));
                }
                sBar.setValue(sBar.getMaximum());
            }
        });
        cs3btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numSlider.getValue(); i++) {
                    listModel.addElement((++count + ": " + ScaleResult.genRandom(ScaleType.CHROMATICSCALES)));
                }
                sBar.setValue(sBar.getMaximum());
            }
        });
        wholeTonebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numSlider.getValue(); i++) {
                    listModel.addElement((++count + ": " + ScaleResult.genRandom(ScaleType.WHOLETONE)));
                }
                sBar.setValue(sBar.getMaximum());
            }
        });
        arpeggiosbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numSlider.getValue(); i++) {
                    listModel.addElement((++count + ": " + ScaleResult.genRandom(ScaleType.ARPEGGIOS)));
                }
                sBar.setValue(sBar.getMaximum());
            }
        });


        generateAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numSlider.getValue(); i++)
                    listModel.addElement((++count + ": " + ScaleResult.genRandom(ScaleType.SCALE1)));
                for (int i = 0; i < numSlider.getValue(); i++)
                    listModel.addElement((++count + ": " + ScaleResult.genRandom(ScaleType.SCALE3)));
                for (int i = 0; i < numSlider.getValue(); i++)
                    listModel.addElement((++count + ": " + ScaleResult.genRandom(ScaleType.SCALE6)));
                for (int i = 0; i < numSlider.getValue(); i++)
                    listModel.addElement((++count + ": " + ScaleResult.genRandom(ScaleType.SCALEIN3)));
                for (int i = 0; i < numSlider.getValue(); i++)
                    listModel.addElement((++count + ": " + ScaleResult.genRandom(ScaleType.CHROMATICSCALESAPART)));
                for (int i = 0; i < numSlider.getValue(); i++)
                    listModel.addElement((++count + ": " + ScaleResult.genRandom(ScaleType.CHROMATICSCALES)));
                for (int i = 0; i < numSlider.getValue(); i++)
                    listModel.addElement((++count + ": " + ScaleResult.genRandom(ScaleType.WHOLETONE)));
                for (int i = 0; i < numSlider.getValue(); i++)
                    listModel.addElement((++count + ": " + ScaleResult.genRandom(ScaleType.ARPEGGIOS)));
                sBar.setValue(sBar.getMaximum());
            }
        });
        infobtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(frame, "ScalePickerGUI 1.0.0\nISOTOPE Studio, Mars\n2016.9.15",
                        "Info",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        exitbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });
    }

}