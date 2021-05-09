//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package Virus;
import population.Person;
import population.Sick;

public interface IVirus {
   int num_of_virus =3;
   double contagionProbability(Person person);//calculate the probability that s(sick) will die
   boolean tryToContagion(Person p1, Person p2);//calculate the probability that p1(sick) will contagion p2
   boolean  tryToKill(Sick s);//calculate the probability that s(sick) will die
   int get_num_of_virus();
}
