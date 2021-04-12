package simulation;

public  class Simulation {
    public static class Clock{
        private static long time=0;

        public static long now() {
            return time;
        }
        public static void nextTick(){
            time++;
        }
    }
}
