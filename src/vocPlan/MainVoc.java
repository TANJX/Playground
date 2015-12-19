package vocPlan;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.net.URLDecoder;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainVoc {
	static int[] repeatDay = { 1, 2, 4, 7, 14, 30 };
	static String[] repeatDayString = { "1", "2", "4", "7", "14", "30", "", "", "", "" };
	static final int repeatTimes = repeatDay.length;
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
	static JList repeatedList = new JList(repeatDayString);
	static JFileChooser jFileChooser = new JFileChooser();

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
		JButton button = new JButton("Done...");
		button.setBounds(580, 40, 80, 30);
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
								(int) slist.getValue(), 0));
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

					JButton export = new JButton("Export to Excel Format...");
					export.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
							try {
								jFileChooser.setCurrentDirectory(new File(getPath()));
							} catch (UnsupportedEncodingException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							int returnVal = jFileChooser.showOpenDialog(listFrame);
							if (returnVal == JFileChooser.APPROVE_OPTION) {
								File file = jFileChooser.getSelectedFile();
								String filename = (int) slist.getValue() + "-lists " + (int) sy.getValue() + "-"
										+ (int) sm.getValue() + "-" + (int) sd.getValue() + ".csv";
								String f = file.getAbsolutePath() + "\\" + filename;
								try {
									write(new File(f), result((int) sy.getValue(), (int) sm.getValue(),
											(int) sd.getValue(), (int) slist.getValue(), 1));
									JOptionPane.showMessageDialog(listFrame,
											"Success! \nFileName: " + filename + "\nPath: " + file.getAbsolutePath());
								} catch (ParseException e) {
									e.printStackTrace();
									JOptionPane.showMessageDialog(listFrame, "Error! ParseException");
								}
							}
						}

					});
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

	private static String result(int y, int m, int d, int listNum, int mode) throws ParseException {
		StringBuffer result = new StringBuffer("");
		StringBuffer excelResult = new StringBuffer("Date,New list,Review->\n");
		int[][] list = new int[listNum + 1][2];
		for (int i = 1; i <= listNum; i++) {
			list[i][1] = 0;
			list[i][0] = 0;
		}

		result.append("Here is your Vocabulary PLAN:\n" + "\n");
		Date bdate;
		bdate = format1.parse(y + "-" + m + "-" + d);
		Calendar cal = Calendar.getInstance();
		cal.setTime(bdate);

		int day = 1, listProgress = 0;
		while (status(list)) {
			if ((cal.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)
					&& cal.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)
					&& cal.get(Calendar.DATE) == Calendar.getInstance().get(Calendar.DATE)))
				result.append("**Today: ");
			else
				result.append("\t");
			result.append(fullDateFormat.format(cal.getTime()) + ": \t\t");
			excelResult.append(format1.format(cal.getTime()) + ",");
			if (ifNewList(y, m, d, day) && listProgress < listNum) {
				listProgress++;
				list[listProgress][0] = day;
				result.append("(new!)" + listProgress + ", ");
				excelResult.append(listProgress + ",");
			} else
				excelResult.append(",");

			for (int i = 1; i <= listNum; i++) {
				loop: {
					if (list[i][0] != 0)
						for (int o : repeatDay)
							if (day - list[i][0] == o) {
								result.append(i + ", ");
								excelResult.append(i + ",");
								list[i][1]++;
								break loop;
							}
				}
			}
			result.append("\n");
			excelResult.append("\n");
			cal.add(Calendar.DAY_OF_MONTH, 1);
			day++;
		}
		result.append("Days: " + day + "\n");
		if (mode == 0)
			return result.toString();
		else if (mode == 1)
			return excelResult.toString();
		else
			return "";
	}

	private static boolean status(int[][] list) {
		for (int i = 1; i < list.length; i++) {
			if (list[i][1] != repeatTimes)
				return true;
		}
		return false;
	}

	private static boolean ifNewList(int y, int m, int d, int day) throws ParseException {
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

	private static void write(File file, String text) {
		// String address, String fileName, String text
		// File file = new File(address + fileName);
		try (FileOutputStream fop = new FileOutputStream(file)) {
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// get the content in bytes
			byte[] contentInBytes = text.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getPath() throws UnsupportedEncodingException {
		StringBuffer path = new StringBuffer(System.getProperty("java.class.path") + "/");
		for (int i = path.length() - 4; i > 0; i--) {
			if (path.charAt(i) == '\\') {
				path.delete(i, path.length() - 1);
				break;
			}
		}
		for (int i = 0; i < path.length(); i++) {
			if (path.charAt(i) == '\\') {
				path.setCharAt(i, '/');
			}
		}
		return URLDecoder.decode(path.toString(), "UTF-8");
	}
}
