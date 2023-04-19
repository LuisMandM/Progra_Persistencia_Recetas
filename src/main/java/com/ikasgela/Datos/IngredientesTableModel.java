package com.ikasgela.Datos;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientesTableModel extends AbstractTableModel {
    private String[] columnas = {"Ingrediente", "Cantidad"};
    private HashMap<String, String> ingredientesMap;
    private List<String> ingredientes_List = new ArrayList<>();
    private List<String> cantidades_List = new ArrayList<>();

    public IngredientesTableModel() {
        this.ingredientesMap = new HashMap<>();
    }

    public IngredientesTableModel(HashMap<String, String> ingredinetes) {
        this.ingredientesMap = ingredinetes;
        for (Map.Entry<String, String> entry : ingredientesMap.entrySet()) {
            ingredientes_List.add(entry.getKey());
            cantidades_List.add(entry.getValue());
        }
    }

    @Override
    public int getRowCount() {
        return ingredientes_List.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return ingredientes_List.get(rowIndex);
            case 1:
                return cantidades_List.get(rowIndex);
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

}
