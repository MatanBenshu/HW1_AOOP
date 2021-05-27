package ui;
import country.Map;
import country.Settlement;

import javax.swing.*;
import java.awt.*;

public  class RamzorMainWindow extends JFrame {


    static private  MapPanel map_panel =new MapPanel(null);
    static private  SpeedSlider speed_slider = new SpeedSlider(1, 10);
   static private  MenuBar menu_bar;
    static boolean sim_is_pause = true;


public RamzorMainWindow() {




        menu_bar = new MenuBar(this);
        setTitle("Main Window");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(menu_bar);
        this.setPreferredSize(new Dimension(800, 500));
        this.add(map_panel, BorderLayout.CENTER);
        this.add(speed_slider, BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);

    }
    public MapPanel getMapPanel () {
        return map_panel;
    }
    public Map getMapFile () {
        return menu_bar.getMapfile();
    }

    public void setMapPanel (MapPanel map){
        this.map_panel.setVisible(false);
        this.remove(map_panel);
        this.add(map, BorderLayout.CENTER);
        map_panel = map;
        map_panel.setVisible(true);

    }

    public MenuBar getMenu_bar () {
        return menu_bar;
    }

    public JFrame getWindowAddres () {
        return this;
    }

    public static void UpdateMap () {
        Settlement[] settlements =menu_bar.getMapfile().getSettlements();
        for (int i = 0; i < settlements.length; i++) {

            map_panel.ColorUpdate(settlements[i]);
        }

        menu_bar.UpdateStatisticsDialog();
    }

    public static void UpdateMap (Settlement settle){
        map_panel.ColorUpdate(settle);
        menu_bar.UpdateStatisticsDialog();
}

    public static int getSliderValue () {
        return speed_slider.getValue();
    }
}











