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
        JDialog dialog=this;
        add_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num;
                String text_string = textField.getText();
                    num= Integer.parseInt(text_string);
                   Settlement settlement;
                settlement = main_dialog.getStats_table().getSettlementInRow();
                settlement.addVaccine_num(num);
                   main_dialog.getStats_table().Update(main_dialog.getStats_table());
                   dialog.setVisible(false);
                   main_dialog.getStats_table().setVisible(false);main_dialog.getStats_table().setVisible(true);

            }
        });
        this.add(textField);
        this.add(add_button);
        this.setVisible(true);

    }
}
