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
    public Person vaccinate(){
        Person update = new Vaccinated(this.getAge(),this.getLocation(),this.getSettlement(), Simulation.Clock.now());
        this.getSettlement().updatePerson(this,update);
        return update;
    }
    @Override
    public double contagionProbability() {
        return 1;
    }
    ///end of public method
}
