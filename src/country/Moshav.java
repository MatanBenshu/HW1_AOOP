package country;

import location.Location;
import population.Person;

import java.util.ArrayList;

public class Moshav extends Settlement{

    public Moshav(String name, Location location, ArrayList<Person> people) {
        super(name, location, people);
    }

   public RamzorColor calculateRamzorGrade(){

        return this.getRamzorcolor().valueToColor(0.3+3*Math.pow(Math.pow(1.2,this.getRamzorcolor().getColorV())*(this.contagiousPercent()-0.35),5));
   }

}
