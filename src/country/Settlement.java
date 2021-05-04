//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package country;

import simulation.RandomV;
import location.Location;
import location.Point;
import population.Person;
import population.Healthy;
import population.Vaccinated;
import population.Convalescent;

import population.Sick;
import simulation.Simulation;

import java.awt.*;
import java.util.ArrayList;

public class Settlement {
    private ArrayList<Settlement> Related_settlements ;
    private String name;
    private Location location;
    private ArrayList<Person> people = new ArrayList<Person>();//all residents
    private ArrayList<Person> not_sick_people = new ArrayList<Person>();//only healthy
    protected RamzorColor ramzorcolor;
    private ArrayList<Sick> sick_people= new ArrayList<Sick>();//only sick
    private int max_residents;
    private int vaccine_num=0;

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
    public final ArrayList<Sick> getSick_people(){return this.sick_people;}//get sick array
    public int getResidentsNum(){ return this.people.size(); }
    public  int getSickNum(){return this.sick_people.size();}
    public int getHealthNum(){return this.not_sick_people.size();}
    public ArrayList<Settlement> getPassages(){return this.Related_settlements;}

    //----------end of getters and setters---------
    public RamzorColor calculateRamzorGrade() {
        return RamzorColor.GREEN;
    }

    public double contagiousPercent() {
        double num_of_sick = 0;
        double sattel_size =(double) this.people.size();
        for (int i = 0; i < sattel_size; i++) {
            if (this.people.get(i) instanceof Sick)
                num_of_sick++;

        }

        return num_of_sick / sattel_size;
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
                this.people.set(this.people.indexOf(old_obj),new_obj);
                this.not_sick_people.remove(old_obj);
                this.sick_people.add((Sick) new_obj);
            }
            else {
                if (old_obj instanceof Sick){
                    this.people.set(this.people.indexOf(old_obj),new_obj);
                    this.sick_people.remove(old_obj);
                    this.not_sick_people.add(new_obj);
                }
                else {
                    this.people.set(this.people.indexOf(old_obj),new_obj);
                    this.not_sick_people.set(this.people.indexOf(old_obj),new_obj);
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
        if (transferProb(new_sattle)==true) {
            new_sattle.addPerson(person);
            if( person instanceof Sick)
                this.sick_people.remove(person);
            else
                this.not_sick_people.remove(person);
            this.people.remove(person);
            ////start change person settlement
            person.setSettlement(new_sattle);
        }
        return true;
    }

    public boolean FullSettlement(){
        if (this.people.size()<this.max_residents)
            return false;
        else
            return true;
    }

    public void addPassage(Settlement settlement){Related_settlements.add(settlement);}
    public void giveVaccines(){
        //give vaccine to healthy people and vaccine num--
        for(int i=0;i<not_sick_people.size();i++){
            if(vaccine_num>0){
                if(not_sick_people.get(i) instanceof Healthy){
                    Healthy healthy=(Healthy) not_sick_people.get(i);
                    Vaccinated vac=new Vaccinated(healthy.getAge(),healthy.getLocation(),healthy.getSettlement(), Simulation.Clock.now());
                    Update_person_status(healthy,vac);
                    vaccine_num--;
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
//----------end of public methods---------


    ////----------start of private methods------------------

//    }
}