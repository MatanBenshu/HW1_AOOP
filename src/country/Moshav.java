package country;

import location.Location;
import population.Person;

import java.util.ArrayList;

public class Moshav extends Settlement{

    public Moshav(String name, Location location) {
        super(name, location);
    }

   public RamzorColor calculateRamzorGrade(){
         this.ramzorcolor =this.ramzorcolor.valueToColor(0.3+3*Math.pow(Math.pow(1.2,this.ramzorcolor.getColorV())*(this.contagiousPercent()-0.35),5));

        return this.ramzorcolor;
   }

    @Override
    public String toString(){ //overriding the toString() method
        return "Moshav : "+this.getName() +"Location : "+getLocation() + " RamzorColor :" +this.getRamzorcolor() ;
    }

}
