package basic.recursion;

import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author student
 */
public class Main {

    static int ta(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return ta(n - 1) * 2 + 1;
    }

    static int shulie(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return shulie(n - 1) + shulie(n - 1);
    }

    static String step(int n) {
        StringBuffer result = new StringBuffer("");
        if (n == 0) {
            return result.append("null").toString();
        }
        if (n == 1) {
            result.append("A TO C\n");
            return result.toString();
        } else {
            result.append("A TO B\n");
            result.append(step(n-1));
            result.append("B TO C\n");
        }
        return result.toString();
    }

    static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return factorial(n - 1) * n;
    }
    static JFrame frame = new JFrame("Ta");
    static JPanel pane = new JPanel();
    static JScrollPane  rightPane = new JScrollPane();
    static JTextArea textArea = new JTextArea();
    static JSpinner spinner = new JSpinner();

    public static void main(String args[]) {

        spinner.setBounds(20, 40, 100, 30);
        rightPane.setBounds(250, 10, 200, 70);
        rightPane.setViewportView(textArea);
        pane.setLayout(null);
        frame.add(pane);

        pane.add(spinner);
        pane.add(rightPane);
        textArea.setText("\nººÅµËþ: " + ta(0) + "\nì³²¨À­ÆõÊýÁÐ: " + shulie(0) + "\n½×³Ë: " + factorial(0));
        spinner.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                int value = (int) spinner.getValue();
                if (value >= 0 || value <= 25) {
                   textArea.setText("\nººÅµËþ: " + ta(value) + "\nì³²¨À­ÆõÊýÁÐ: " + shulie(value) + "\n½×³Ë: " + factorial(value));
                   //textArea.setText("\nººÅµËþ: " + step(value));
                    
                } else {
                    spinner.setValue(0);
                }
            }
        });

        frame.setSize(500, 130);
        frame.setVisible(true);
    }
}
