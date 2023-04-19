package com.ikasgela.GUI;

import com.ikasgela.Datos.IngredientesTableModel;
import com.ikasgela.Main;
import com.ikasgela.Datos.Receta;

import javax.swing.*;

public class V_VerReceta {
    private JPanel panel1;
    private JScrollPane recetas_Scroll;
    private JScrollPane instrucc_Scroll;
    private JScrollPane table_Scroll;
    private JList<String> recetas_Jlist;
    private JTextArea instruct_text;

    private JTable info_Tabla;


    public V_VerReceta() {
        SetRecetas();

        info_Tabla = new JTable();
        info_Tabla.setModel(new IngredientesTableModel());
        table_Scroll.setViewportView(info_Tabla);

        recetas_Jlist.addListSelectionListener(e -> {
            String seleccion = recetas_Jlist.getSelectedValue();
            Receta actual = Receta_Selected(seleccion);

            if (actual != null) {
                instruct_text.setText(FormatQuest(actual.getInstrucciones()));
                if (actual.getIngredientes().size() > 0)
                    info_Tabla.setModel(new IngredientesTableModel(actual.getIngredientes()));
                else {
                    JOptionPane.showMessageDialog(null, "Receta sin Ingredientes asignados",
                            "Sin registros", JOptionPane.INFORMATION_MESSAGE);
                    info_Tabla.setModel(new IngredientesTableModel());
                }
            } else JOptionPane.showMessageDialog(null, "Error al seleccionar la receta," +
                    "\nIntente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
        });
    }

    public void SetRecetas() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Receta receta : Main.Recetas()) {
            model.addElement(receta.getTitulo());
        }
        recetas_Jlist.setModel(model);
    }

    private static Receta Receta_Selected(String titulo) {
        for (Receta receta : Main.Recetas()) {
            if (receta.getTitulo().equals(titulo)) return receta;
        }
        return null;
    }

    public JPanel getPanel() {
        return panel1;
    }

    private String FormatQuest(String pregunta) {
        StringBuilder formato = new StringBuilder();

        char[] letras = pregunta.toCharArray();
        int count = 0;
        for (char letra : letras) {
            if (count < 50) {
                formato.append(letra);
                count++;
            } else if (letra != ' ') {
                formato.append(letra);
                count++;
            } else {
                formato.append("\n").append(letra);
                count = 0;
            }
        }
        return String.valueOf(formato);

    }
}
