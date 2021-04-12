package population;

import country.Settlement;
import location.Point;
import simulation.Simulation;

import java.time.Instant;

public class Vaccinated extends Person {
    private long vaccinationTime;

    public Vaccinated(int age, Point location, Settlement settlement, String status, long vaccinationTime) {
        super(age, location, settlement);
        this.vaccinationTime = vaccinationTime;

    }

    public Vaccinated(Person person, long vaccinationTime) {
        super(person);
        this.vaccinationTime = vaccinationTime;
    }

    @Override
    public double contagionProbability() {

        if(Simulation.Clock.now()<21){

        }

    }
}

