package country;

import location.Location;
import population.Person;

import java.util.ArrayList;

public class Kibbutz extends Settlement{
    public Kibbutz(String name, Location location) {
        super(name, location);
    }

    public RamzorColor calculateRamzorGrade(){
       this.ramzorcolor = this.ramzorcolor.valueToColor( 0.4+Math.pow(Math.pow(1.5,this.ramzorcolor.getColorV())*(this.contagiousPercent()-0.4),3));
        return this.ramzorcolor;
    }
    @Override
    public String toString(){ //overriding the toString() method
        return "Kibbutz : "+this.getName() +"Location : "+getLocation() + " RamzorColor :" +this.ramzorcolor ;
    }


}
