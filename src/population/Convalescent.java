//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package population;

import Virus.IVirus;
import country.Settlement;
import location.Point;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Convalescent that = (Convalescent) o;
        return Objects.equals(virus, that.virus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), virus);
    }

    @Override
    public double contagionProbability() {
        return 0.2;
    }
}

