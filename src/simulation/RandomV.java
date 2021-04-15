//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package simulation;

import java.util.Random;

public abstract  class  RandomV {

     public static double GetRand(double min, double max){
      return ((Math.random() * (max - min)) + min);
     }
     public static int GetRand(int max){
         return (new Random().nextInt(max));
     }


}
