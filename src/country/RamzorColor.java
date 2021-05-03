//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package country;

import java.awt.*;

public enum RamzorColor {
    GREEN("Green",Color.GREEN, 0.4,1), YELLOW("Yellow",Color.YELLOW, 0.6,0.8), ORANGE("Orange",Color.orange, 0.8,0.6), RED("Red",Color.red, 1,0.4);
    private final double value;
    private final String color_name;
    private final Color colored;
    private final double cross_prob;

    private RamzorColor(String color_name,Color color, double V, final double cross) {
        this.value = V;
        this.color_name = color_name;
        this.colored= color; //create new color instance according to given string
        this.cross_prob=cross;
    }
    public double getColorV() {
        return this.value;
    }

    public String getColorName() {
        return color_name;
    }

    public Color getColored() {
        return colored;
    }

    public RamzorColor valueToColor(double value) {
        if (value <= GREEN.value) return GREEN;
        else if (GREEN.value < value && value <= YELLOW.value) return YELLOW;
        else if (YELLOW.value < value && value <= ORANGE.value) return ORANGE;
        else return RED;

    }
    public double getCross_prob(){return this.cross_prob;}
    @Override
    public String toString() {
        return "RamzorColor{" +
                "value=" + value +
                ", color='" + color_name + '\'' +
                '}';
    }
}