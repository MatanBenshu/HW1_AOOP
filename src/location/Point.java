package location;
import java.util.Objects;

public class Point {
    private int x;
    private int y;

    /*constructor*/
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /*copy constructor*/
    public Point(Point obj){
        this.x = obj.getX();
        this.y = obj.getY();
    }
    //------------ start of public method----------///

    //------------ start of getters and setters----------
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    //------------ end of getters and setters----------

    public double distanceFrom(Point p2){
        return Math.sqrt(Math.pow(this.x+ p2.getX(),2)+Math.pow(this.y+ p2.getY(),2));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return getX() == point.getX() && getY() == point.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    //------------ end of public method----------

}
