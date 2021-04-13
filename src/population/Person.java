package population;

import Virus.IVirus;
import country.Settlement;
import location.Location;
import location.Point;

import java.util.Objects;

public abstract class Person {
    private int age;
    private Point location;
    private Settlement settlement;

    //----------start of constructor---------
    public Person(int age, Point location, Settlement settlement) {
        this.age = age;
        this.setLocation(location); ;
        this.settlement=settlement;
    }
    public Person(Person person){
       this.age = person.getAge();
       this.setLocation(person.getLocation());
       this.settlement = person.getSettlement();
    }
    //---------------end of constrctor--------------------------------------

    //------------ start of public method----------

    //--------------start of getters and setters-------------
    public abstract double contagionProbability();

    public int getAge() {
        return this.age;
    }

    public Point getLocation() {
        return new Point(this.location);
    }

    public Settlement getSettlement() {
        return settlement;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setLocation(Point location) {

        this.location =new Point( location);
    }

    public void setSettlement(Settlement settlement) {
        this.settlement =settlement;
    }

    //------------end of getters and setters---------------------------

   public Person contagion(IVirus iVirus){
        Person sick_p = new Sick(this,12,iVirus);
        this.settlement.updatePerson(sick_p);
     return sick_p;

    }


    public boolean equals(Person o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        return this.getAge() == o.getAge() && this.location.equals(o.getLocation()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAge(), getLocation(), getSettlement());
    }
    // --------------end of public method--------------
}



