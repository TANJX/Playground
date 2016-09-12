package app.seatArrangement;

class Student {
    public String name;
    private int seat = 0;

    Student(String name) {
        this.name = name;
    }

    void setSeat(int n) {
        seat = n;
    }

    public String toString() {
        return name + "," + seat;
    }

}
