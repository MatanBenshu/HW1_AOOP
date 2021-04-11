package population;

import Virus.IVirus;
import country.Settlement;
import location.Location;
import location.Point;

public abstract class Person {
    private int age;
    private Point location;
    private Settlement settlement;
    private double P_of_person;
    public Person(int age, Point location, Settlement settlement) {
        this.age = age;
        this.setLocation(location); ;
        this.setSettlement(settlement);
    }
    public Person(Person person){
       this.age = person.getAge();
       this.setLocation(person.getLocation());
       this.setSettlement(person.getSettlement();
    }
    public abstract double contagionProbability();

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Point getLocation() {
        return new Point(this.location);
    }

    public void setLocation(Point location) {

        this.location =new Point( location);
    }

    public Settlement getSettlement() {
        return settlement;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement =new Settlement( settlement);
    }

   public Person contagion(IVirus iVirus);







}



