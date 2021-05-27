
//Matan Ben-Shushsan 205639800
//Aviya David 209203991
package ui;

import country.Map;
import country.Settlement;
import simulation.Main;
import simulation.SimThread;
import simulation.Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *All the implementations required for the button simulation in menu bar
 *
 */
public class SimulationMenu extends JMenu {


    public static boolean stop_is_on = true;
    public static boolean play_flag = false;
    private JMenuItem play=new JMenuItem("Play");
    private  JMenuItem pause=new JMenuItem("Pause");
    private JMenuItem stop=new JMenuItem("Stop");
    private JMenuItem set_tick_per_day=new JMenuItem("Set Ticks Per Day");
    public SimulationMenu(RamzorMainWindow frame){
        /**
         *
         * @param frame-main frame ref
         */
        super("Simulation");
        this.setPlayEnabled(false);
       this.setStopEnabled(false);
       this.pause.setEnabled(false);
        set_tick_per_day.setEnabled(true);
        this.add(play);
        this.add(pause);
        this.add(stop);
        this.add(set_tick_per_day);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(frame.getMenu_bar().isUploaded_file()==true) {

                 play_flag =true;
                   synchronized (Map.class){Map.class.notifyAll();}
                 setPlayEnabled(false);
                    setStopEnabled(true);
                    pause.setEnabled(true);

                }
            }
        });
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(play_flag==true){
                    play_flag =false;//stop  sim
                    setPlayEnabled(true);
                    pause.setEnabled(false);
                }
            }
        });
        this.stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //stop  sim
                play.setEnabled(false);
                play_flag =false;
                pause.setEnabled(false);
                setStopEnabled(false);
                stop_is_on=true;
                Simulation.Clock.initialization();
                frame.getMenu_bar().setLoadEnabled(true);



            }
        });
        set_tick_per_day.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetTicksPerDay dialog=new SetTicksPerDay(frame);
            }
        });

        this.setVisible(true);
    }
    public void setStopEnabled(boolean value) {
        this.stop.setEnabled(value);
    }
    public void setPlayEnabled(boolean value){
        this.play.setEnabled(value);

    }

    private class SetTicksPerDay extends JDialog{
        /**
         * This class is used to create a tic definition window for the day
         *
         */
    private JSpinner spinner;
    private JButton set_button;
    final long  def_ticks_per_day=1;


        SetTicksPerDay(RamzorMainWindow main_frame){
            super(main_frame,"Set Ticks Per Day",true);
            this.spinner=new JSpinner(new SpinnerNumberModel(Simulation.Clock.getTicks_per_day(),this.def_ticks_per_day,1000,1) );
            this.set_button=new JButton("Set");
            this.set_button.setPreferredSize(new Dimension(50,50));
            SetTicksPerDay tick_dialog=this;
            this.set_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SpinnerNumberModel sp_modal=(SpinnerNumberModel) spinner.getModel();
                    Simulation.Clock.setTicks_per_day(sp_modal.getNumber().longValue());
                    tick_dialog.setVisible(false);
                }
            });
            this.setBounds(200,200,200,200);
            this.add(spinner,BorderLayout.CENTER);
            this.add(set_button,BorderLayout.SOUTH);
            spinner.setVisible(true);
            this.setVisible(true);
        }

    }
}
