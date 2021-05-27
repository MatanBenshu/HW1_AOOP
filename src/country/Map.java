//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package country;

import simulation.Simulation;
import ui.RamzorMainWindow;

import java.util.concurrent.CyclicBarrier;

public class Map {
    static private CyclicBarrier cyclicBarrier;
    private Settlement[] settlements;

    public Map(Settlement[] settlements) {
        this.settlements = settlements;
    }

    public Settlement[] getSettlements() {
        return settlements;
    }

    public void setSettlements(Settlement[] settlements) {
        this.settlements = settlements;
    }

    public String[] Get_settlementsNameArry(){
        String[] names = new String[settlements.length];
        for (int i = 0; i < settlements.length; i++) {
            names[i]=settlements[i].getName();
        }
        return names;

    }

    public void StartThreads(){

        for (int i = 0; i <settlements.length ; i++) {
            new Thread(settlements[i],settlements[i].getName()).start();

        }
        cyclicBarrier=new CyclicBarrier(settlements.length, new Runnable() {
            @Override
            public void run() {
                Simulation.Clock.nextTick();
                RamzorMainWindow.UpdateMap();
                try {
                    Thread.sleep(1000* RamzorMainWindow.getSliderValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }





    public static CyclicBarrier getCyclicBarrier() {
        return cyclicBarrier;
    }
}
