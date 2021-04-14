package Virus;

import com.company.RandomV;
import population.Healthy;
import population.Person;
import population.Sick;
import java.lang.Math;
import java.time.Instant;
import java.time.temporal.ValueRange;

public class SouthAfricanVariant implements IVirus{


    public SouthAfricanVariant() {}

    private double getDeathProbability(int age){
        if(age<=18)
            return 5/100;
        else
            return 8/100;
    }
    private double getContagionProbability(int age){
        if(age<=18)
            return 60/100;
        else
            return 50/100;
    }


    @Override
    public double contagionProbability(Person person){
        return person.contagionProbability()*getContagionProbability(person.getAge());
    }

    @Override
    public boolean tryToContagion(Person p1, Person p2) {
        if( !(p2 instanceof Sick)){

            double distance = p1.getLocation().distanceFrom(p2.getLocation());
            double prob = Math.min(1, 0.14 * Math.exp(2 - 0.25 * distance))*contagionProbability(p2);
            if (RandomV.GetRand(0, 1) < prob)
                return true;
            else
                return false;
        }
        return false;
    }

    @Override
    public boolean tryToKill(Sick s){
        double die= getDeathProbability(s.getAge());
        double prob= Math.max(0,die-0.01*die*(Math.pow((s.getContagiousTime()-15),2)));
        if(RandomV.GetRand(0,1)<prob)
            return true;
        else
            return false;
    }
    @Override
    public String toString(){ //overriding the toString() method
        return " SouthAfricanVariant" ;
    }
}

