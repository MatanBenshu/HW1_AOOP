package Virus;
import population.Person;
import population.Sick;

public interface IVirus {

   public double contagionProbability(Person person);
   public boolean tryToContagion(Person p1, Person p2);
   public boolean  tryToKill(Sick s);
}
