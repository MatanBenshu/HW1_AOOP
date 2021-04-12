package com.company;

 public abstract  class  RandomV {

     public static double GetRand(double min, double max){
      return ((Math.random() * (max - min)) + min);
     }


}
