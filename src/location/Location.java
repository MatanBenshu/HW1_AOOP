package location;

import java.util.Objects;
public class Location {
    private Point position;//
    private Size size;

    public Location(Point position,Size size) {
        this.position = new Point(position);
        this.size = new Size(size) ;

    }
    public Location(Location obj) {
        this.size = obj.getSize();
        this.position = obj.getPosition();

    }
    /*getters*/
    public Point getPosition() {
        Point dupli_pos = new Point(position);
        return dupli_pos;
    }
    public Size getSize() {
        Size dupli_size = new Size(this.size);
        return dupli_size;
    }
    /*setters*/
    public void setPosition(Point position) {

        this.position = new Point(position);
    }

    public void setSize(Size size) {

        this.size = new Size(size);
    }


}


