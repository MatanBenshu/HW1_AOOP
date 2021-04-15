package location;

import java.util.Objects;
public class Location {
    private final Point position;//top left point Location coordinates
    private final Size size;//width and height of location

    public Location(Point position,Size size) {
        this.position = position;
        this.size = size;

    }

    /*getters*/
    public Point getPosition() {

        return this.position;
    }
    public Size getSize() {

        return this.size;
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

    @Override
    public String toString() {
        return "Location{" +
                "position=" + position +
                ", size=" + size +
                '}';
    }
}


