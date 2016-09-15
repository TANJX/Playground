package cc.isotopestudio.scalepicker.reference;/*
 * Created by Mars on 9/14/2016.
 * Copyright ISOTOPE Studio
 */

public class InstallData {
    protected String m_name;
    protected int m_size;
    protected boolean m_selected;

    public InstallData(String name, int size) {
        m_name = name;
        m_size = size;
        m_selected = false;
    }

    public String getName() {
        return m_name;
    }

    public int getSize() {
        return m_size;
    }

    public void setSelected(boolean selected) {
        m_selected = selected;
    }

    public void invertSelected() {
        m_selected = !m_selected;
    }

    public boolean isSelected() {
        return m_selected;
    }

    public String toString() {
        return m_name + " (" + m_size + " K)";
    }
}