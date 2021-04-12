package location;

import java.util.Objects;
public class Location {
    private Point position;//top left point Location coordinates
    private Size size;//width and height of location

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


    public boolean equals(Location o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        return this.getPosition().equals(o.getPosition())&& this.getSize().equals(o.getSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition(), getSize());
    }

    private double Area_size(){
       double s_location =  this.size.getHeight()* this.size.getWidth();
        return s_location;
    }

}


