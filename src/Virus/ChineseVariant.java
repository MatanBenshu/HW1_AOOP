package Virus;

import population.Person;
import population.Sick;

import java.time.Instant;

public class ChineseVariant implements IVirus {
    private final Person  person;

    public ChineseVariant(Person person) {
        this.person = person;
        }
    private double getP(int age) {

        if(age <=17)
            return 0.1/100;
        else if(18 <= age &&age<=55)
            return 5/100;
        else
            return 70/100;
    }
    @Override
    public double contagionProbability(Person person) {
        return person.contagionProbability()*getP(person.getAge());

    }

    @Override
    public boolean tryToContagion(Person sick_pers, Person pers) {

    }

    @Override
    public boolean tryToKill(Sick sick_pers) {
        return false;
    }
}

}
