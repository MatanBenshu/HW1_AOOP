//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package country;

import IO.LogFile;
import IO.SimulationFile;
import location.Size;
import simulation.RandomV;
import location.Location;
import location.Point;
import population.*;
import population.Healthy;
import population.Vaccinated;
import population.Convalescent;

import population.Sick;
import ui.MenuBar;
import ui.SimulationMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;

public class Settlement implements Runnable {
    private String name;
    private final Location location;
    private ArrayList<Person> people = new ArrayList<>();//all residents
    private ArrayList<Person> not_sick_people = new ArrayList<Person>();//only healthy
    protected RamzorColor ramzorcolor;
    private ArrayList<Sick> sick_people= new ArrayList<Sick>();//only sick
    private int max_residents;
    private int vaccine_num=0;
    private int dead=0;
    private ArrayList<Settlement> Related_settlements ;

    //----------start of constructor---------
    public Settlement(String name, Location location,int max_residents,ArrayList<Settlement> Rel_settle) {
        this.name = new String(name);
        this.location = location;
        this.ramzorcolor = RamzorColor.GREEN;
        this.max_residents=max_residents;
        this.Related_settlements=Rel_settle;
    }

//--------end of constructor---------


    //----------start of public methods---------
    //----------start of getters and setters---------
    public String getName() {
        return new String(this.name);
    }
    public String getColor(){return this.ramzorcolor.toString();}
    public int getVaccineNum(){return this.vaccine_num; }
    public Location getLocation() {
        return location;
    }
    public Person getPerson(int index) { return this.people.get(index); }
    public final ArrayList<Person> getPeople(){return this.people;}
    public final ArrayList<Person> get_not_sick_people() {return  this.not_sick_people;}
    public Sick getSickPerson(int index) { return sick_people.get(index); }//get sick in index
    public final ArrayList<Sick> getSickPeople(){return this.sick_people;}//get sick array
    public synchronized int getResidentsNum(){ return this.people.size(); }
    public  int getSickNum(){
        int size;
        synchronized (this){size=this.sick_people.size();}
        return size;
    }
    public int getHealthNum(){return this.not_sick_people.size();}
    public ArrayList<Settlement> getPassages(){return this.Related_settlements;}
    public String SettlementType(){return null;}
    public Point getPoint(){return location.getPosition();}
    public Size getSize(){return location.getSize();}
    public int getMaxResidents(){return this.max_residents;}

    public int getDeadNum() {
        return this.dead;
    }

    //----------end of getters and setters---------
    public RamzorColor calculateRamzorGrade() {
        return RamzorColor.GREEN;
    }

    public double contagiousPercent() {
        return (double)this.getSickNum() / this.getResidentsNum();
    }

    public Point randomLocation() {
        final Point left_sattel_p = this.location.getPosition();
        final int width = this.location.getSize().getWidth();
        final int height = this.location.getSize().getHeight();
        int B_x = left_sattel_p.getX() + width;
        int A_y = left_sattel_p.getY() - height;
        Point A = new Point(left_sattel_p.getX(), A_y);//The point that is at zero height in front of the left point of the settlement in relation to the height.
        Point B = new Point(B_x, left_sattel_p.getY());//The rightmost point at the same height as the leftmost point
        Point C = new Point(B_x, A_y);//
        int rand_X = (int) RandomV.GetRand(B_x, left_sattel_p.getX());
        int rand_y = (int) RandomV.GetRand(A_y, left_sattel_p.getY());
        return new Point(rand_X, rand_y);

    }

    public boolean addPerson(Person person) {

        if(this.people.contains(person)==false) {
            if (getResidentsNum() <max_residents){
                this.people.add(person);
                if (person instanceof Sick)
                    sick_people.add((Sick) person);
                else
                    not_sick_people.add( person);}
            return true;
        }
        return false;
    }
    public boolean Update_person_status(Person old_obj,Person new_obj )
    {
        if(this.people.contains(old_obj)){
            if (new_obj instanceof Sick){

                this.not_sick_people.remove(old_obj);
                this.sick_people.add((Sick) new_obj);
                this.people.set(this.people.indexOf(old_obj),new_obj);
            }
            else {
                if (old_obj instanceof Sick){

                    this.sick_people.remove(old_obj);
                    this.not_sick_people.add(new_obj);
                    this.people.set(this.people.indexOf(old_obj),new_obj);
                }
                else {
                    this.not_sick_people.set(this.people.indexOf(old_obj),new_obj);
                    this.people.set(this.people.indexOf(old_obj),new_obj);

                }
            }
            return true;

        }

        return false;
    }

   public boolean transferPerson(Person person, Settlement new_sattle) {
        //start change settlement persons
        if(this.people.contains(person)==false)
            return false;
        if(new_sattle.FullSettlement())
            return false;
        synchronized (Settlement.class) {
            if (transferProb(new_sattle) == true) {

                new_sattle.addPerson(person);
                if (person instanceof Sick)
                    this.sick_people.remove(person);
                else
                    this.not_sick_people.remove(person);
                this.people.remove(person);
                ////start change person settlement
                person.setSettlement(new_sattle);

            }
        }
        return true;
    }

    public boolean FullSettlement(){
        if (this.getResidentsNum()<this.getMaxResidents())
            return false;
        else
            return true;
    }

