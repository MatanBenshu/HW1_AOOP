package Virus;
import population.Person;
import population.Sick;

public interface IVirus {

   public double contagionProbability(Person person);
   public boolean tryToContagion(Person sick_pers, Person pers);
   public boolean  tryToKill(Sick sick_pers);
}
