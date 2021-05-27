
//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package IO;

import country.Settlement;
import ui.MenuBar;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *Implementation of writing to a log file
 * this class implements 2 methods-
 * 1)Save
 * 2)write
 *
 */

public class LogFile {
   static private PrintWriter printWriter=null;
   static private FileWriter fileWriter=null;
    private static Date date;
    private static DateFormat dateFormat;
    public static void Save(File file) throws IOException {

        String s=file.getParent()+"\\"+file.getName()+".txt";
        date =  Calendar.getInstance().getTime();
     dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
     fileWriter=new FileWriter(s,true);
     printWriter=new PrintWriter(fileWriter);

    }
    public static boolean WriterIsNull(){
        if (fileWriter==null)
            return true;
        else
            return false;

    }
    public  synchronized static void Write(Settlement settlement) throws IOException {
        /**
         * write log date to log file
         * @param settlement-the settlement required to write.
         */
      printWriter.println("-----------------------");
       printWriter.println("Date and Time: "+dateFormat.format(date));
     printWriter.println("settlement: "+settlement.getName());
        printWriter.println("Sick People: "+settlement.getSickNum());
        printWriter.println("dead: "+settlement.getDeadNum());
        printWriter.println("-----------------------");
    }

    }






