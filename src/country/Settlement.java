package country;

import location.Location;
import location.Point;
import population.Person;
import population.Sick;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Settlement {
    private String name;
    private Location location;
    private ArrayList<Person> people;
    private final RamzorColor[] ramzorcolor = RamzorColor.values();
    //----------start of constructor---------
    public Settlement(String name, Location location,ArrayList <Person> people) {
        this.name =new String(name) ;
        this.location =new Location(location) ;
        this.people = Make_person_list(people);

    }
    public Settlement(Settlement settlement) {
        this.name = new String(settlement.getName());
        this.people = Make_person_list(settlement.getPeople());
        this.location = new Location(settlement.getLocation());
    }
    //----------end of constructor---------


    //----------start of public methods---------
    //----------start of getters and setters---------
    public String getName() {
        return new String(this.name);
    }

    public Location getLocation() {
        return new Location(this.location);
    }

    public ArrayList<Person> getPeople() {
        return Make_person_list(this.people);
    }

    public void setName(String name) {
        this.name = new String(name);
    }
    public void setLocation(Location location) {
        this.location =new Location(location);
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = Make_person_list(people);
    }

    public RamzorColor[] getRamzorcolor() {
        return ramzorcolor;
    }
    //----------end of getters and setters---------
    public RamzorColor  calculateRamzorGrade(){};

    public double contagiousPercent(){
        double num_of_sick = 0;
        int sattel_size =this.people.size();
        for (int i = 0; i <sattel_size; i++) {
            if (this.people.get(i) instanceof Sick)
                num_of_sick++;

        }

        return num_of_sick/sattel_size;
    }
    public Point randomLocation(){


    }
    //----------end of public methods---------


////----------start of private methods------------------
  private ArrayList<Person> Make_person_list(ArrayList <Person> per_arry){

        ArrayList<Person> new_arry_list = new ArrayList<Person>();
        for (int i=0;i<per_arry.size();i++){
            new_arry_list.add(per_arry.get(i));
        }
    return new_arry_list;
  }
}
