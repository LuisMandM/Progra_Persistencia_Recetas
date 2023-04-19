package com.ikasgela;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class V_VerIngredientes {
    private JButton desc_Button;
    private JButton asc_Button;
    private JLabel name_Label;
    private JLabel id_Label;
    private JLabel current_Label;
    private JLabel total_Label;
    private JPanel panel;
    private int indice;

    public V_VerIngredientes() {
        indice = 0;
        total_Label.setText(String.valueOf(Main.Ingredientes().size()));
        current_Label.setText(String.valueOf(indice + 1));
        setInfo(Main.Ingredientes().get(indice));

        asc_Button.addActionListener(e -> {
            if (indice == Main.Ingredientes().size() - 1) indice = 0;
            else indice++;
            current_Label.setText(String.valueOf(indice + 1));
            setInfo(Main.Ingredientes().get(indice));
        });
        desc_Button.addActionListener(e -> {
            if (indice == 0) indice = Main.Ingredientes().size() - 1;
            else indice--;
            current_Label.setText(String.valueOf(indice + 1));
            setInfo(Main.Ingredientes().get(indice));
        });
    }

    private void setInfo(Ingrediente ingrediente_Show) {
        name_Label.setText(ingrediente_Show.getNombre());
        id_Label.setText(String.valueOf(ingrediente_Show.getId()));
    }

    public JPanel getPanel() {
        return panel;
    }

}
