package country;

import location.Location;
import population.Person;

import java.util.ArrayList;

public class City extends Settlement{
    public City(String name, Location location) {
        super(name, location );
    }

    public RamzorColor calculateRamzorGrade(){
       return  this.getRamzorcolor().valueToColor(0.2*Math.pow(4,1.25*this.contagiousPercent())) ;
    }
    @Override
    public String toString(){ //overriding the toString() method
        return "City : "+this.getName() +"Location : "+getLocation() + " RamzorColor :" +this.getRamzorcolor() ;
    }
}
