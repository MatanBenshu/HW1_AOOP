package ui;

import country.Map;
import country.Settlement;
import simulation.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MapPanel extends JPanel  {

         public  MapPanel(){
             this.setBackground(Color.white);
         }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(Main.y.getSettlements()!=null){
                Settlement[] settlement= Main.y.getSettlements();
                Settlement sett ;

                for (int i = 0; i <settlement.length ; i++) {
                    for (int j = 0; j <settlement[i].getPassages().size() ; j++) {
                        Graphics2D gr = (Graphics2D ) g;
                        gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON) ;
                        sett=settlement[i];
                        Settlement rel=sett.getPassages().get(j);
                        int x_rel,y_rel,x_sett,y_sett;
                        x_rel=rel.getLocation().getPosition().getX();
                        x_sett=sett.getLocation().getPosition().getX();
                        y_rel=rel.getLocation().getPosition().getY();
                        y_sett=sett.getLocation().getPosition().getY();
                        g.drawLine(x_rel,y_rel,x_sett,y_sett);

                    }
                }



            for (int i = 0; i <settlement.length ; i++) {
                sett =settlement[i];
               int x=sett.getLocation().getPosition().getX();
               int y=sett.getLocation().getPosition().getY();
               int width=sett.getLocation().getSize().getWidth();
               int height=sett.getLocation().getSize().getHeight();
               g.setColor(Color.black);
                g.drawRect(x,y , width, height);
                g.setColor(sett.calculateRamzorGrade().getColored());
                g.fillRect(x,y , width, height);
                g.setColor(Color.black);
                g.drawString(sett.getName(),x,y+(height/2));

            }
            }


    }



}
