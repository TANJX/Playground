package cc.isotopestudio.fileextensionrenamer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class MainPanel extends JPanel {
    private JFileChooser jFileChooser;
    private JTextField extentionField;
    private File files[];
    private JFrame listFrame;
    private JScrollPane pane;
    private JTextArea textArea;
    private JButton confirm;

    MainPanel() {
        JButton start = new JButton("Choose Files...");
        extentionField = new JTextField();
        extentionField.setColumns(10);
        add(new JLabel("New Extention"));
        //(if files have no extension, add '.' before new extension):
        add(extentionField);
        add(start);
        start.addActionListener(arg0 -> {
            jFileChooser = new JFileChooser();
            jFileChooser.setMultiSelectionEnabled(true);
            int returnVal = jFileChooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                files = jFileChooser.getSelectedFiles();
                listFrame = new JFrame("Files");
                pane = new JScrollPane();
                textArea = new JTextArea();
                textArea.setEditable(false);
                textArea.setText(info(files));
                pane.setViewportView(textArea);
                confirm = new JButton("Continue");
                confirm.addActionListener(arg01 -> {
                    rename(files);
                    JOptionPane.showMessageDialog(listFrame, "Succeed! ");
                    listFrame.remove(confirm);
                });
                listFrame.add(pane, BorderLayout.CENTER);
                listFrame.add(confirm, BorderLayout.SOUTH);
                listFrame.setSize(600, 700);
                listFrame.setVisible(true);
            }
        });
    }

    private String info(File[] files) {
        StringBuffer temp = new StringBuffer("Path:\n" + getFolderPath(files[0]) + "\nFiles:\n");
        for (File file : files) {
            temp.append(file.getName() + "\t->\t");
            temp.append(getPureName(file) + extentionField.getText() + "\n");
        }
        return temp.toString();
    }

    private String getFolderPath(File file) {
        StringBuffer path = new StringBuffer(file.getPath());
        for (int i = path.length() - 1; i > 0; i--) {
            if (path.charAt(i) == '\\') {
                path.delete(i + 1, path.length());
                break;
            }
        }
        return path.toString();
    }

    private String getPureName(File file) {
        StringBuffer name = new StringBuffer(file.getName());
        for (int i = name.length() - 1; i > 0; i--) {
            if (name.charAt(i) == '.') {
                name.delete(i + 1, name.length());
                break;
            }
        }
        System.out.println(name.toString());
        return name.toString();
    }

    private void rename(File[] files) {
        for (File file : files) {
            file.renameTo(new File(getFolderPath(file) + getPureName(file) + extentionField.getText()));
        }
    }
}
