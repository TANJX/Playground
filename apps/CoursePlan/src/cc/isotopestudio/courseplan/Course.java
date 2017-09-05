package cc.isotopestudio.courseplan;
/*
 * Created by david on 9/4/2017.
 * Copyright ISOTOPE Studio
 */

public enum Course {
    GE_A_1(4, CourseType.GE),
    GE_B_1(4, CourseType.GE),
    GE_B_2(4, CourseType.GE),
    GE_C_1(4, CourseType.GE),
    GE_C_2(4, CourseType.GE),
    GE_D_1(4, CourseType.GE),

    COMM_200(4, CourseType.COMM),
    COMM_206(4, CourseType.COMM, "COMM 206 or 311"),
    COMM_209(4, CourseType.COMM, "COMM 209 or 309"),
    COMM_204(4, CourseType.COMM, "COMM 204 or 322"),
    COMM_301(4, CourseType.COMM, "COMM 301 or 305"),
    ASCJ_200(4, CourseType.COMM, "ASCJ 200 or 210"),
    COMM_ELECTIVE_1(4, CourseType.COMM),
    COMM_ELECTIVE_2(4, CourseType.COMM),
    COMM_ELECTIVE_3(4, CourseType.COMM),
    COMM_ELECTIVE_4(4, CourseType.COMM),
    COMM_ELECTIVE_5(4, CourseType.COMM),
    COMM_ELECTIVE_6(4, CourseType.COMM),

    MUEN_300(2, CourseType.MUSC),
    PIAN_301(6, CourseType.MUSC),
    MUCO_130a(3, CourseType.MUSC),
    MUCO_130b(3, CourseType.MUSC),

    CSCI_103(4, CourseType.CSCI),
    CSCI_109(2, CourseType.CSCI),
    CSCI_104(4, CourseType.CSCI),
    CSCI_170(4, CourseType.CSCI),
    CSCI_201(4, CourseType.CSCI, "CSCI 201 or 270"),
    CSCI_ELECTIVE_1(4, CourseType.CSCI),
    CSCI_ELECTIVE_2(4, CourseType.CSCI),
    CSCI_ELECTIVE_3(4, CourseType.CSCI),
    WRIT_150(4, CourseType.WRIT),
    WRIT_340(4, CourseType.WRIT);

    final int credit;
    final CourseType type;
    String desc;

    Course(int credit, CourseType type) {
        this.credit = credit;
        this.type = type;
    }

    Course(int credit, CourseType type, String desc) {
        this.credit = credit;
        this.type = type;
        this.desc = desc;
    }
}
