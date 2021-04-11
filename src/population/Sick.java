package population;

import Virus.IVirus;
import country.Settlement;
import location.Point;

public class Sick extends Person {
 private long contagiousTime;
 private IVirus virus;

    public Sick(int age, Point location, Settlement settlement, long contagiousTime, IVirus virus) {
        super(age, location, settlement);
        this.contagiousTime = contagiousTime;
        this.virus = virus;
    }

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

    public void setContagiousTime(long contagiousTime) {
        this.contagiousTime = contagiousTime;
    }

    public IVirus getVirus() {
        return virus;
    }

    public void setVirus(IVirus virus) {
        this.virus = virus;
    }

    public Sick(Person person, long contagiousTime, IVirus virus) {
        super(person);
        this.contagiousTime = contagiousTime;
        this.virus = virus;
    }

    @Override
    public double contagionProbability() {
        return 1;
    }
}
