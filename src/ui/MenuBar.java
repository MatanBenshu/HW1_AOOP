package ui;


import IO.SimulationFile;
import country.Map;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuBar extends JMenuBar {
    private boolean Uploaded_file=false;
    private Map mapfile;
    private RamzorMainWindow mainWindow;
    private JMenu file;
    private SimulationMenu simulationMenu;
    private JMenu help;
    private JMenuItem edit_mutations;
   private JMenuItem Load=new JMenuItem("Load");
   private JMenuItem statistics=new JMenuItem("Statistics");
   private StatisticsDialog statisticsDialog;

    public void UpdateStatisticsDialog() {
        statisticsDialog.Update();
    }

    public MenuBar(RamzorMainWindow main_window){
        this.mainWindow= main_window;
        file = new JMenu("File");
        simulationMenu = new SimulationMenu(this.mainWindow,this);
        help = new JMenu("help");
        this.add(file);
        this.add(simulationMenu);
        this.add(help);

        Load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(main_window.sim_is_stop == true){

                JFileChooser fileChooser = new JFileChooser();


               int response = fileChooser.showOpenDialog(null);
               if(response == JFileChooser.APPROVE_OPTION) {
                   SimulationFile.file = fileChooser.getSelectedFile().getAbsolutePath();
                   SimulationFile file = null;
                   try {
                       file = new SimulationFile();
                   } catch (Exception exception) {
                       exception.printStackTrace();
                   }
                   try {
                       mapfile = file.loadMap();
                   } catch (Exception exception) {
                       exception.printStackTrace();
                   }

                main_window.setMapPanel(new MapPanel(mapfile));
                   setLoadEnabled(false);
                    Uploaded_file=true;
               }
               Uploaded_file=true;
               simulationMenu.setPlayEnabled(true);
               statisticsDialog= new StatisticsDialog(mapfile,main_window);
                statisticsDialog.setVisible(false);
               }
                else{
                    //show error messege
                }
            }
        });


        statistics.setEnabled(false);
        statistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statisticsDialog.setVisible(true);

            }
        });



        edit_mutations=new JMenuItem("Edit Mutations");
        edit_mutations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editMutatations();
            }
        });

        JMenuItem exit=new JMenuItem("exit");

       exit.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.exit(0);
           }
       });

        file.add(Load);
        file.add(statistics);
        file.add(edit_mutations);
        file.add(exit);
//        JMenuItem play_item=new JMenuItem("Play");
//        play_item.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(main_window.sim_is_stop ==true && mainWindow.sim_is_pause==true){
//                    main_window.sim_is_stop = false;
//                    main_window.sim_is_pause = false;
//                    //start sim here
//
//
//
//                }
//            }
//        });
//        simulation.add(play_item);
//        JMenuItem pause_item=new JMenuItem("Pause");
//        pause_item.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(main_window.sim_is_pause == false)
//                {
//                //puse sim here
//
//                }
//                else
//                {
//                    //error massage
//                }
//            }
//        });
//        simulation.add(pause_item);
        JMenuItem help_item=new JMenuItem("Help");

        help.add(help_item);
        help_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog help_dialog = new JDialog(main_window.getWindowAddres(), "Help",true);
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
                JDialog about_dialog = new JDialog(main_window.getWindowAddres(), "About",false);
                about_dialog.setBounds(0,0,500,500);
                JTextArea text = new JTextArea();
                text.setText("this program ");
            }
        });

    }
    private  void editMutatations() {

        JFrame f = new JFrame();
        String colum[] = {"British", "Chinese", "SouthAfrica"};
        JPanel panel = new JPanel();
        Object[][] data = {{false, false, false}, {false, false, false}, {false, false, false}};
        MutationsTable MOD = new MutationsTable(data, colum);
        JTable table = new JTable(MOD);
        table.setFillsViewportHeight(true);
        panel.add(new RowedTableScroll(table, colum));
        JDialog d = new JDialog(f, "Mutations Window", true);
        MOD.addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                //-----------------------to do--------------
                System.out.println("good");
                // your code goes here, whatever you want to do when something changes in the table
            }
        });




        d.add(panel);
        d.pack();
        d.setVisible(true);

    }

    public void setLoadEnabled(boolean value){
        if(value==true){
            Load.setEnabled(true);
            statistics.setEnabled(false);
        }
        else{
            Load.setEnabled(false);
            statistics.setEnabled(true);

        }

    }
    public boolean isUploaded_file() {
        return Uploaded_file;
    }

    public Map getMapfile() {
        return mapfile;
    }

}
