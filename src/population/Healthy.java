//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package population;

import country.Settlement;
import location.Point;
import simulation.Simulation;

public class Healthy extends Person{

    //start of constrctor////////
    public Healthy(int age, Point location, Settlement settlement) {
        super(age, location, settlement);

    }



    //end of constrctor/////////

    //public methods
    public Vaccinated vaccinate(){
        Vaccinated vac_P = new Vaccinated(this.getAge(),this.getLocation(),this.getSettlement(), Simulation.Clock.now());
        return vac_P;
    }

    @Override
    public double contagionProbability() {
        return 1;
    }
    @Override
    public String toString(){ //overriding the toString() method
        return " Healthy Age : "+this.getAge() +"Location : "+this.getLocation().toString() + " Settelment :" +this.getSettlement().toString() ;
    }
    ///end of public method
}
