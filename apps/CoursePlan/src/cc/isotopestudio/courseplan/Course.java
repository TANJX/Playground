package cc.isotopestudio.courseplan;
/*
 * Created by david on 9/4/2017.
 * Copyright ISOTOPE Studio
 */

import java.util.List;

public class Course {
    private final int unit;
    private final List<String> courses;

    Course(int credit, List<String> courses) {
        this.unit = credit;
        this.courses = courses;
    }

    public int getUnit() {
        return unit;
    }

    public List<String> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(courses.get(0));
        for (int i = 1; i < courses.size(); i++) {
            sb.append(", ").append(courses.get(i));
        }
        sb.append(" (").append(unit).append(")");
        return sb.toString();
    }
}
