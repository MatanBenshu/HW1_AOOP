package ui;


import IO.SimulationFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JMenuBar {

    private JMenu file;
    private JMenu simulation;
    private JMenu help;

    public Menu(){

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
                if(SimulationFile.simulation_status == false){
                JFileChooser fileChooser = new JFileChooser();
               int response = fileChooser.showOpenDialog(null);
               if(response == JFileChooser.APPROVE_OPTION) {
                   SimulationFile.file = fileChooser.getSelectedFile().getAbsolutePath();
               }
               }

            }
        });
        JMenuItem statistics=new JMenuItem("Statistics");
        JMenuItem edit_mutations=new JMenuItem("Edit Mutations");
        JMenuItem exit=new JMenuItem("exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        file.add(Load);
        file.add(exit);
        JMenuItem play_item=new JMenuItem();
        play_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SimulationFile.simulation_run==false){
                    SimulationFile.simulation_run=true;


                }
            }
        });
        JMenuItem pause_item=new JMenuItem();
        pause_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SimulationFile.simulation_run==true)

            }
        });



    }


}