    public void addPassage(Settlement settlement){Related_settlements.add(settlement);}
    public void giveVaccines(){
        //give vaccine to healthy people and vaccine num--
        for(int i=0;i<this.not_sick_people.size()&&this.vaccine_num>0;i++){
            if(this.vaccine_num>0){
                if(this.not_sick_people.get(i) instanceof Healthy){
                    Healthy healthy=(Healthy) not_sick_people.get(i);
                    Vaccinated vac = healthy.vaccinate();
                    Update_person_status(healthy,vac);
                   this. vaccine_num--;
                   System.out.println("vaccine num in "+this.name+"="+this.vaccine_num);
                }
            }
        }
    }
    @Override
    public String toString() {
        return "Settlement{" +
                "name='" + name + '\'' +
                ", location=" + location +
                ", people=" + people +
                ", ramzorcolor=" + ramzorcolor +
                '}';
    }
    private boolean transferProb(Settlement new_settle){
        double prob=this.calculateRamzorGrade().getCross_prob()*new_settle.calculateRamzorGrade().getCross_prob();
        if(prob<RandomV.GetRand(0,1))
            return true;
        return false;
    }
    public void tryTokillSicks() throws IOException {
        ArrayList<Sick> dead_array=new ArrayList<>();
        for (int i = 0; i <sick_people.size() ; i++) {
            Sick s_person=this.sick_people.get(i);
            if(s_person.tryToDie()==true){

                dead_array.add(s_person);
                this.dead++;
            }
        }
        if(dead_array.size()!=0) {
            if(dead_array.size()>=this.OnePercentOfPop()&&!LogFile.WriterIsNull())
                LogFile.Write(this);
            this.sick_people.removeAll(dead_array);
            this.people.removeAll(dead_array);
        }

    }
    private int OnePercentOfPop(){
        return (int)(this.getResidentsNum()*(0.01));

    }
    public void setName(String aValue) {
        this.name=aValue;
    }

    public void addVaccine_num(int vaccine_num) {
        this.vaccine_num = this.vaccine_num+vaccine_num;
    }

    public int PopulationSize() {
        return this.people.size();
    }

    @Override
    public void run() {
        this.makeSick(this, sizeOfSick(this.getResidentsNum()));
        final double tryPass = 0.03;
        while (SimulationMenu.stop_is_on == false) {

            synchronized (Map.class) {
                while (SimulationMenu.play_flag == false) {
                    System.out.println("wait loop");
                    try {
                     Map.class.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

//----------------Make random healthy people sick----------------//

//----------------------Try to contagion-------------------------------//

            this.tryCon(this);
            System.out.println("Try to contagion ->" + this.getName());


//-----------------------After 25 days sick is convalescent---------//

            System.out.println("After 25 days sick is convalescent->" + this.getName());
            int sick_num;
            for (int p = 0; p < this.getSickNum(); p++) {
                Sick s = this.getSickPerson(p);
                if (s!=null&&s.DaysPastFromCont() >= 25) {//need to return sick not Person{
                    Convalescent c = (Convalescent) s.recover();
                    this.Update_person_status(s, c);
                    System.out.println("After 25 days sick is convalescent ,end if");
                }
                System.out.println("After 25 days sick is convalescent ,p=" + p);
            }

            //---------------------Try to pass---------------------//


                int numOfPasses = (int) (this.getResidentsNum() * tryPass);
                if (this.getPassages().size() != 0) {
                    for (int j = 0; j < numOfPasses; j++) {
                        Person p = this.randPerson(this.getPeople(), this.getPeople().size());
                        Settlement passTo = this.getPassages().get(RandomV.GetRand(this.getPassages().size()));

                            boolean passed = this.transferPerson(p, passTo);

                        System.out.println("ATry to pass,j=" + j);
                        //System.out.print("Person " + p + "transfer to: " + passTo.getName() + "status= " + passed);
                    }
                    System.out.println("ATry to pass,if");
                }
                System.out.println("ATry to pass->" + this.getName());


            //------------------Vaccine shot----------------------//
            this.giveVaccines();
            System.out.println("Vaccine shot->" + this.getName());

            try {
                this.tryTokillSicks();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("tryTokillSicks()->" + this.getName());
//-----------------update map-----------------------//


            //--------------------berrier----------------
            try {

                Map.getCyclicBarrier().await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }


//--------------------Thread sleep--------------//

        }
    }
    //----------end of public methods---------


    ////----------start of private methods------------------
    private void tryCon(Settlement settlement){
        //** */

        final int tryContagion = 3;
        int size_of_sick = this.getSickNum();
        int sizeof_not_sick= this.get_not_sick_people().size();
        //break
        if(sizeof_not_sick>0)
        {
            for (int i = 0; i < size_of_sick; i++)
            {
                Person sick_per = this.getSickPerson(i);
                int j =tryContagion ;
                while (j > 0&&sizeof_not_sick>0) {//for try to contagion const time loop
                    Person rand_person = randPerson(this.get_not_sick_people(),sizeof_not_sick);
                    if (!(rand_person instanceof Sick)) {
                        if (sick_per!=null&&sick_per.getVirus().tryToContagion(sick_per, rand_person)) {
                            Person s = rand_person.contagion();
                            this.Update_person_status(rand_person, s);
                        }
                        j--;
                        sizeof_not_sick= this.get_not_sick_people().size();
                    }
                }
            }

        }
    }




     private  static  int sizeOfSick(int ResidentsNum) {
         final double percent_of_sick = 0.01;
        return (int) (ResidentsNum * percent_of_sick );
    }

     private synchronized void makeSick(Settlement settlement, int size_of_sick) {

        for (int i = 0; i < size_of_sick;i++ ) {
            Person exists_per = randPerson( settlement.get_not_sick_people(),settlement.get_not_sick_people().size());
            settlement.Update_person_status(exists_per, exists_per.contagion());
        }

    }

    private static Person randPerson(ArrayList<Person> A_persons, int size){
        int i = RandomV.GetRand(size);
        return A_persons.get(i);


    }


//    }
}