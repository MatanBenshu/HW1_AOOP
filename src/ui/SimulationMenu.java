package ui;

import simulation.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationMenu extends JMenu {
    private MenuBar menuBar;
   private boolean sim_play=false;
    private JMenuItem play=new JMenuItem("Play");
    private  JMenuItem pause=new JMenuItem("Pause");
    private JMenuItem stop=new JMenuItem("Stop");

    public SimulationMenu(MenuBar menuBar){
        super("Simulation");
        this.menuBar=menuBar;
        play.setEnabled(true);
        pause.setEnabled(true);
        stop.setEnabled(true);
        this.add(play);
        this.add(pause);
        this.add(stop);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(menuBar.isUploaded_file()==true)
                         Main.StartSim();
            }
        });


        this.setVisible(true);
    }
    public void setPlayEnabled(boolean value) {
        this.play.setEnabled(value);
    }

    public void setPauseEnabled(boolean value) {
        this.pause.setEnabled(value);
    }

    public void setStopEnabled(boolean value) {
        this.stop.setEnabled(value);
    }




    private class Play extends JMenuItem{


    }
    private class Pause extends JMenuItem{


    }
    private  class Stop extends JMenuItem{


    }

}
