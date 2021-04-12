package Virus;

import population.Healthy;
import population.Person;
import population.Sick;
import java.lang.Math;
import java.time.Instant;

public class BritishVariant implements IVirus{
    private final Person  person;

    public BritishVariant(Person person) {
        this.person = person;
    }
    private double getDeathProbability(int age){
        if(age<=18)
            return 1/100;
        else
            return 10/100;
    }

    private double getRand(double min, double max){
        return ((Math.random() * (max - min)) + min);
    }

    @Override
    public double contagionProbability(Person person){
        return person.contagionProbability()*0.7;
    }

    @Override
    public boolean tryToContagion(Person p1, Person p2) {
        if( p2 instanceof Healthy)
         {
            double distance = p1.getLocation().distanceFrom(p2.getLocation());
            double prob = Math.min(1, 0.14 * Math.exp(2 - 0.25 * distance));
            if (getRand(0, 1) < prob)
                return true;
            else
                return false;

        }
        return false;
    }

    @Override
    public boolean tryToKill(Sick s){
        double die= getDeathProbability(s.getAge());
        double prob= Math.max(0,die-0.01*die*(Math.pow((s.getContagiousTime()-15),2);
        if(getRand(0,1)<prob)
            return true;
        else
            return false;
    }
}
