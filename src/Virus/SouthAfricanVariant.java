//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package Virus;

import simulation.RandomV;
import population.Person;
import population.Sick;
import java.lang.Math;

public class SouthAfricanVariant implements IVirus{
//immutable, represent the South African Variant of COVID

    public SouthAfricanVariant() {}

    private double getDeathProbability(int age){
        //returns death probability according to age
        if(age<=18)
            return 0.05;
        else
            return 0.08;
    }
    private double getContagionProbability(int age){
        //returns contagion probability according to age
        if(age<=18)
            return 0.6;
        else
            return 0.5;
    }


    @Override
    public double contagionProbability(Person person){//calculate the probability that s(sick) will die
        return person.contagionProbability()*getContagionProbability(person.getAge());
    }

    @Override
    public boolean tryToContagion(Person p1, Person p2) {
        if( !(p2 instanceof Sick)){
            //calculate the probability that p1(sick) will contagion p2

            double distance = p1.getLocation().distanceFrom(p2.getLocation());
            double prob = Math.min(1, 0.14 * Math.exp(2 - 0.25 * distance))*contagionProbability(p2);
            return RandomV.GetRand(0, 1) < prob;
        }
        return false;
    }


    @Override
    public boolean tryToKill(Sick s){//calculate the probability that s(sick) will die
        double die= getDeathProbability(s.getAge());
        double prob= Math.max(0,die-0.01*die*(Math.pow((s.getContagiousTime()-15),2)));
        return RandomV.GetRand(0, 1) < prob;
    }
    @Override
    public String toString(){ //overriding the toString() method
        return " SouthAfricanVariant" ;
    }
}