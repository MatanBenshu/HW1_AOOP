//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package simulation;
import IO.SimulationFile;
import Virus.ChineseVariant;
import Virus.IVirus;
import country.Map;
import country.Settlement;
import population.Convalescent;
import population.Person;
import population.Sick;
import ui.MainWindow;
import ui.MenuBar;

import java.util.ArrayList;


public class Main {
    public static Map y;
    private static final double percent_of_sick=0.2;
    private static final int tryContagion=3;
    private static final double tryPass=0.03;
    public static void main(String[] args) throws Exception {
        MainWindow.SMainWindow.start();
//----------------Read from file-----------------------//
        SimulationFile X = new SimulationFile();
        y = X.loadMap();

//----------------Make random healthy people sick----------------//
        for (int i = 0; i < y.getSettlements().length; i++) {
            makeSick(y.getSettlements()[i],sizeOfSick(y.getSettlements()[i].getResidentsNum(),percent_of_sick));
        }
//----------------------Try to contagion-------------------------------//
        for (int j = 0; j < y.getSettlements().length; j++) {
                tryCon(y.getSettlements());
                System.out.println(y.getSettlements()[1]);
            System.out.println(y.getSettlements()[1].contagiousPercent());
        }
//-----------------------After 25 days sick is convalescent---------//
        for (int i = 0; i < y.getSettlements().length; i++) {
            Settlement currentSettlement=y.getSettlements()[i];
            for(int p=0;p<currentSettlement.getSickNum();p++)
            {
                Sick s= currentSettlement.getSickPerson(p);
                if(s.DaysPastFromCont()>=25) {//need to return sick not Person{
                    Convalescent c = (Convalescent) s.recover();
                    currentSettlement.Update_person_status(s,c);
                }
            }
        }

//---------------------Try to pass---------------------//
        for(int i=0;i<y.getSettlements().length;i++){
            Settlement currentSettlement=y.getSettlements()[i];
            int numOfPasses=(int)(currentSettlement.getResidentsNum()*tryPass);
            for(int j=0;j<numOfPasses;j++){
                Person p= randPerson(currentSettlement.getPeople());
                Settlement passTo= currentSettlement.getPassages().get(RandomV.GetRand(currentSettlement.getPassages().size()));
                boolean passed=currentSettlement.transferPerson(p,passTo);
                System.out.print("Person "+p+"transfer to: "+passTo.getName()+ "status= "+passed);
            }
        }
//------------------Vaccine shot----------------------//
        for(int i=0;i<y.getSettlements().length;i++){
            y.getSettlements()[i].giveVaccines();
        }
    }



    private  static void tryCon(Settlement[] settlement){
        //** */
        int num_of_settlements=settlement.length;
        for (int k = 0; k <num_of_settlements ; k++) {
            int size_of_sick = settlement[k].getSickNum();
            for (int i = 0;  i < size_of_sick; i++)
            {
                Person sick_per=settlement[k].getSickPerson(i);
                int j = tryContagion;
                while (j >0) {//for try to contagion const time loop
                    Person rand_person = randPerson(settlement[k].getPeople());
                    if(!(rand_person instanceof Sick))
                    {
                        if (sick_per.getVirus().tryToContagion(sick_per, rand_person)==true) {
                            Person s=rand_person.contagion();
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

        for (int i = 0; i < size_of_sick;i++ ) {
            Person exists_per = randPerson( settlement.get_not_sick_people());
            settlement.Update_person_status(exists_per, exists_per.contagion());
        }
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