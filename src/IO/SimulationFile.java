//Matan Ben-Shushsan 205639800
//Aviya David 209203991

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
    //immutable, for converting file to map
    public static  String file = null;
    public static boolean simulation_run=false;
    private final double MAX_RESIDENDS=1.3;
    public SimulationFile() throws Exception {
    }
    private Settlement[] readFile(String path) throws Exception {

        //reading file and creating map instance
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);

        ArrayList<Settlement> settlements= new ArrayList<Settlement>();//for size of settlements array
        ArrayList<String> hashtags= new ArrayList<String>();
        String s1 = br.readLine();
        while (s1.length() != 0) {
            if (s1.charAt(0)==('#')){
                hashtags.add(s1);
                s1 = br.readLine();
                continue;
            }
            else {
                settlements.add(strToSettlement(s1));
                s1 = br.readLine();
            }
        }
        br.close();
        fr.close();

        Settlement[] conv = new Settlement[settlements.size()];
        settlements.toArray(conv);

        return conv;

    }

    private void createHealthyArr(int size, Settlement settlement){
        //returns healthy persons array according to size and settlement
        for(int i=0;i<size;i++)
        {
            settlement.addPerson(createHPerson(settlement));
        }
    }

    private Healthy createHPerson(Settlement settle){
        //returns healthy persons array according to settlement
        int age= calcAge();
        Point loc=settle.randomLocation();
        return new Healthy(age,loc,settle);
    }

    private int calcAge(){
        //returns random age
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
        //converting string to settlement
        Settlement place = null;

        int num_of_crit=7;
        str=str.replaceAll(" ", "");
        String[] line = str.split(";");
        if(line.length>0 && line.length== num_of_crit) {
            String setName = line[1];
            Point p = new Point(Integer.parseInt(line[2]), Integer.parseInt(line[3]));
            Size s = new Size(Integer.parseInt(line[4]), Integer.parseInt(line[5]));
            int maxRes= (int)(Double.parseDouble(line[6])*MAX_RESIDENDS);

            switch (line[0]) {
                case "City":
                    place = new City(setName, new Location(p, s), maxRes, new ArrayList<Settlement>());
                    break;
                case "Kibbutz":
                    place = new Kibbutz(setName, new Location(p, s),maxRes,new ArrayList<Settlement>());
                    break;
                case "Moshav":
                    place = new Moshav(setName, new Location(p, s),maxRes,new ArrayList<Settlement>());
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

    private void passage(Settlement[] settlements, ArrayList<String> union){
        int set1=0;
        int set2=0;
        for(String str:union) {
            str = str.replaceAll(" ", "");
            String[] line = str.split(";");
            //keep in mind string from txt is:#;set1;set2
            set1= findSettlement(settlements,line[1]);
            set2=findSettlement(settlements,line[2]);
            settlements[set1].addPassage(settlements[set2]);
            settlements[set2].addPassage(settlements[set1]);
        }
    }
    private int findSettlement(Settlement[] settlements,String settlementName){
        int i;
        for(i=0;i< settlements.length;i++)
        {
            if (settlementName.equals(settlements[i].getName()))
                return i;
        }
        return -1;
    }

    public Map loadMap() throws Exception {
        //converting file to Map
        Map mp = new Map(readFile(file));
        return mp;
    }



}