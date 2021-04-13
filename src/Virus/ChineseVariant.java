package Virus;

import com.company.RandomV;
import population.Healthy;
import population.Person;
import population.Sick;
import java.lang.Math;
import java.time.Instant;


public class ChineseVariant implements IVirus {
    private final Person  person;

    public ChineseVariant(Person person) {
        this.person = person;
        }
    private double getDeathProb(int age) {

        if(age <=18)
            return 0.1/100;
        else if(18 < age && age<=55)
            return 5/100;
        else
            return 10/100;
    }
    private double getContProb(int age) {

        if(age <=18)
            return 20/100;
        else if(18 < age && age<=55)
            return 50/100;
        else
            return 70/100;
    }


    @Override
    public double contagionProbability(Person person) {
        return person.contagionProbability()*getContProb(person.getAge());

    }

    @Override
    public boolean tryToContagion(Person p1, Person p2) {
        if( !(p2 instanceof Sick))
        {
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
    public boolean tryToKill(Sick s) {
        double die= getDeathProb(s.getAge());
        double prob= Math.max(0,die-0.01*die*(Math.pow((s.getContagiousTime()-15),2)));
        if(RandomV.GetRand(0,1)<prob)
            return true;
        else
            return false;
    }
}


