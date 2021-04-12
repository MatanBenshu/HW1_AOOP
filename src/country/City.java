package country;

import location.Location;
import population.Person;

import java.util.ArrayList;

public class City extends Settlement{
    public City(String name, Location location, ArrayList<Person> people) {
        super(name, location, people);
    }

    public RamzorColor calculateRamzorGrade(){

       return  this.getRamzorcolor().valueToColor(0.2*Math.pow(4,1.25*this.contagiousPercent())) ;
    }

}
