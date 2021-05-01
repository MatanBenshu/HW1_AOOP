//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package simulation;

public  class Simulation {
    public static class Clock{
        private static long time=0;
        private static long ticks_per_day=1;
        public static long now() {
            return time;
        }

        public static void setTicks_per_day(long ticks_per_day) {
            Clock.ticks_per_day = ticks_per_day;
        }
        public static long DaysPast(long start_time){
            long time_past=time-start_time;
            return (time_past/ticks_per_day)+1;
        }
        public static void nextTick(){
            time++;
        }
    }
}
