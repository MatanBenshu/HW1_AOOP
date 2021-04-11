package country;

import location.Location;
import population.Person;

public class Settlement {
    private String name;
    private Location location;
    private Person[] people;
    private final RamzorColor[] ramzorcolor = RamzorColor.values();

    public Settlement(String name, Location location, Person[] people) {
        this.name =new String(name) ;
        this.location =new Location(location) ;
        //this.people =new Person[people.length];

    }
    public Settlement(Settlement settlement) {
        this.name = new String(settlement.name);
        this.people = settlement.Make_person_arry(settlement.people,settlement.people.length );
        this.location = new Location(settlement.location);
    }

//private methods/////
  private void Make_person_arry(Person[] per_arry,int size){
        this.people = new Person[size];
        for (int i=0;i<size;i++){



        }

  }
}
