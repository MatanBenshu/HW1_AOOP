//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package country;

import location.Location;
import population.*;

import java.util.ArrayList;

public class Kibbutz extends Settlement{
    public Kibbutz(String name, Location location,int maxres,ArrayList<Settlement> near_settle) {
        super(name, location,maxres,near_settle);
    }

    public RamzorColor calculateRamzorGrade(){
       this.ramzorcolor = this.ramzorcolor.valueToColor( 0.4+Math.pow(Math.pow(1.5,this.ramzorcolor.getColorV())*(this.contagiousPercent()-0.4),3));
        return this.ramzorcolor;
    }
    @Override
    public String toString(){ //overriding the toString() method
        return "Kibbutz : "+this.getName() +"Location : "+getLocation() + " RamzorColor :" +this.ramzorcolor ;
    }
    @Override
    public String SettlementType(){
        return "Kibbutz";
    }


}
