package basic.searching;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JFrame;

/**
 *
 * @author Mars
 */
public class Main {

    public static int sequentialSearch(int[] list, int a) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == a) {
                return i;
            }
        }
        return -1;
    }

    public static int sequentialSearch(ArrayList<Integer> list, int a) {
        for (int i = 0; list != null && i < list.size(); i++) {
            if (list.get(i) == a) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] list, int a) {
        int low = 0, high = list.length - 1;
        int temp;
        while (low <= high && (low <= list.length - 1)
                && (high <= list.length - 1)) {
            temp = (low + high) / 2;
            System.out.println(low + "\t" + temp + "\t" + high);
            if (list[temp] > a) {
                high = temp - 1;
            } else if (list[temp] < a) {
                low = temp + 1;
            } else {
                return temp;
            }
        }
        return -1;
    }

    public static int binarySearch2(int[] list, int a) {
        int low = 0, high = list.length - 1;
        int temp;
        while (low <= high && (low <= list.length - 1)
                && (high <= list.length - 1)) {
            temp = (low + high) / 2;
            System.out.println(low + "\t" + temp + "\t" + high);
            if (list[temp] > a) {
                high = temp - 1;
            } else if (list[temp] < a) {
                low = temp + 1;
            } else {
                return temp;
            }
        }
        return -1;
    }

    public static int binarySearch2(int[] list, int a, int low, int high) {
        int temp;
        while (low <= high && (low <= list.length - 1)
                && (high <= list.length - 1)) {
            temp = (low + high) / 2;
            System.out.println(low + "\t" + temp + "\t" + high);
            if (list[temp] > a) {
                return binarySearch2(list, a, low, temp - 1);
            } else if (list[temp] < a) {
                return binarySearch2(list, a, temp + 1, high);
            } else {
                return temp;
            }
        }
        return -1;
    }
    static int[] list;
    static JFrame frame = new JFrame("Sequential Search");
    static JPanel pane = new JPanel();
    static JScrollPane rightPane = new JScrollPane();
    static JScrollPane middlePane = new JScrollPane();
    static JTextArea textArea = new JTextArea();
    static JSpinner num = new JSpinner();
    static JSpinner target = new JSpinner();
    static JButton addButton = new JButton("Add");
    static JButton clearButton = new JButton("Reset");
    static JButton findButton = new JButton("Find");
    static DefaultListModel listModel = new DefaultListModel();
    static JList jlist = new JList(listModel);
    static JLabel label = new JLabel("Result:");

    public static void main(String args[]) {
        num.setBounds(20, 10, 70, 30);
        target.setBounds(20, 50, 70, 30);
        addButton.setBounds(110, 10, 70, 30);
        clearButton.setBounds(110, 50, 70, 30);
        findButton.setBounds(200, 10, 60, 70);
        middlePane.setBounds(280, 10, 60, 70);
        label.setBounds(350, 10, 70, 20);
        rightPane.setBounds(350, 40, 70, 40);
        rightPane.setViewportView(textArea);
        middlePane.setViewportView(jlist);
        pane.setLayout(null);
        frame.add(pane);

        pane.add(num);
        pane.add(target);
        pane.add(addButton);
        pane.add(clearButton);
        pane.add(findButton);
        pane.add(middlePane);
        pane.add(label);
        pane.add(rightPane);

        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (list == null) {
                    list = new int[1];
                    list[0] = (int) num.getValue();
                    listModel.addElement("" + (int) num.getValue());
                } else {
                    int[] temp = list;
                    list = new int[temp.length + 1];
                    for (int i = 0; i < temp.length; i++) {
                        list[i] = temp[i];
                    }
                    list[temp.length] = (int) num.getValue();
                    listModel.addElement("" + (int) num.getValue());
                }
            }
        });

        findButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (list == null) {
                    textArea.setText("No Value!");
                } else {
                    //int result = sequentialSearch(list, (int) target.getValue());
                    //int result = binarySearch(list, (int) target.getValue());
                    int result = binarySearch2(list, (int) target.getValue(), 0, list.length - 1);
                    if (result == -1) {
                        textArea.setText("No result!");
                    } else {
                        textArea.setText(result + "");
                    }
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                list = null;
                listModel.clear();
                textArea.setText("Cleared!");
            }
        });

        frame.setSize(500, 130);
        frame.setVisible(true);
    }
}
