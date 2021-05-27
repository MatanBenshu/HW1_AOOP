//Matan Ben-Shushsan 205639800
//Aviya David 209203991
package simulation;

import country.Settlement;
import population.Convalescent;
import population.Person;
import population.Sick;
import ui.RamzorMainWindow;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SimThread implements Runnable {
    private static CyclicBarrier cyclicBarrier;
    public static boolean stop_is_on = false;
    public static boolean play_flag = false;
    private static int thread_num = 0;
    private Settlement settlement;
    private static final double percent_of_sick = 0.01;
    private final int tryContagion = 3;
    private final double tryPass = 0.03;

    private final SimThread ref;

    public SimThread(Settlement value) {

        this.settlement = value;

        this.ref = this;
    }

    @Override
    public void run() {

        while (stop_is_on == false) {

            while (play_flag == false) {
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            this.notify();
//----------------Make random healthy people sick----------------//
            this.makeSick(settlement, sizeOfSick(settlement.getResidentsNum()));
//----------------------Try to contagion-------------------------------//

            this.tryCon(settlement);
            System.out.println("Try to contagion ->" + settlement.getName());


//-----------------------After 25 days sick is convalescent---------//
            Settlement currentSettlement = settlement;
            System.out.println("After 25 days sick is convalescent->" + settlement.getName());
            int sick_num;
            sick_num = currentSettlement.getSickNum();
            for (int p = 0; p < sick_num; p++) {
                Sick s = currentSettlement.getSickPerson(p);
                if (s.DaysPastFromCont() >= 25) {//need to return sick not Person{
                    Convalescent c = (Convalescent) s.recover();
                    currentSettlement.Update_person_status(s, c);
                    System.out.println("After 25 days sick is convalescent ,end if");
                }
                System.out.println("After 25 days sick is convalescent ,p=" + p);
            }

            //---------------------Try to pass---------------------//

            synchronized (this) {
                int numOfPasses = (int) (currentSettlement.getResidentsNum() * tryPass);
                if (currentSettlement.getPassages().size() != 0) {
                    for (int j = 0; j < numOfPasses; j++) {
                        Person p = this.randPerson(currentSettlement.getPeople(), currentSettlement.getPeople().size());
                        Settlement passTo = currentSettlement.getPassages().get(RandomV.GetRand(currentSettlement.getPassages().size()));

                        boolean passed = currentSettlement.transferPerson(p, passTo);

                        System.out.println("ATry to pass,j=" + j);
                        //System.out.print("Person " + p + "transfer to: " + passTo.getName() + "status= " + passed);
                    }
                    System.out.println("ATry to pass,if");
                }
                System.out.println("ATry to pass->" + settlement.getName());
            }

            //------------------Vaccine shot----------------------//
            settlement.giveVaccines();
            System.out.println("Vaccine shot->" + settlement.getName());


//-----------------update map-----------------------//


            //--------------------berrier----------------
            try {

                cyclicBarrier.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }


//--------------------Thread sleep--------------//

        }

    }




        public static void setCyclicBarrier(int thread_num) {

        cyclicBarrier = new CyclicBarrier(thread_num, new Runnable() {
            @Override
            public void run() {
                Simulation.Clock.nextTick();
                System.out.println(Simulation.Clock.now()+"barrier");
                RamzorMainWindow.UpdateMap();
                System.out.println("update map->");
                try {
                    Thread.sleep(1000* RamzorMainWindow.getSliderValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private void tryCon(Settlement settlement){
        //** */

            int size_of_sick = this.settlement.getSickNum();
            int sizeof_not_sick= this.settlement.get_not_sick_people().size();
            //break
            if(sizeof_not_sick>0)
            {
                for (int i = 0; i < size_of_sick; i++)
                {
                    Person sick_per = this.settlement.getSickPerson(i);
                    int j = this.tryContagion;
                    while (j > 0&&sizeof_not_sick>0) {//for try to contagion const time loop
                        Person rand_person = randPerson(this.settlement.get_not_sick_people(),sizeof_not_sick);
                        if (!(rand_person instanceof Sick)) {
                            if (sick_per.getVirus().tryToContagion(sick_per, rand_person)) {
                                Person s = rand_person.contagion();
                                this.settlement.Update_person_status(rand_person, s);
                            }
                            j--;
                            sizeof_not_sick= this.settlement.get_not_sick_people().size();
                        }
                    }
                }

            }
        }




    static public   int sizeOfSick(int ResidentsNum) {
        return (int) (ResidentsNum * percent_of_sick );
    }

    static public void makeSick(Settlement settlement, int size_of_sick) {

        for (int i = 0; i < size_of_sick;i++ ) {
            Person exists_per = randPerson( settlement.get_not_sick_people(),settlement.get_not_sick_people().size());
            settlement.Update_person_status(exists_per, exists_per.contagion());
        }
    }

    private static Person randPerson(ArrayList<Person> A_persons, int size){
        int i = RandomV.GetRand(size);
        return A_persons.get(i);


    }
    private static class NextTick implements Runnable{


        @Override
        public void run() {



        }

        }
    }








