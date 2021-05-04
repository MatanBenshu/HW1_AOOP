package ui;


import IO.SimulationFile;
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
                StatisticsDialog dialog =new StatisticsDialog();
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
        JMenuItem help_item=new JMenuItem("Help");

        help.add(help_item);
        help_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog help_dialog = new JDialog(MainWindow.SMainWindow.getWindowAddres(), "Help",true);
                help_dialog.setBounds(0,0,500,500);
                JTextArea text = new JTextArea();
                text.setText("menu bar option:\n" +
                        "file option :\n" +
                        "1)load only .txt files\n" +
                        "2)statistics optin:work if the user load file,this option show all settlement stats\n" +
                        "3)exit option close the program\n" +
                        "simulation option:\n" +
                        "1)load file.txt before playing sim\n" +
                        "2)pause is possible only if the sim is running");
                text.setBounds(0,0,400,400);
                text.setLineWrap(true);
                text.setWrapStyleWord(true);
                help_dialog.add(text);
                help_dialog.setVisible(true);
            }
        });
        JMenuItem about_item =new JMenuItem("About");
        help.add(about_item);
        about_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog about_dialog = new JDialog(MainWindow.SMainWindow.getWindowAddres(), "About",false);
                about_dialog.setBounds(0,0,500,500);
                JTextArea text = new JTextArea();
                text.setText("this program ");
            }
        });

    }


}
