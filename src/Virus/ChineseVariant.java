//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package Virus;

import population.Person;
import simulation.RandomV;
import population.Sick;
import java.lang.Math;


public class ChineseVariant implements IVirus {
//immutable, represent the Chinese Variant of COVID

    public ChineseVariant() {

        }
    public int get_num_of_virus(){return num_of_virus;}

    private double getDeathProb(int age) {
        //returns death probability according to age
        if(age <=18)
            return 0.1/100;
        else if( age<=55)
            return 0.05;
        else
            return 0.1;
    }
    private double getContProb(int age) {
        //returns contagion probability according to age
        if(age <=18)
            return 0.2;
        else if(age<=55)
            return 0.5;
        else
            return 0.7;
    }


    @Override
    public double contagionProbability(Person person) {
        //returns contagion probability according to age
        return person.contagionProbability()*getContProb(person.getAge());

    }

    @Override
    public boolean tryToContagion(Person p1, Person p2) {
        //calculate the probability that p1(sick) will contagion p2
        if( !(p2 instanceof Sick))
        {
            double distance = p1.getLocation().distanceFrom(p2.getLocation());
            double prob = Math.min(1, 0.14 * Math.exp(2 - 0.25 * distance))*contagionProbability(p2);
            return RandomV.GetRand(0, 1) < prob;
        }
        return false;
    }

    @Override
    public boolean tryToKill(Sick s) {
        //calculate the probability that s(sick) will die

        double die= getDeathProb(s.getAge());
        double prob= Math.max(0,die-0.01*die*(Math.pow((s.DaysPastFromCont()-15),2)));
        return RandomV.GetRand(0, 1) < prob;
    }
    @Override
    public String toString(){ //overriding the toString() method
        return " ChineseVariant" ;
    }//overriding the toString() method
}


