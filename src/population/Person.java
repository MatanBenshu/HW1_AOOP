//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package population;

import Virus.IVirus;
import country.Settlement;
import location.Location;
import location.Point;
import simulation.Simulation;

import java.util.Objects;

public abstract class Person {
    private int age;
    private Point location;
    private Settlement settlement;

    //----------start of constructor---------
    public Person(int age, Point location, Settlement settlement) {
        this.age = age;
        this.location=location; ;
        this.settlement =settlement;
    }

    //---------------end of constrctor--------------------------------------

    //------------ start of public method----------

    //--------------start of getters and setters-------------
    public int getAge() {
        return this.age;
    }

    public Point getLocation() {
        return this.location;
    }

    public Settlement getSettlement() {
        return this.settlement;
    }

    public IVirus getVirus(){return null;}
    public void setSettlement(Settlement sat){this.settlement=settlement;}

    //------------end of getters and setters---------------------------

    public abstract double contagionProbability();

   public Person contagion(IVirus iVirus) {
           Person p_S = new Sick(this.age, this.location, this.settlement, Simulation.Clock.now(), iVirus);
           return p_S;
   }


    public boolean equals(Person p) {
        if (this == p) return true;
        if (p == null || this.getClass() != p.getClass()) return false;
        return this.getAge() == p.getAge() && this.location.equals(p.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAge(), getLocation(), getSettlement());
    }
    // --------------end of public method--------------
}



