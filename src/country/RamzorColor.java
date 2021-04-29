//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package country;

import java.awt.*;

public enum RamzorColor {
    GREEN("Green", 0.4), YELLOW("Yellow", 0.6), ORANGE("Orange", 0.8), RED("Red", 1);
    private final double value;
    private final String color;
    private final Color colored;
    private final double cross_prob;

    private RamzorColor(String color, double V) {
        this.value = V;
        this.color = color;
        this.colored= Color.decode(color); //create new color instance according to given string
    }
    public double getColorV() {
        return this.value;
    }

    public String getColor() {
        return color;
    }

    public RamzorColor valueToColor(double value) {
        if (value <= GREEN.value) return GREEN;
        else if (GREEN.value < value && value <= YELLOW.value) return YELLOW;
        else if (YELLOW.value < value && value <= ORANGE.value) return ORANGE;
        else return RED;

    }

    @Override
    public String toString() {
        return "RamzorColor{" +
                "value=" + value +
                ", color='" + color + '\'' +
                '}';
    }
}
