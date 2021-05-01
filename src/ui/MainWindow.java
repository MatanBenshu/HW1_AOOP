package ui;
import javax.swing.*;

public class MainWindow extends JFrame{

    public MainWindow() {

        super("Main Window");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        JPanel MenuBar = new JPanel();
        MenuBar.add(new JButton("Menu"));
        JPanel MapPanel = new JPanel();
        MapPanel.add(new JButton("Map Panel"));
        JSlider speedslider = new JSlider();
        speedslider.add(new JLabel("Simulation Speed Slider"));
        this.add(MenuBar);
        this.add(MapPanel);
        this.add(speedslider);
        this.setVisible(true);


    }


}
