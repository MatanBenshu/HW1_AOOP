package IO;
import country.City;
import country.Kibbutz;
import country.Moshav;
import country.Settlement;
import location.Location;
import location.Point;
import location.Size;
import population.Healthy;
import population.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import country.Map;
public class SimulationFile {
    private static final String file = "C:\\Users\\מתן בן שושן\\IdeaProjects\\HW1_AOOP\\src\\IO\\input.txt";
    public SimulationFile() throws Exception {

    }
    private Settlement[] readFile(String path) throws Exception {

        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);



        ArrayList<Settlement> settlements= new ArrayList<Settlement>();//for size of settlements array
        int j=0; //to iterate on settlements

        String s1 = br.readLine();
        while (s1.length() != 0) {
            settlements.add(strToSettlement(s1));//by ref?
            j++;
            s1 = br.readLine();
        }
        br.close();
        fr.close();

        Settlement[] conv = new Settlement[settlements.size()];
        settlements.toArray(conv);
        return conv;

    }

    private void createHealthyArr(int size, Settlement settlement){
        for(int i=0;i<size;i++)
        {
            settlement.addPerson(createHPerson(settlement));
        }
    }

    private Healthy createHPerson(Settlement settle){
        int age= calcAge();
        Point loc=settle.randomLocation();
        return new Healthy(age,loc,settle);
    }

    private int calcAge(){
        double y= Math.random()*4;
        Random rand= new Random();
        double x;
        do {
            x = rand.nextGaussian();
        }while (x>=1||x<=-1);
        x=x*6+9;
        return (int)(5*x+y);
    }
    private Settlement strToSettlement(String str){
        Settlement place = null;

        int num_of_crit=7;
        str=str.replaceAll(" ", "");
        String[] line = str.split(";");
        int len=line.length;
        if(line.length>0 && line.length== num_of_crit) {
            String setName = line[1];
            Point p = new Point(Integer.parseInt(line[2]), Integer.parseInt(line[3]));
            Size s = new Size(Integer.parseInt(line[4]), Integer.parseInt(line[5]));
            ArrayList<Person> per = null;

            switch (line[0]) {
                case "City":
                    place = new City(setName, new Location(p, s));
                    break;
                case "Kibbutz":
                    place = new Kibbutz(setName, new Location(p, s));
                    break;
                case "Moshav":
                    place = new Moshav(setName, new Location(p, s));
                    break;
                default:
                    System.out.print("No such settlement! ");
                    System.exit(0);

            }

            createHealthyArr(Integer.parseInt(line[line.length - 1]), place);
            return place;
        }
        else {
            System.out.print("Error ,the file doesnt match! ");
            System.exit(0);
            return place;
        }

    }

    public Map loadMap() throws Exception {
        Map mp = new Map(readFile(file));
        return mp;
    }


}