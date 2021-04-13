package com.company;

import Virus.ChineseVariant;
import Virus.IVirus;
import country.City;
import country.RamzorColor;
import country.Settlement;
import location.Location;
import location.Point;
import location.Size;
import population.Healthy;
import population.Person;
import population.Sick;
import simulation.Simulation;

import java.time.Clock;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Size s=new Size(8,5);
        Point p=new Point(1,1);
        Point p2=new Point(2,3);
        ChineseVariant C_VAR = new ChineseVariant();
        Location l=new Location(p,s);
        Simulation.Clock.nextTick();
        ArrayList<Person> per_a=new ArrayList<Person>();

        Settlement ashdod = new City("ashdod",l,per_a);
        Person matan = new Healthy(25,p,ashdod);
        Person golan = new Healthy(30,p2,ashdod);
        Person ela = new Healthy(27,p,ashdod);
        if(ashdod.addPerson(matan)==false)System.out.println("error add person");
        ashdod.addPerson(golan);
        ashdod.addPerson(ela);
        System.out.println(ashdod.getRamzorcolor());
         matan = matan.contagion(C_VAR);
        Simulation.Clock.nextTick();        Simulation.Clock.nextTick();
        System.out.println(ashdod.contagiousPercent());
        System.out.println(ashdod.getRamzorcolor());
        ela.contagion(C_VAR);
        System.out.println(ashdod.getPerson(ela));
        System.out.println(ela);
        System.out.println(ashdod.contagiousPercent());
        System.out.println(ashdod.getRamzorcolor());


    }
}
