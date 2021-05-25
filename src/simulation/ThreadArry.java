package simulation;

import country.Settlement;
import ui.RamzorMainWindow;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class ThreadArry extends ArrayList<Thread> {
    static private ThreadArry Instance=null;
    private boolean has_threads=false;
    private  ThreadArry(){};
    static public  ThreadArry getInstance() {
        if(Instance==null) {
            Instance = new ThreadArry();
        }
        return Instance;
    }
    public void startThreads(){
        if(has_threads){
        int size=this.size();
        for (int i = 0; i <size(); i++) {
            this.get(i).start();
        }
        }
    }
     public void makeSimThreads(RamzorMainWindow window){
        Settlement[]  settlements=window.getMenu_bar().getMapfile().getSettlements();
        for (int i = 0; i <settlements.length ; i++) {
            this.add(new Thread(new SimThread(settlements[i],window,settlements[i].getName())));
        }
         SimThread.setCyclicBarrier(this.size());
         has_threads=true;
    }
    public void initialization(){

        if (this.size()!=0){

            while(this.size()!=0)
                this.remove(0);
        }

        int size=this.size();
    }


}
