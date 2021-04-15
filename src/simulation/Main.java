//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package simulation;
import IO.SimulationFile;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.BritishVariant;
import Virus.SouthAfricanVariant;
import country.Map;
import country.Settlement;
import country.Map;
import population.Person;
import population.Sick;

import java.util.ArrayList;


public class Main {
    private static final double percent_of_sick=0.01;
    public static void main(String[] args) throws Exception {
        SimulationFile X = new SimulationFile();
        Map y = X.loadMap();
        for (int j = 0; j < 5; j++) {
                makeSimulation(y.getSettlements());

        }

    }

    private  static void makeSimulation(Settlement[] settlement){
        //** */
        int num_of_settlements=settlement.length;
        for (int k = 0; k <num_of_settlements ; k++) {

            ArrayList<Person> persons = settlement[k].getPeople();//saving people array
            int size_of_sick = sizeOfSick(settlement[k].getPeople(), percent_of_sick);
            ArrayList<Person> sick_arry=makeSickArry(settlement[k],size_of_sick);
            int pepole_on_sattel = settlement[k].getPeople().size();
            for (int i = 0;  i < size_of_sick; i++)
            {
                    Person sick_per=sick_arry.get(i);
                    int j = 0;//for try to contagion 6 time loop
                    while (j < 6) {
                        Person rand_person = randPerson(persons);
                        if(!(rand_person instanceof Sick))
                        {
                            if (sick_per.getVirus().tryToContagion(sick_per, rand_person)==true) {
                                Person s=rand_person.contagion(sick_per.getVirus());
                                persons.set(persons.indexOf(rand_person),s);

                            }
                            j++;
                        }
                    }
                }

            }
        }







    private static int sizeOfSick(ArrayList<Person> per_list, double precent) {
        return (int) (per_list.size() * precent);
    }

    private static ArrayList<Person> makeSickArry(Settlement settlement, int size_of_sick) {
                ArrayList<Person> per_arr = settlement.getPeople();
                ArrayList<Person> sick_arr = new ArrayList<Person>() ;
                IVirus rand_virus=rand_Virus();
                for (int i = 0; i < size_of_sick; i++) {
                    Person sick_person = per_arr.get(i).contagion(rand_virus);
                    per_arr.set(i, sick_person);
                    sick_arr.add(sick_person);
                }
        return sick_arr;
    }
    private static Person randPerson(ArrayList<Person> per_arry){
        int size= per_arry.size();
        while (true) {
            int i =RandomV.GetRand(size);

                if (!(per_arry.get(i) instanceof Sick)) {
                    return per_arry.get(i);

            }
        }
    }
    private static IVirus rand_Virus() {


        while (true) {
            int x = RandomV.GetRand(IVirus.num_of_virus);
            switch (x) {
                case 0:
                    return new ChineseVariant();
                case 1:
                    return new Virus.SouthAfricanVariant();
                case 2:
                    return new Virus.BritishVariant();

            }
        }

    }
}