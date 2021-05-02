package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private   JMenuBar menu_bar;
    private JPanel map_panel ;
    private JSlider speed_slider;
    static boolean simulation_running =false;

    public MainWindow(){
        this.menu_bar=new Menu();
        this.setTitle("Main Window");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.PAGE_AXIS));
        this.setJMenuBar(menu_bar);
        this.setVisible(true);

    }



}





