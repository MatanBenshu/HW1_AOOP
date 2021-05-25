//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package simulation;
import country.Map;
import country.Settlement;
import population.*;
import ui.RamzorMainWindow;

import java.time.Clock;
import java.util.ArrayList;


public class Main {
    public static boolean stop_is_on=false;
    public static boolean play_sim =false;
    private static final double percent_of_sick=0.01;
    private static final int tryContagion=3;
    private static final double tryPass=0.03;
    private static  RamzorMainWindow window;
    public static void main(String[] args) throws Exception {
     window= new RamzorMainWindow();


    }
    public static void StartSim() throws InterruptedException {
        ThreadArry threadArry=ThreadArry.getInstance();
        threadArry.initialization();
       threadArry.makeSimThreads(window);
       threadArry.startThreads();
        System.out.println("here");//never performed;


    }

private static class MainSim implements Runnable{


    @Override
    public void run() {


        //----------------Read from file-----------------------//
        Map y = window.getMapFile();


        while (play_sim == true) {
            for (int i = 0; i < y.getSettlements().length; i++) {
                makeSick(y.getSettlements()[i], sizeOfSick(y.getSettlements()[i].getResidentsNum()));
            }

//----------------Make random healthy people sick----------------//
            int settlements_size=y.getSettlements().length;
//----------------------Try to contagion-------------------------------//
            for (int j = 0; j < settlements_size; j++) {
                tryCon(y.getSettlements());
                System.out.println("Try to contagion ,j="+j);
            }
//-----------------------After 25 days sick is convalescent---------//
            for (int i = 0; i < settlements_size; i++) {
                Settlement currentSettlement = y.getSettlements()[i];
                System.out.println("After 25 days sick is convalescent ,i="+i);
                for (int p = 0; p < currentSettlement.getSickNum(); p++) {
                    Sick s = currentSettlement.getSickPerson(p);
                    if (s.DaysPastFromCont() >= 25) {//need to return sick not Person{
                        Convalescent c = (Convalescent) s.recover();
                        currentSettlement.Update_person_status(s, c);
                        System.out.println("After 25 days sick is convalescent ,end if");
                    }
                    System.out.println("After 25 days sick is convalescent ,p="+p);
                }
            }

//---------------------Try to pass---------------------//
            for (int i = 0; i < settlements_size; i++) {
                Settlement currentSettlement = y.getSettlements()[i];
                int numOfPasses = (int) (currentSettlement.getResidentsNum() * tryPass);
                if (currentSettlement.getPassages().size() != 0) {
                    for (int j = 0; j < numOfPasses; j++) {
                        Person p = randPerson(currentSettlement.getPeople(),currentSettlement.getPeople().size());
                        Settlement passTo = currentSettlement.getPassages().get(RandomV.GetRand(currentSettlement.getPassages().size()));
                        boolean passed = currentSettlement.transferPerson(p, passTo);
                        System.out.println("ATry to pass,j="+j);
                        //System.out.print("Person " + p + "transfer to: " + passTo.getName() + "status= " + passed);
                    }
                    System.out.println("ATry to pass,if");
                }
                System.out.println("ATry to pass,i="+i);
            }
//------------------Vaccine shot----------------------//
            for (int i = 0; i < settlements_size; i++){
                y.getSettlements()[i].giveVaccines();
                System.out.println("Vaccine shot,i="+i);
            }

//-----------------update map-----------------------//
            for (int i = 0; i < settlements_size; i++) {
                window.UpdateMap(y.getSettlements()[i]);
                System.out.println("update map,i="+i);
            }
            Simulation.Clock.nextTick();
            System.out.println(Simulation.Clock.now());
//--------------------Thread sleep--------------//
            try {
                Thread.sleep(1000*window.getSliderValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }



        System.out.println("sim is stop");
    }

    }




    public   static void tryCon(Settlement[] settlement){
        //** */
        for (Settlement value : settlement) {
            int size_of_sick = value.getSickNum();
            int sizeof_not_sick=value.get_not_sick_people().size();
            //break
            if(sizeof_not_sick>0)
            {
                for (int i = 0; i < size_of_sick; i++)
                {
                    Person sick_per = value.getSickPerson(i);
                    int j = tryContagion;
                    while (j > 0&&sizeof_not_sick>0) {//for try to contagion const time loop
                        Person rand_person = randPerson(value.get_not_sick_people(),sizeof_not_sick);
                        if (!(rand_person instanceof Sick)) {
                            if (sick_per.getVirus().tryToContagion(sick_per, rand_person)) {
                                Person s = rand_person.contagion();
                                value.Update_person_status(rand_person, s);
                            }
                            j--;
                            sizeof_not_sick=value.get_not_sick_people().size();
                        }
                    }
                }

            }
        }
        }

    private static int sizeOfSick(int ResidentsNum) {
        return (int) (ResidentsNum * Main.percent_of_sick);
    }

    private static void makeSick(Settlement settlement, int size_of_sick) {

        for (int i = 0; i < size_of_sick;i++ ) {
            Person exists_per = randPerson( settlement.get_not_sick_people(),settlement.get_not_sick_people().size());
            settlement.Update_person_status(exists_per, exists_per.contagion());
        }
    }

    private static Person randPerson(  ArrayList<Person> A_persons,int size){
        int i = RandomV.GetRand(size);
        return A_persons.get(i);


    }
}
