package com.company;

import Virus.ChineseVariant;
import country.City;
import country.RamzorColor;
import country.Settlement;
import location.Location;
import location.Point;
import location.Size;
import population.Healthy;
import population.Person;
import simulation.Simulation;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Size s=new Size(8,5);
        Point p=new Point(1,1);
        Point p2=new Point(2,3);
        ChineseVariant C_VAR = new ChineseVariant();
        Location l=new Location(p,s);
        Size s_Y=new Size(30,40);
        Point p1_Y=new Point(10,20);
        Point p2_Y=new Point(11,14);
        Location Y=new Location(p,s);
        Simulation.Clock.nextTick();


        Settlement yinon = new City("yinon",Y);
        Settlement ashdod = new City("ashdod",l);
        Person matan = new Healthy(25,p,ashdod);
        Person golan = new Healthy(30,p2,ashdod);
        Person ela = new Healthy(27,p,ashdod);
        ashdod.addPerson(matan);
        System.out.println("error add person");

        ashdod.addPerson(golan);
        ashdod.addPerson(ela);

        System.out.println(ashdod.calculateRamzorGrade());

        matan.contagion(C_VAR);
        Simulation.Clock.nextTick();        Simulation.Clock.nextTick();

        System.out.println(ashdod.contagiousPercent());
        System.out.println(ashdod.calculateRamzorGrade());

        ela.contagion(C_VAR);

        System.out.println(ela);
        System.out.println(ashdod.contagiousPercent());
        System.out.println(ashdod.calculateRamzorGrade());
        ashdod.addPerson(new Healthy(22,p2,ashdod));
        System.out.println(ashdod.contagiousPercent());
        System.out.println(ashdod.calculateRamzorGrade());


    }
}
