package ui;
import country.Map;
import country.Settlement;

import javax.swing.*;
import java.awt.*;

public  class RamzorMainWindow {



        private  JFrame main_window = new JFrame();
        private  MapPanel map_panel =new MapPanel(null);
        private  SpeedSlider speed_slider = new SpeedSlider(0, 10);
    private  MenuBar menu_bar;
        static boolean sim_is_pause = true;
        static boolean sim_is_stop = true;


    public MapPanel getMapPanel() {
        return map_panel;
    }

    public  RamzorMainWindow() {
             menu_bar = new MenuBar(this);
            main_window.setTitle("Main Window");
            main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main_window.setJMenuBar(menu_bar);
            main_window.setPreferredSize(new Dimension(800, 500));
            main_window.add(map_panel, BorderLayout.CENTER);
            main_window.add(speed_slider, BorderLayout.SOUTH);
            main_window.pack();
            main_window.setVisible(true);

        }

    public Map getMapFile() {
        return menu_bar.getMapfile();
    }

    public void setMapPanel(MapPanel map) {
            this.map_panel.setVisible(false);
            this.main_window.remove(map_panel);
            main_window.add(map, BorderLayout.CENTER);
            map_panel = map;
            map_panel.setVisible(true);

        }

        public JFrame getWindowAddres() {
            return main_window;
        }

        public  void UpdateMap(Settlement settle) {
            map_panel.ColorUpdate(settle);
           menu_bar.UpdateStatisticsDialog();
        }
    public int getSliderValue(){return speed_slider.getValue();}
    }






