package com.ikasgela.GUI;

import javax.swing.*;
import java.awt.*;

public class V_Principal {
    private JPanel panel;
    private JButton newRecetaButton;
    private JButton verRecetasButton;
    private JButton newIngredienteButton;
    private JButton verIngredientesButton;


    public V_Principal() {
        newRecetaButton.addActionListener(e -> {
            JFrame frame_Init = new JFrame("Nueva Receta");
            frame_Init.setContentPane(new V_AddReceta().getPanel());
            frame_Init.pack();
            frame_Init.setVisible(true);
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            int x = (screenSize.width - frame_Init.getWidth()) / 2;
            int y = (screenSize.height - frame_Init.getHeight()) / 2;
            frame_Init.setLocation(x, y);

        });
        verRecetasButton.addActionListener(e -> {
            JFrame frame_Init = new JFrame("Recetas");
            frame_Init.setContentPane(new V_VerReceta().getPanel());
            frame_Init.pack();
            frame_Init.setVisible(true);
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            int x = (screenSize.width - frame_Init.getWidth()) / 2;
            int y = (screenSize.height - frame_Init.getHeight()) / 2;
            frame_Init.setLocation(x, y);
        });
        newIngredienteButton.addActionListener(e -> {
            JFrame frame_Init = new JFrame("Nuevo Ingrediente");
            frame_Init.setContentPane(new V_AddIngrediente().getPanel());
            frame_Init.pack();
            frame_Init.setVisible(true);
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            int x = (screenSize.width - frame_Init.getWidth()) / 2;
            int y = (screenSize.height - frame_Init.getHeight()) / 2;
            frame_Init.setLocation(x, y);

        });
        verIngredientesButton.addActionListener(e -> {
            JFrame frame_Init = new JFrame("Ingredientes");
            frame_Init.setContentPane(new V_VerIngredientes().getPanel());
            frame_Init.pack();
            frame_Init.setVisible(true);
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            int x = (screenSize.width - frame_Init.getWidth()) / 2;
            int y = (screenSize.height - frame_Init.getHeight()) / 2;
            frame_Init.setLocation(x, y);
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
