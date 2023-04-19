package com.ikasgela.GUI;

import com.ikasgela.Datos.Ingrediente;
import com.ikasgela.Main;

import javax.swing.*;

public class V_AdminCantidades {
    private JComboBox<String> ingredientes_comboBox;
    private JTextField cantidad_textField;
    private JButton saveButton;
    private JPanel panel;

    public V_AdminCantidades() {

        for (Ingrediente ingrediente : Main.Ingredientes()) {
            ingredientes_comboBox.addItem(ingrediente.getNombre());
        }
        saveButton.addActionListener(e -> {
            String ingrediente = ingredientes_comboBox.getSelectedItem().toString();
            String cantidad = cantidad_textField.getText();
            String cantidad_Copia = cantidad.trim().replace(" ", "");
            if (!cantidad_Copia.equals("")) {
                V_AddReceta.AddIngrediente(ingrediente, cantidad);
                cantidad_textField.setText("");
                JOptionPane.showMessageDialog(null,
                        "Ingrediente añadido correctamente", "Info Registro", JOptionPane.INFORMATION_MESSAGE);

            } else JOptionPane.showMessageDialog(null,
                    "Cantidad Incorrecto", "Info Cantidad", JOptionPane.ERROR_MESSAGE);
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
