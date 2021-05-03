package ui;

import country.Map;
import country.Settlement;
import simulation.Main;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

         public  MapPanel(){
             this.setBackground(Color.white);
         }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Settlement[] settlement= Main.y.getSettlements();
            for (int i = 0; i <settlement.length ; i++) {
                Settlement sett =settlement[i];
               int x=sett.getLocation().getPosition().getX();
               int y=sett.getLocation().getPosition().getY();
               int width=sett.getLocation().getSize().getWidth();
               int height=sett.getLocation().getSize().getHeight();
               g.setColor(Color.black);
                g.drawRect(x,y , width, height);
                g.setColor(sett.calculateRamzorGrade().getColored());
                g.fillRect(x,y , width, height);
            }

    }


}
