package com.ikasgela.GUI;

import com.ikasgela.Main;
import com.ikasgela.Datos.Receta;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class V_AddReceta {
    private JTextField nombre_textField;
    private JTextPane instrucc_textPane;
    private JButton addIngredientesButton;
    private JButton guardarButton;
    private JPanel panel;

    private static HashMap<String, String> ingredientes = new HashMap<>();

    public V_AddReceta() {
        addIngredientesButton.addActionListener(e -> {
            JFrame frame_Init = new JFrame("Añadiendo ingredientes");
            frame_Init.setContentPane(new V_AdminCantidades().getPanel());
            frame_Init.pack();
            frame_Init.setVisible(true);
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            int x = (screenSize.width - frame_Init.getWidth()) / 2;
            int y = (screenSize.height - frame_Init.getHeight()) / 2;
            frame_Init.setLocation(x, y);
        });
        guardarButton.addActionListener(e -> {
            String nombre = nombre_textField.getText();
            String instrucciones = instrucc_textPane.getText();
            String copia_nombre = nombre.trim().replaceAll(" ", "");
            if (!copia_nombre.equals("") && !Main.Recetas().contains(nombre) && !instrucciones.equals("")) {
                if (ingredientes.size() > 0) {
                    Main.AddReceta(new Receta(nombre, instrucciones, ingredientes));
                } else {
                    Main.AddReceta(new Receta(nombre, instrucciones));
                }
                JOptionPane.showMessageDialog(null,
                        "Receta guardada Correctamente", "Receta Added", JOptionPane.INFORMATION_MESSAGE);
                nombre_textField.setText("");
                instrucc_textPane.setText("");
            } else JOptionPane.showMessageDialog(null,
                    "Puede que tenga datos incorrectos o repetidos intente nuevamente", "Error",
                    JOptionPane.ERROR_MESSAGE);

        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public static void AddIngrediente(String ingrediente, String cantidad) {
        ingredientes.put(ingrediente, cantidad);
    }
}
