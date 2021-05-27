//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package simulation;

import java.util.Map;

public  class Simulation {
    public static class Clock{
        private static long time=0;
        private static long ticks_per_day=1;
        public static long now() {
            return time;
        }
        public static void initialization(){time=0;}
        public static void setTicks_per_day(long ticks_per_day) {
            Clock.ticks_per_day = ticks_per_day;
        }
        public static long DaysPast(long start_time){
            long time_past=time-start_time;
            return (long) Math.ceil(time_past/ticks_per_day);
        }
        public static void nextTick(){
            time++;
        }

        public static long getTicks_per_day() {
            return ticks_per_day;
        }
    }
}
