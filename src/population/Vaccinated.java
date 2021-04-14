package population;

import country.Settlement;
import location.Point;
import simulation.Simulation;

import java.time.Instant;

public class Vaccinated extends Person {
    private long vaccinationTime;

    public Vaccinated(int age, Point location, Settlement settlement,  long vaccinationTime) {
        super(age, location, settlement);
        this.vaccinationTime = vaccinationTime;

    }



    @Override
    public double contagionProbability() {
        long t=Simulation.Clock.now();
        if((t-this.vaccinationTime)<21){
            return Math.min(1,0.56+0.15*Math.sqrt(21- t));
        }
        return Math.max(0.05,1.05/(t-14));
    }
    @Override
    public String toString(){ //overriding the toString() method
        return " Vaccinated Age : "+this.getAge() +"Location : "+this.getLocation().toString() + " Settelment :" +this.getSettlement().toString() ;
    }
}

