package country;

public enum RamzorColor {
    GREEN(0.4),YELLOW(0.6),ORANGE(0.8),RED(1);
    private final double value;

   private RamzorColor(double V){
       this.value =V;
   }

    public double getColorV() {
        return this.value;
    }
    public RamzorColor valueToColor(double value){
       if (value <=GREEN.value)return GREEN;
       else if (GREEN.value<value&& value <= YELLOW.value) return YELLOW;
       else if (YELLOW.value<value&& value <= ORANGE.value) return ORANGE;
       else return RED;

    }

}
