package ui;
import javax.swing.*;
import java.awt.*;

public  class MainWindow {


    public static class SMainWindow {
        private static JFrame main_window=new JFrame();
        private static  JMenuBar menu_bar=new MenuBar();
        private static JPanel map_panel = new JPanel() ;
        private static SpeedSlider speed_slider = new SpeedSlider(0,10);
        static boolean sim_is_pause =true;
        static boolean sim_is_stop = true;


        static public void start(){

            main_window.setTitle("Main Window");
            main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main_window.setLayout(new BorderLayout());
            main_window.setJMenuBar(menu_bar);
            main_window.setPreferredSize(new Dimension(800,500));
            main_window.add(map_panel,BorderLayout.CENTER);
            main_window.add(speed_slider,BorderLayout.SOUTH);
            main_window.pack();
            main_window.setVisible(true);

        }
      static public void  set_map(MapPanel map){
            main_window.remove(map_panel);
             main_window.add(map,BorderLayout.CENTER);
             map_panel=map;}
    }



}





