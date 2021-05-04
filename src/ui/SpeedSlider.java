package ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SpeedSlider extends JSlider {
    public  SpeedSlider(int min,int max){
        super(min,max);
        this.setPaintTicks(true);
        this.setMinorTickSpacing(1);
        this.setPaintTrack(true);
        this.setMajorTickSpacing(1);
        this.setPaintLabels(true);


    }

   int getSliderValue(){
        return this.getValue();
   }

}
