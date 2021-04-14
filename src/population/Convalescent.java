package population;

import Virus.IVirus;
import country.Settlement;
import location.Point;

public class Convalescent extends Person{

    private IVirus virus;

    public Convalescent(int age, Point location, Settlement settlement, IVirus iVirus) {
        super(age, location, settlement);
        this.virus = iVirus;
    }
    @Override
    public String toString(){ //overriding the toString() method
        return " Convalescent Age : "+this.getAge() +"Location : "+this.getLocation().toString() + " Settelment :" +this.getSettlement().toString() ;
    }



    @Override
    public double contagionProbability() {
        return 0.2;
    }
}

