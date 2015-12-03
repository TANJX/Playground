package vocPlan;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainVoc {
	static Locale locale = new Locale("en", "US");
	static DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	static DateFormat fullDateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);

	static JFrame frame = new JFrame("Vocabulary Plan");
	static JPanel panel1 = new JPanel();
	static JLabel label1 = new JLabel("Input DATE: ");
	static JLabel ly = new JLabel("YEAR: ");
	static JLabel lm = new JLabel("MONTH: ");
	static JLabel ms = new JLabel("DAY: ");
	static JSpinner sy = new JSpinner();
	static JSpinner sm = new JSpinner();
	static JSpinner sd = new JSpinner();
	static JLabel label2 = new JLabel("Input number of LISTs: ");
	static JSpinner slist = new JSpinner();
	static JScrollPane scroll = new JScrollPane(panel1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	static JLabel label3 = new JLabel("New list on: ");
	static JCheckBox c1 = new JCheckBox("Monday");
	static JCheckBox c2 = new JCheckBox("Tuesday");
	static JCheckBox c3 = new JCheckBox("Wednesday");
	static JCheckBox c4 = new JCheckBox("Thrusday");
	static JCheckBox c5 = new JCheckBox("Friday");
	static JCheckBox c6 = new JCheckBox("Saturday");
	static JCheckBox c7 = new JCheckBox("Sunday");

	public static void main(String[] args) throws ParseException {

		frame.setSize(700, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Center on screen
		Toolkit toolkit = frame.getToolkit();
		Dimension size = toolkit.getScreenSize(); // resolution of the monitor
		frame.setLocation(size.width / 2 - frame.getWidth() / 2, size.height / 2 - frame.getHeight() / 2);

		panel1.setLayout(null);

		label1.setBounds(30, 10, 80, 30);
		panel1.add(label1);

		ly.setBounds(50, 40, 50, 30);
		panel1.add(ly);

		lm.setBounds(170, 40, 50, 30);
		panel1.add(lm);

		ms.setBounds(280, 40, 50, 30);
		panel1.add(ms);

		sy.setBounds(100, 40, 50, 30);
		sy.setValue(Calendar.getInstance().get(Calendar.YEAR));
		panel1.add(sy);

		sm.setBounds(220, 40, 50, 30);
		sm.setValue(Calendar.getInstance().get(Calendar.MONTH) + 1);
		panel1.add(sm);

		sd.setBounds(320, 40, 50, 30);
		sd.setValue(Calendar.getInstance().get(Calendar.DATE));
		panel1.add(sd);

		label2.setBounds(400, 10, 150, 30);
		panel1.add(label2);

		slist.setBounds(480, 40, 50, 30);
		slist.setValue(10);
		panel1.add(slist);

		label3.setBounds(30, 80, 150, 30);
		panel1.add(label3);

		c1.setBounds(40, 100, 80, 30);
		c1.setSelected(true);
		panel1.add(c1);

		c2.setBounds(120, 100, 80, 30);
		c2.setSelected(true);
		panel1.add(c2);

		c3.setBounds(200, 100, 100, 30);
		c3.setSelected(true);
		panel1.add(c3);

		c4.setBounds(300, 100, 80, 30);
		c4.setSelected(true);
		panel1.add(c4);

		c5.setBounds(380, 100, 80, 30);
		c5.setSelected(true);
		panel1.add(c5);

		c6.setBounds(460, 100, 80, 30);
		panel1.add(c6);

		c7.setBounds(540, 100, 80, 30);
		panel1.add(c7);
		//
		frame.add(panel1, BorderLayout.CENTER);
		JButton button = new JButton("Done!");
		button.setBounds(580, 40, 70, 30);
		panel1.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (c1.isSelected() || c2.isSelected() || c3.isSelected() || c4.isSelected() || c5.isSelected()
						|| c6.isSelected() || c7.isSelected()) {
					JFrame listFrame = new JFrame("Plan");
					JTextArea textArea = new JTextArea();
					textArea.setEditable(false);
					listFrame.setSize(600, 700);
					System.out.println("Button clicked");
					try {
						textArea.setText(result((int) sy.getValue(), (int) sm.getValue(), (int) sd.getValue(),
								(int) slist.getValue()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					JPanel contentPane = new JPanel();
					contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
					contentPane.setLayout(new BorderLayout(0, 0));
					listFrame.setContentPane(contentPane);
					JScrollPane scrollPane = new JScrollPane();
					contentPane.add(scrollPane, BorderLayout.CENTER);
					scrollPane.setViewportView(textArea);

					JButton export = new JButton("Export to Excel Format");
					JPanel buttonPane = new JPanel();
					buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
					buttonPane.add(export);
					listFrame.add(buttonPane, BorderLayout.SOUTH);

					listFrame.setLocation(size.width / 2 - frame.getWidth() / 2,
							size.height / 2 - frame.getHeight() / 2 - 300);
					listFrame.setVisible(true);
				} else
					JOptionPane.showMessageDialog(frame, "You have to select at least one day to get your new list! ");
			}
		});

		sy.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int temp = (int) sy.getValue();
				if (temp <= 2014)
					sy.setValue(2014);
			}
		});
		sm.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int temp = (int) sm.getValue();
				if (temp <= 0)
					sm.setValue(1);
				else if (temp > 12)
					sm.setValue(12);
			}
		});
		sd.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int temp = (int) sd.getValue();
				if (temp <= 0)
					sd.setValue(1);
				else if (temp > 31)
					sd.setValue(31);
			}
		});
		slist.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int temp = (int) slist.getValue();
				if (temp <= 0)
					slist.setValue(1);
			}
		});

		frame.setVisible(true);

	}

	public static String result(int y, int m, int d, int listNum) throws ParseException {
		StringBuffer result = new StringBuffer("");
		int[][] list = new int[listNum + 1][2];//
		for (int i = 1; i <= listNum; i++) {
			list[i][1] = 0;
			list[i][0] = 0;
		}

		result.append("Here is your voc plan:\n" + "\n");
		Date bdate;
		bdate = format1.parse(y + "-" + m + "-" + d);
		Calendar cal = Calendar.getInstance();
		cal.setTime(bdate);

		int day = 1, listProgress = 0;
		while (status2(list)) {
			if ((cal.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)
					&& cal.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)
					&& cal.get(Calendar.DATE) == Calendar.getInstance().get(Calendar.DATE)))
				result.append("**Today: ");
			else
				result.append("\t");
			result.append(fullDateFormat.format(cal.getTime()) + ": \t\t");
			if (ifNewList(y, m, d, day) && listProgress < listNum) {
				listProgress++;
				list[listProgress][0] = day;
				result.append("(new!)" + listProgress + ", ");
			}

			for (int i = 1; i <= listNum; i++) {
				if (list[i][0] != 0 && (day - list[i][0] == 1 || day - list[i][0] == 2 || day - list[i][0] == 4
						|| day - list[i][0] == 7 || day - list[i][0] == 14)) {
					result.append(i + ", ");
					list[i][1]++;
				}
			}
			result.append("\n");
			cal.add(Calendar.DAY_OF_MONTH, 1);
			day++;
		}
		result.append("Days: " + day + "\n");
		return result.toString();
	}

	static boolean status1(int[] list) {
		for (int i = 1; i < list.length; i++) {
			if (list[i] != 5)
				return true;
		}
		return false;
	}

	static boolean status2(int[][] list) {
		for (int i = 1; i < list.length; i++) {
			if (list[i][1] != 5)
				return true;
		}
		return false;
	}

	public static boolean ifNewList(int y, int m, int d, int day) throws ParseException {
		String date = y + "-" + m + "-" + (d + day - 1);
		Date bdate = format1.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(bdate);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY && c1.isSelected()) {
			return true;
		}
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY && c2.isSelected()) {
			return true;
		}
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY && c3.isSelected()) {
			return true;
		}
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY && c4.isSelected()) {
			return true;
		}
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && c5.isSelected()) {
			return true;
		}
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY && c6.isSelected()) {
			return true;
		}
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && c7.isSelected()) {
			return true;
		}
		return false;
	}

}
