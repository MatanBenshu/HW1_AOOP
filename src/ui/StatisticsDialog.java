package ui;

import IO.StatisticsFile;
import country.Map;
import country.Settlement;
import country.SettlementData;
import simulation.Main;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class StatisticsDialog extends JDialog  {
    private  Map mapfile;
    private String user_string;
    private JTextField text_filed = new JTextField(20);
        private JComboBox<Settlement> col_select=new JComboBox<Settlement>();
        private JPanel north_panel=new JPanel();
        private  JPanel south_panel=new JPanel();;
        private StaticTable stats_table;
        private JFileChooser fileChooser=new JFileChooser();
        private JButton save_button = new JButton("Save");
        private JButton add_sick = new JButton("Add Sick");
        private JButton vaccinate = new JButton("Vaccinate");


    public StatisticsDialog(Map mapfile,RamzorMainWindow mainwindow){

        super(mainwindow.getWindowAddres(),"Statistics Window",false);
        this.mapfile=mapfile;
        this.setBounds(0,0,800,500);
        this.north_panel.setLayout(new BoxLayout(north_panel,BoxLayout.LINE_AXIS));
        this.north_panel.setPreferredSize(new Dimension(800,30));
        this.text_filed.setPreferredSize(new Dimension(100,30));


        text_filed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_string = text_filed.getText();
                System.out.println(user_string);
            }
        });
        col_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.north_panel.add(col_select);
        this.north_panel.add(text_filed);
        this.col_select.setPreferredSize(new Dimension(500,30));
        this.add(north_panel,BorderLayout.NORTH);


        // end of setting north panel
       this.south_panel.setLayout(new BoxLayout(south_panel,BoxLayout.LINE_AXIS));
       this.south_panel.setPreferredSize(new Dimension(800,30));
        this.south_panel.add(save_button);
        save_button.setPreferredSize(new Dimension(500,30));
        save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               JFileChooser savefile =new JFileChooser();

               savefile.showSaveDialog(null);
              StatisticsFile.CSV(mapfile,savefile.getSelectedFile());


            }
        });

        add_sick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Settlement settle = stats_table.getSettlementInRow();
                    Main.makeSick(settle, Main.sizeOfSick(settle.getResidentsNum()));
                    stats_table.Update(stats_table);
                 mainwindow.UpdateMap(settle);
            }
        });
        StatisticsDialog dialog=this;
        vaccinate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new VccinateDialog(dialog,mapfile);


            }
        });
        south_panel.add(add_sick);
        south_panel.add(vaccinate);
        this.add(south_panel,BorderLayout.SOUTH);
        //end of setting south panel
        this.stats_table=new StaticTable(new SettlementData(mapfile),this,text_filed);

        //end of setting center table
        this.setVisible(true);

   }

    public String getUser_string() {
        return user_string;
    }

    public StaticTable getStats_table() {
        return stats_table;
    }

    public void TableError ()
    {
        JOptionPane.showMessageDialog(this,"please select your row\n","Error",JOptionPane.ERROR_MESSAGE);
        }

    public void Update(){stats_table=new StaticTable(new SettlementData(mapfile),this,text_filed);}
}
