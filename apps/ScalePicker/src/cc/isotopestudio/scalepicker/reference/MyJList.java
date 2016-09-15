package cc.isotopestudio.scalepicker.reference;/*
 * Created by Mars on 9/14/2016.
 * Copyright ISOTOPE Studio
 */

/**
 * CheckBoxList
 */

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class MyJList extends JFrame {

    protected JList m_list;
    protected JLabel m_total;

    public MyJList() {
        super("Swing List [Check boxes]");
        setSize(260, 240);
        getContentPane().setLayout(new FlowLayout());

        InstallData[] options = {new InstallData("Program executable", 118),
                new InstallData("Help files", 52),
                new InstallData("Tools and converters", 83),
                new InstallData("Source code", 133)};

        m_list = new JList(options);
        CheckListCellRenderer renderer = new CheckListCellRenderer();
        m_list.setCellRenderer(renderer);
        m_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        CheckListener lst = new CheckListener(this);
        m_list.addMouseListener(lst);
        m_list.addKeyListener(lst);

        JScrollPane ps = new JScrollPane();
        ps.getViewport().add(m_list);

        m_total = new JLabel("Space required: 0K");

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(ps, BorderLayout.CENTER);
        p.add(m_total, BorderLayout.SOUTH);
        p.setBorder(new TitledBorder(new EtchedBorder(),
                "Please select options:"));
        getContentPane().add(p);

        WindowListener wndCloser = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(wndCloser);

        setVisible(true);

        recalcTotal();
    }

    public void recalcTotal() {
        ListModel model = m_list.getModel();
        int total = 0;
        for (int k = 0; k < model.getSize(); k++) {
            InstallData data = (InstallData) model.getElementAt(k);
            if (data.isSelected())
                total += data.getSize();
        }
        m_total.setText("Space required: " + total + "K");
    }

    public static void main(String argv[]) {
        new MyJList();
    }
}

class CheckListCellRenderer extends JCheckBox implements ListCellRenderer {
    protected static Border m_noFocusBorder = new EmptyBorder(1, 1, 1, 1);

    public CheckListCellRenderer() {
        super();
        setOpaque(true);
        setBorder(m_noFocusBorder);
    }

    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.toString());

        setBackground(isSelected ? list.getSelectionBackground() : list
                .getBackground());
        setForeground(isSelected ? list.getSelectionForeground() : list
                .getForeground());

        InstallData data = (InstallData) value;
        setSelected(data.isSelected());

        setFont(list.getFont());
        setBorder((cellHasFocus) ? UIManager
                .getBorder("List.focusCellHighlightBorder") : m_noFocusBorder);

        return this;
    }
}

class CheckListener implements MouseListener, KeyListener {
    protected MyJList m_parent;
    protected JList m_list;

    public CheckListener(MyJList parent) {
        m_parent = parent;
        m_list = parent.m_list;
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getX() < 20)
            doCheck();
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == ' ')
            doCheck();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    protected void doCheck() {
        int index = m_list.getSelectedIndex();
        if (index < 0)
            return;
        InstallData data = (InstallData) m_list.getModel().getElementAt(index);
        data.invertSelected();
        m_list.repaint();
        m_parent.recalcTotal();
    }
}
