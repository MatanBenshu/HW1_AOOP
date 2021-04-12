package country;

import location.Location;
import population.Person;

import java.util.ArrayList;

public class Kibbutz extends Settlement{
    public Kibbutz(String name, Location location, ArrayList<Person> people) {
        super(name, location, people);
    }

    public RamzorColor calculateRamzorGrade(){

        return this.getRamzorcolor().valueToColor( 0.4+Math.pow(Math.pow(1.5,this.getRamzorcolor().getColorV())*(this.contagiousPercent()-0.4),3));
    }



}
