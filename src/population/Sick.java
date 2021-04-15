//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package population;

import Virus.IVirus;
import country.Settlement;
import location.Point;

import java.util.Objects;

public class Sick extends Person {
    //immutable, represents sick person
 private long contagiousTime;
 private IVirus virus;

//    constructors
    public Sick(int age, Point location, Settlement settlement, long contagiousTime, IVirus virus) {
        super(age, location, settlement);
        this.contagiousTime = contagiousTime;
        this.virus = virus;
    }


//----------end of constrctor-----------

    @Override
    public String toString() {
        //print sick person representation
        return "Sick{" +
                "contagiousTime=" + contagiousTime +
                ", virus=" + virus +
                '}';
    }

    public IVirus getVirus() {
        //return person's COVID variant
        return virus;
    }

    public long getContagiousTime() {
        //return person's contagion time
        return contagiousTime;
    }
    public Person recover(){
        //return converting this person to convalescent person
        return new Convalescent(this.getAge(),this.getLocation(),this.getSettlement(),this.virus);
    }

    @Override
    public boolean equals(Object o) {
        //returns true if it is the same person, else false
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sick sick = (Sick) o;
        return contagiousTime == sick.contagiousTime && Objects.equals(virus, sick.virus);
    }

    @Override
    public int hashCode() {return Objects.hash(super.hashCode(), contagiousTime, virus); }

    public boolean tryToDie(){
        //returns true if the virus killed this sick person, else false
        return this.virus.tryToKill(this);
    }
    @Override
    public double contagionProbability() {
        //if try to contagion again - not possible, error message
        System.out.print("Already sick!");
        return 0;
    }

}
