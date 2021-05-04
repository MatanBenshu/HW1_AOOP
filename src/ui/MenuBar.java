package ui;


import IO.SimulationFile;
import country.Settlement;
import simulation.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {

    private JMenu file;
    private JMenu simulation;
    private JMenu help;
    public static boolean load =false;

    public MenuBar(){

        file = new JMenu("File");
        simulation = new JMenu("Simulation");
        help = new JMenu("help");
        this.add(file);
        this.add(simulation);
        this.add(help);
        JMenuItem Load=new JMenuItem("Load");
        Load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(MainWindow.SMainWindow.sim_is_stop == true){
                JFileChooser fileChooser = new JFileChooser();
               int response = fileChooser.showOpenDialog(null);
               if(response == JFileChooser.APPROVE_OPTION) {
                   SimulationFile.file = fileChooser.getSelectedFile().getAbsolutePath();
                   SimulationFile X = null;
                   try {
                       X = new SimulationFile();
                   } catch (Exception exception) {
                       exception.printStackTrace();
                   }
                   try {
                       Main.y = X.loadMap();
                   } catch (Exception exception) {
                       exception.printStackTrace();
                   }
                MainWindow.SMainWindow.set_map(new MapPanel());

               }
               }
                else{
                    //show error messege
                }
            }
        });
        JMenuItem statistics=new JMenuItem("Statistics");
        statistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Statistics dialog =new Statistics();
            }
        });



        JMenuItem edit_mutations=new JMenuItem("Edit Mutations");
        JMenuItem exit=new JMenuItem("exit");

       exit.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.exit(0);
           }
       });

        file.add(Load);
        file.add(statistics);
        file.add(exit);
        JMenuItem play_item=new JMenuItem("Play");
        play_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(MainWindow.SMainWindow.sim_is_stop ==true &&MainWindow.SMainWindow.sim_is_pause==true){
                    MainWindow.SMainWindow.sim_is_stop = false;
                    MainWindow.SMainWindow.sim_is_pause = false;
                    //start sim here



                }
            }
        });
        simulation.add(play_item);
        JMenuItem pause_item=new JMenuItem("Pause");
        pause_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(MainWindow.SMainWindow.sim_is_pause == false)
                {
                //puse sim here

                }
                else
                {
                    //error massage
                }
            }
        });
        simulation.add(pause_item);


    }


}
