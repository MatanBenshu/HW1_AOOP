package ui;

import simulation.Main;
import simulation.Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationMenu extends JMenu {
    private MenuBar menuBar;

    private JMenuItem play=new JMenuItem("Play");
    private  JMenuItem pause=new JMenuItem("Pause");
    private JMenuItem stop=new JMenuItem("Stop");
    private JMenuItem set_tick_per_day=new JMenuItem("Set Ticks Per Day");
    public SimulationMenu(RamzorMainWindow frame,MenuBar menuBar){
        super("Simulation");
        this.menuBar=menuBar;
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
                if(menuBar.isUploaded_file()==true) {
                    try {
                        Main.StartSim();
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    setPlayEnabled(false);
                    Main.play_sim=true;
                    setStopEnabled(true);
                    Main.stop_is_on=false;
                    pause.setEnabled(true);
                }
            }
        });
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Main.play_sim==true){
                    Main.play_sim=false;//stop  sim
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
                Main.play_sim=false;
                pause.setEnabled(false);
                setStopEnabled(false);
                Main.stop_is_on=false;
                frame.getMenu_bar().setLoadEnabled(true);


            }
        });
        set_tick_per_day.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JDialog Ticks_dialog= new JDialog(frame,"Set Ticks Per Day",true);
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
    private JSpinner spinner;
    private JButton set_button;
    final long  def_ticks_per_day=Simulation.Clock.getTicks_per_day();


        SetTicksPerDay(RamzorMainWindow main_frame){
            super(main_frame,"Set Ticks Per Day",true);
            this.spinner=new JSpinner(new SpinnerNumberModel(this.def_ticks_per_day,this.def_ticks_per_day,1000,1) );
            this.set_button=new JButton("Set");
            this.set_button.setPreferredSize(new Dimension(50,50));
            SetTicksPerDay tick_dialog=this;
            this.set_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SpinnerNumberModel sp_modal=(SpinnerNumberModel) spinner.getModel();
                    Simulation.Clock.setTicks_per_day(sp_modal.getNumber().longValue());
                    System.out.println("tics "+Simulation.Clock.getTicks_per_day());
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
