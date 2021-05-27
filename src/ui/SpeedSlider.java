//Matan Ben-Shushsan 205639800
//Aviya David 209203991

package ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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



}
