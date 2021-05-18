//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package country;

import location.Location;
import population.*;

import java.util.ArrayList;

public class Moshav extends Settlement{

    public Moshav(String name, Location location,int maxres,ArrayList<Settlement> near_settle) {
        super(name, location,maxres,near_settle);
    }

   public RamzorColor calculateRamzorGrade(){
         this.ramzorcolor =this.ramzorcolor.valueToColor(0.3+3*Math.pow(Math.pow(1.2,this.ramzorcolor.getColorV())*(this.contagiousPercent()-0.35),5));

        return this.ramzorcolor;
   }

    @Override
    public String toString(){ //overriding the toString() method
        return "Moshav : "+this.getName() +"Location : "+getLocation() + " RamzorColor :" +this.ramzorcolor ;
    }
    @Override
    public String SettlementType(){
        return "Moshav";
    }

}
