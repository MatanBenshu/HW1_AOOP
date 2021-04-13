package population;

import Virus.IVirus;
import country.Settlement;
import location.Location;
import location.Point;
import simulation.Simulation;

import java.util.Objects;

public abstract class Person {
    private int age;
    private Point location;
    private Settlement settlement;

    //----------start of constructor---------
    public Person(int age, Point location, Settlement settlement) {
        this.age = age;
        this.location=location; ;
        this.settlement =settlement;
    }
//    public Person(Person person){
//       this.age = person.getAge();
//       this.setLocation(person.getLocation());
//       this.setSettlement(person.getSettlement());
//    }
    //---------------end of constrctor--------------------------------------

    //------------ start of public method----------

    //--------------start of getters and setters-------------
    public abstract double contagionProbability();

    public int getAge() {
        return this.age;
    }

    public Point getLocation() {
        return this.location;
    }

    public Settlement getSettlement() {
        return this.settlement;
    }


    public void setSettlement(Settlement sat){this.settlement=settlement;}


    //------------end of getters and setters---------------------------

   public Person contagion(IVirus iVirus){
        Person p_S=new Sick(this.age,this.location,this.settlement,Simulation.Clock.now(),iVirus);
        settlement.updatePerson(this,p_S);

     return p_S;

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



