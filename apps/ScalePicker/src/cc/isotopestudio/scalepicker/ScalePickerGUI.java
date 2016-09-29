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
    private JButton dominantbtn;
    private JButton diminishedbtn;
    private JButton clearbtn;

    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("ScalePickerGUI 1.1.0");
        frame.setContentPane(new ScalePickerGUI().MainPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(1300, 700));
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
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        createList();

        scale1btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numSlider.getValue(); i++) {
                    addLine((++count + ": " + ScaleResult.genRandom(ScaleType.SCALE1)));
                }
                addLine("-----------");
            }
        });
        scales3btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numSlider.getValue(); i++) {
                    addLine((++count + ": " + ScaleResult.genRandom(ScaleType.SCALE3)));
                }
                addLine("-----------");
            }
        });
        scales6btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numSlider.getValue(); i++) {
                    addLine((++count + ": " + ScaleResult.genRandom(ScaleType.SCALE6)));
                }
                addLine("-----------");
            }
        });
        legato3btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numSlider.getValue(); i++) {
                    addLine((++count + ": " + ScaleResult.genRandom(ScaleType.SCALEIN3)));
                }
                addLine("-----------");
            }
        });
        cs3abtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numSlider.getValue(); i++) {
                    addLine((++count + ": " + ScaleResult.genRandom(ScaleType.CHROMATICSCALESAPART)));
                }
                addLine("-----------");
            }
        });
        cs3btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numSlider.getValue(); i++) {
                    addLine((++count + ": " + ScaleResult.genRandom(ScaleType.CHROMATICSCALES)));
                }
                addLine("-----------");
            }
        });
        wholeTonebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numSlider.getValue(); i++) {
                    addLine((++count + ": " + ScaleResult.genRandom(ScaleType.WHOLETONE)));
                }
                addLine("-----------");
            }
        });
        arpeggiosbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numSlider.getValue(); i++) {
                    addLine((++count + ": " + ScaleResult.genRandom(ScaleType.ARPEGGIOS)));
                }
                addLine("-----------");
            }
        });
        dominantbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numSlider.getValue(); i++) {
                    addLine((++count + ": " + ScaleResult.genRandom(ScaleType.DOMINANT)));
                }
                addLine("-----------");
            }
        });
        diminishedbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numSlider.getValue(); i++) {
                    addLine((++count + ": " + ScaleResult.genRandom(ScaleType.DIMINISHED)));
                }
                addLine("-----------");
            }
        });

        generateAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < numSlider.getValue(); i++)
                    addLine((++count + ": " + ScaleResult.genRandom(ScaleType.SCALE1)));
                for (int i = 0; i < numSlider.getValue(); i++)
                    addLine((++count + ": " + ScaleResult.genRandom(ScaleType.SCALE3)));
                for (int i = 0; i < numSlider.getValue(); i++)
                    addLine((++count + ": " + ScaleResult.genRandom(ScaleType.SCALE6)));
                for (int i = 0; i < numSlider.getValue(); i++)
                    addLine((++count + ": " + ScaleResult.genRandom(ScaleType.SCALEIN3)));
                for (int i = 0; i < numSlider.getValue(); i++)
                    addLine((++count + ": " + ScaleResult.genRandom(ScaleType.CHROMATICSCALESAPART)));
                for (int i = 0; i < numSlider.getValue(); i++)
                    addLine((++count + ": " + ScaleResult.genRandom(ScaleType.CHROMATICSCALES)));
                for (int i = 0; i < numSlider.getValue(); i++)
                    addLine((++count + ": " + ScaleResult.genRandom(ScaleType.WHOLETONE)));
                for (int i = 0; i < numSlider.getValue(); i++)
                    addLine((++count + ": " + ScaleResult.genRandom(ScaleType.ARPEGGIOS)));
                for (int i = 0; i < numSlider.getValue(); i++)
                    addLine((++count + ": " + ScaleResult.genRandom(ScaleType.DOMINANT)));
                for (int i = 0; i < numSlider.getValue(); i++)
                    addLine((++count + ": " + ScaleResult.genRandom(ScaleType.DIMINISHED)));
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException ignored) {
                }
                addLine("-----------");
            }
        });

        infobtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(frame, "ScalePickerGUI 1.0.0\nISOTOPE Studio, Mars\n2016.9.29",
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
        clearbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count = 0;
                createList();
            }
        });
    }

    private boolean firstTime = true;

    private void createList() {
        firstTime = true;
        listModel = new DefaultListModel<>();
        list1.setModel(listModel);
        listModel.addElement("Click to generate execise");
        listModel.addElement("---");
        listModel.addElement("ScalePicker 1.1.1");
        listModel.addElement("---");
        listModel.addElement("ISOTOPE Studio");
        listModel.addElement("Mars");
        listModel.addElement("Sept. 26, 2016");
    }

    private void addLine(String line) {
        if (firstTime) {
            listModel.clear();
            firstTime = !firstTime;
        }
        listModel.addElement(line);
        listScroll.getViewport().doLayout();
        JScrollBar bar = listScroll.getVerticalScrollBar();
        bar.setValue(bar.getMaximum());
    }

}