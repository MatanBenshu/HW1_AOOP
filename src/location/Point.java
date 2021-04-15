//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package location;
import java.util.Objects;

public class Point {
    private final int x;
    private final int y;

    /*constructor*/
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


    //------------ start of public method----------///

    //------------ start of getters and setters----------
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    //------------ end of getters and setters----------

    public double distanceFrom(Point p2){
        return Math.sqrt(Math.pow(this.x- p2.getX(),2)+Math.pow(this.y- p2.getY(),2));
    }

    public boolean equals(Point o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        return this.getX() == o.getX() && this.getY() == o.getY();
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
