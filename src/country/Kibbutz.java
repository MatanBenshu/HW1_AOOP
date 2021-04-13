package country;

import location.Location;
import population.Person;

import java.util.ArrayList;

public class Kibbutz extends Settlement{
    public Kibbutz(String name, Location location) {
        super(name, location);
    }

    public RamzorColor calculateRamzorGrade(){

        return this.getRamzorcolor().valueToColor( 0.4+Math.pow(Math.pow(1.5,this.getRamzorcolor().getColorV())*(this.contagiousPercent()-0.4),3));
    }



}
