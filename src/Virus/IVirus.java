package Virus;
import population.Person;
import population.Sick;

public interface IVirus {
   static final int num_of_virus =3;
   public double contagionProbability(Person person);
   public boolean tryToContagion(Person p1, Person p2);
   public boolean  tryToKill(Sick s);
}
