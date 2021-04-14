package simulation;

import IO.SimulationFile;
import Virus.ChineseVariant;
import country.City;
import country.Settlement;
import country.Map;
import location.Location;
import location.Point;
import location.Size;
import population.Healthy;
import population.Person;
import simulation.Simulation;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

        SimulationFile X = new SimulationFile();
        Map y =X.loadMap();

    }
    private void doSickPepole(Settlement[] settlements){
        int size = settlements.length;
        for (int i = 0; i < size; i++) {


        }


    }
    private double precentOfSick(ArrayList<Person> per_list,double precent ){


    }
}