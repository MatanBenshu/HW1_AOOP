package population;

import Virus.IVirus;
import country.Settlement;
import location.Point;

public class Sick extends Person {
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
        return "Sick{" +
                "contagiousTime=" + contagiousTime +
                ", virus=" + virus +
                '}';
    }

    public long getContagiousTime() {
        return contagiousTime;
    }
    public Person recover(){
       Person update = new Convalescent(this.getAge(),this.getLocation(),this.getSettlement(),this.virus);
        this.getSettlement().updatePerson(this,update);
        return update;
    }
    public boolean tryToDie(){
        return this.virus.tryToKill(this);
    }
    @Override
    public double contagionProbability() {
        return 0;
    }
}
