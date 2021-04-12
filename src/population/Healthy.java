package population;

import country.Settlement;
import location.Point;

public class Healthy extends Person{

    //start of constrctor////////
    public Healthy(int age, Point location, Settlement settlement) {
        super(age, location, settlement);

    }

    public Healthy(Person person) {
        super(person);
    }
    //end of constrctor/////////

    //public methods
    public Person vaccinate(){
        return new Vaccinated(this,System.nanoTime());
    }
    @Override
    public double contagionProbability() {
        return 1;
    }
    ///end of public method
}
