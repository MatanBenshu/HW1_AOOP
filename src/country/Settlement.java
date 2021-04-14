package country;

import simulation.RandomV;
import location.Location;
import location.Point;
import population.Person;
import population.Sick;

import java.util.ArrayList;

public class Settlement {
    private String name;
    private Location location;
    private ArrayList<Person> people = new ArrayList<Person>();
    private RamzorColor ramzorcolor;


    //----------start of constructor---------
    public Settlement(String name, Location location) {
        this.name = new String(name);
        this.location = location;
        this.ramzorcolor = RamzorColor.GREEN;
    }

//--------end of constructor---------


    //----------start of public methods---------
    //----------start of getters and setters---------
    public String getName() {
        return new String(this.name);
    }

    public Location getLocation() {
        return location;
    }

    protected RamzorColor getRamzorcolor() {
        return ramzorcolor;
    }

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

            if(this.people.contains(person)==false)
                this.people.add(person);

        return true;
    }

    public boolean transferPerson(Person person, Settlement new_sattle) {
        //start change settlement persons
        if(this.people.contains(person)==false)
            return false;

        new_sattle.addPerson(person);
        this.people.remove(person);
        ////start change person settlement
        person.setSettlement(new_sattle);

        return true;
    }
    public  boolean updatePerson(Person ex_person_obj,Person up_person_obj){

        if (this.people.contains(ex_person_obj) == false)
            return false;
        this.people.remove(ex_person_obj);
        this.people.add(up_person_obj);

        return true;

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
//----------end of public methods---------


    ////----------start of private methods------------------

//    }
}
