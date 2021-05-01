//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package simulation;
import IO.SimulationFile;
import Virus.ChineseVariant;
import Virus.IVirus;
import country.Map;
import country.Settlement;
import population.Person;
import population.Sick;

import java.util.ArrayList;


public class Main {
    private static final double percent_of_sick=0.01;
    public static void main(String[] args) throws Exception {
        SimulationFile X = new SimulationFile();
        Map y = X.loadMap();
        for (int i = 0; i < y.getSettlements().length; i++) {
            makeSick(y.getSettlements()[i],sizeOfSick(y.getSettlements()[i].getResidentsNum(),percent_of_sick));
        }
        for (int j = 0; j < 5; j++) {
                makeSimulation(y.getSettlements());
                System.out.println(y.getSettlements()[1]);
            System.out.println(y.getSettlements()[1].contagiousPercent());
        }

    }

    private  static void makeSimulation(Settlement[] settlement){
        //** */
        int num_of_settlements=settlement.length;
        for (int k = 0; k <num_of_settlements ; k++) {

//            ArrayList<Person> persons = settlement[k].getPerson();//saving people array
            int size_of_sick = settlement[k].getSickNum();
//            del-ArrayList<Person> sick_arry=makeSickArry(settlement[k],size_of_sick);
            int pepole_on_sattel = settlement[k].getResidentsNum();

            for (int i = 0;  i < size_of_sick; i++)
            {
                    Person sick_per=settlement[k].getSickPerson(i);
                    int j = 0;//for try to contagion 6 time loop
                    while (j < 6) {
                        Person rand_person = randPerson(settlement[k].getPeople());
                        if(!(rand_person instanceof Sick))
                        {
                            if (sick_per.getVirus().tryToContagion(sick_per, rand_person)==true) {
                                Person s=rand_person.contagion(sick_per.getVirus());
                                settlement[k].Update_person_status(rand_person,s);

                            }
                            j++;
                        }

                    }
                }

            }
        }







    private static int sizeOfSick(int ResidentsNum, double precent) {
        return (int) (ResidentsNum * precent);
    }

    private static void makeSick(Settlement settlement, int size_of_sick) {

                //del-ArrayList<Person> sick_arr = new ArrayList<Person>() ;
                IVirus rand_virus=rand_Virus();
                for (int i = 0; i < size_of_sick;i++ ) {
                    Person exists_per = randPerson( settlement.getH_people());
                    settlement.Update_person_status(exists_per, exists_per.contagion(rand_virus));

                    //del-sick_arr.add(sick_person);
                }

      //  return sick_arr;
    }
    private static Person randPerson( final  ArrayList<Person> A_persons){
        int size=A_persons.size();
        int i =RandomV.GetRand(size);
        return A_persons.get(i);

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