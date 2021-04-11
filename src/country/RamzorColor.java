package country;

public enum RamzorColor {
    GREEN(0.4),YELLOW(0.6),ORANGE(0.8),RED(1);
    private final double value;

   private RamzorColor(double V){
       this.value =V;
   }

    public double getVcolor() {
        return this.value;
    }

}
