package ui;

import country.Map;
import country.Settlement;
import simulation.Main;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class MapPanel extends JPanel  {
   private Graphics graphics;
   private Map map;
   private ArrayList<Shape> shapeArrayList=new ArrayList<Shape>();

         public  MapPanel(Map map){
             this.setBackground(Color.white);
             this.map=map;
         }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.graphics=g;
            if(map==null)return;
            if(map!=null){
                Settlement[] settlement= map.getSettlements();
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
                        g.drawLine(x_rel+rel.getSize().getWidth()/2,y_rel+rel.getSize().getHeight()/2,x_sett+sett.getSize().getWidth()/2,y_sett+sett.getSize().getHeight()/2);

                    }
                }



            for (int i = 0; i <settlement.length ; i++) {
                sett =settlement[i];
               int x=sett.getLocation().getPosition().getX();
               int y=sett.getLocation().getPosition().getY();
               int width=sett.getLocation().getSize().getWidth();
               int height=sett.getLocation().getSize().getHeight();
               g.setColor(Color.black);
               shapeArrayList.add(new Rectangle2D.Double(x,y,width,height));
                g.drawRect(x,y , width, height);
                g.setColor(sett.calculateRamzorGrade().getColored());
                g.fillRect(x,y , width, height);
                g.setColor(Color.black);
                g.drawString(sett.getName(),x,y+(height/2));

            }
            }


    }

public void ColorUpdate(Settlement settlement){
    int x=settlement.getLocation().getPosition().getX();
    int y=settlement.getLocation().getPosition().getY();
    int width=settlement.getLocation().getSize().getWidth();
    int height=settlement.getLocation().getSize().getHeight();
    this.graphics.setColor(settlement.calculateRamzorGrade().getColored());
    this.graphics.fillRect(x,y , width, height);
    this.repaint();
//    this.setVisible(false);this.setVisible(true);

}
public void OpenByPress(StatisticsDialog dialog){

             this.addMouseListener(new MouseInputAdapter() {
                 @Override
                 public void mouseClicked(MouseEvent e) {
                     super.mouseClicked(e);
                    Point clicked_point=  e.getPoint();
                     for (int i = 0; i <shapeArrayList.size() ; i++) {
                         if(shapeArrayList.get(i).contains(clicked_point)==true){
                          // Settlement sett=map.getSettlements()[i];
                             dialog.getStats_table().setRowSelectionInterval(i,i);
                             dialog.getStats_table().addRowSelectionInterval(i,i);
                             dialog.setVisible(true);
                            break;

                         }
                     }
                 }
             });



}

}
