package ui;

import country.Map;
import country.Settlement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VccinateDialog extends JDialog {
    private JTextField textField=new JTextField();
    private JButton add_button = new JButton("Add");
    private StatisticsDialog main_dialog;
    VccinateDialog(StatisticsDialog main_dialog, Map map){
        super(main_dialog,"Add Vaccinate",false);
        this.main_dialog=main_dialog;
        this.setPreferredSize(new Dimension(200,70));
        this.setResizable(false);
        this.pack();
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.LINE_AXIS));

        add_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num;
                String text_string = textField.toString();
                    num= Integer.parseInt(text_string);
                   Settlement settlement= main_dialog.getStats_table().getSettlementInRow();
                   settlement.setVaccine_num(num);
                   main_dialog.Update();

            }
        });
        this.add(textField);
        this.add(add_button);
        this.setVisible(true);

    }
}
