package cc.isotopestudio.advancedcopy;
/*
 * Created by Mars Tan on 3/12/2017.
 * Copyright ISOTOPE Studio
 */

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AdvancedCopyGUI {
    private static final String VERSION = "1.0.0";

    private JPanel paneA;
    private JPanel paneB;
    private JPanel paneC;
    private JButton chooseFolderAButton;
    private JTextField folderATextPane;
    private JButton chooseFolderBButton;
    private JTextField folderBTextPane;
    private JTextField folderCTextPane;
    private JButton chooseFolderCButton;
    private JPanel mainPane;
    private JButton startbtn;
    private JPanel infoPane;
    private JButton infobtn;
    private JButton exitbtn;

    public static void main(String[] args) {
        new AdvancedCopyGUI();
    }

    private File folderA;
    private File folderB;
    private File folderC;

    private AdvancedCopyGUI() {

        JFrame frame = new JFrame("Advanced Copy " + VERSION);
        frame.setContentPane(mainPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        Toolkit toolkit = frame.getToolkit();
        Dimension size = toolkit.getScreenSize(); // resolution of the monitor
        frame.setLocation(size.width / 2 - frame.getWidth() / 2, (size.height - 50) / 2 - frame.getHeight() / 2);


        chooseFolderAButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(false);
            chooser.setCurrentDirectory(new File("."));
            chooser.setDialogTitle("Select Folder A");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = chooser.showOpenDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File folder = chooser.getSelectedFile();
                if (folder.isDirectory()) {
                    folderA = folder;
                    folderATextPane.setText(folderA.getAbsolutePath());
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(frame, "Please Select a Folder",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        chooseFolderBButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(false);
            chooser.setCurrentDirectory(new File("."));
            chooser.setDialogTitle("Select Folder B");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = chooser.showOpenDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File folder = chooser.getSelectedFile();
                if (folder.isDirectory()) {
                    folderB = folder;
                    folderBTextPane.setText(folderB.getAbsolutePath());
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(frame, "Please Select a Folder",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        chooseFolderCButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(false);
            chooser.setCurrentDirectory(new File("."));
            chooser.setDialogTitle("Select Folder C");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = chooser.showOpenDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File folder = chooser.getSelectedFile();
                if (folder.isDirectory()) {
                    folderC = folder;
                    folderCTextPane.setText(folderC.getAbsolutePath());
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(frame, "Please Select a Folder",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        startbtn.addActionListener(e -> {
            if (folderA == null || folderB == null || folderC == null) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(frame, "Please select the folder",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                startbtn.setEnabled(false);
                Thread updateProgressThread = new Thread(() -> {
                    ProgressGUI progressGUI = new ProgressGUI();
                    FileManager fileManager = new FileManager(progressGUI, folderA, folderB, folderC);
                    fileManager.ingestFolderC();
                    fileManager.copyToC();
                    int percent = fileManager.getFinishedPercentage();
                    while (percent < 100) {
                        progressGUI.progressBar1.setValue(percent);
                        progressGUI.progressBar1.setString(percent + "%");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        percent = fileManager.getFinishedPercentage();
                    }
                    progressGUI.progressBar1.setValue(100);
                    progressGUI.progressBar1.setString("FINISHED");
                    startbtn.setEnabled(true);
                });
                updateProgressThread.start();
            }
        });
        infobtn.addActionListener(e -> {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(frame, "Advanced Copy " + VERSION + "\nISOTOPE Studio, Mars\n2017.3.12",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        exitbtn.addActionListener(e -> {
            frame.dispose();
            System.exit(0);
        });
    }
}
