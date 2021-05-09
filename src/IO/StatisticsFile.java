package IO;
import country.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class StatisticsFile {
    public static void CSV(Map origMap,File file) {

        String s=file.getParent()+"\\"+file.getName()+".csv";
        File f=new File(s);
        try (PrintWriter writer = new PrintWriter(s)) {
            StringBuilder sb = new StringBuilder();
            sb.append("City/Kibbutz/Moshav");
            sb.append(',');
            sb.append("Settlement Name");
            sb.append(',');
            sb.append("Population");
            sb.append(',');
            sb.append("Ramzor Color");
            sb.append(',');
            sb.append("Vaccines Available");
            sb.append(',');
            sb.append("Healthy People");
            sb.append(',');
            sb.append("Sick People");
            sb.append('\n');
            Settlement[] settlements=origMap.getSettlements();
            for (int i=0;i<origMap.getSettlements().length;i++)
            {
                if(settlements[i] instanceof Kibbutz)
                {
                    sb.append("Kibbutz");
                    sb.append(',');
                }
                else if(settlements[i] instanceof City)
                {
                    sb.append("City");
                    sb.append(',');
                }
                else if(settlements[i] instanceof Moshav)
                {
                    sb.append("Moshav");
                    sb.append(',');
                }
                sb.append(settlements[i].getName());
               sb.append(',');

                sb.append(settlements[i].getPeople().size());
                sb.append(',');

                sb.append(settlements[i].calculateRamzorGrade().getColorName());
                sb.append(',');

                sb.append(settlements[i].getVaccineNum());
                sb.append(',');

                sb.append(settlements[i].getHealthNum());
                sb.append(',');

                sb.append(settlements[i].getSickNum());
                sb.append('\n');
            }
            writer.write(sb.toString());

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

}
