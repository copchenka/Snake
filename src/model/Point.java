package model;

public class Point {
    private int i;
    private int j;

    public Point(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point newObj = (Point) obj;
            if (newObj.i == this.i & newObj.j == this.j) return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return i * 31 + j * 31;
    }
}
