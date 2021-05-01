//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package country;

import location.Location;
import population.Person;

import java.util.ArrayList;

public class City extends Settlement{

    //------------Constructor---------//
    public City(String name, Location location,int maxres,ArrayList<Settlement> near_settle) {
        super(name, location,maxres,near_settle );
    }

    public RamzorColor calculateRamzorGrade(){
        // calculate to
        this.ramzorcolor = this.ramzorcolor.valueToColor(0.2*Math.pow(4,1.25*this.contagiousPercent())) ;
       return  this.ramzorcolor;
    }

    @Override
    public String toString(){ //overriding the toString() method
        return "City : "+this.getName() +"Location : "+getLocation() + " RamzorColor :" +this.ramzorcolor ;
    }
}
