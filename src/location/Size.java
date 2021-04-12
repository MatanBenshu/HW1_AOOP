package location;

import java.util.Objects;

public class Size {
    private  int width;
    private int height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public Size(Size obj) {
        this.width = obj.getWidth();
        this.height = obj.getHeight();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public boolean equals(Size o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        return this.getWidth() == o.getWidth() && this.getHeight() == o.getHeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWidth(), getHeight());
    }

    @Override
    public String toString() {
        return "Size{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
