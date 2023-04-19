package com.ikasgela;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class V_AddIngrediente {
    private JTextField name_textField;
    private JPanel panel;
    private JButton saveButton;

    public V_AddIngrediente() {
        saveButton.addActionListener(e -> {
            String nombre = name_textField.getText();
            Ingrediente actual = new Ingrediente(nombre);
            if (Main.Ingredientes().contains(actual)) JOptionPane.showMessageDialog(null,
                    "Este Ingrediente ya existe", "Info Registro", JOptionPane.ERROR_MESSAGE);
            else {
                Main.AddIngrediente(actual);
                JOptionPane.showMessageDialog(null,
                        "Ingrediente añadido correctamente", "Info Registro", JOptionPane.INFORMATION_MESSAGE);
            }
            name_textField.setText("");
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
