package com.ikasgela;

import com.ikasgela.Datos.Config;
import com.ikasgela.Datos.Ingrediente;
import com.ikasgela.Datos.Receta;
import com.ikasgela.GUI.V_Principal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    private static Logger logger = LogManager.getLogger();
    private static Connection conexion = null;

    private static List<Receta> recetas = new ArrayList<>();
    private static List<Ingrediente> ingredientes = new ArrayList<>();

    public static void main(String[] args) {
        conectar();
        QuestIngredientes();
        QuestRecetas();
        desconectar();

        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        JFrame frame_Init = new JFrame("Menu_Principal");
        frame_Init.setContentPane(new V_Principal().getPanel());
        frame_Init.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_Init.pack();
        frame_Init.setVisible(true);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - frame_Init.getWidth()) / 2;
        int y = (screenSize.height - frame_Init.getHeight()) / 2;
        frame_Init.setLocation(x, y);

    }


    public static void AddReceta(Receta receta) {
        conectar();
        try {
            String squery = "INSERT INTO RECETA (TITULO, INSTRUCCIONES) VALUES (?,?)";
            PreparedStatement pst = conexion.prepareStatement(squery);
            pst.setString(1, receta.getTitulo());
            pst.setString(2, receta.getInstrucciones());

            int filas_modificadas = pst.executeUpdate();
            if (filas_modificadas > 0) {
                System.out.println("RECETA ADDED");
                commit();
            } else System.out.println("Ha ocurrido un eror intente nuevamente");

            for (Map.Entry<String, String> cantidad : receta.getIngredientes().entrySet()) {
                String squery_cant = "INSERT INTO RECETA_INGREDIENTE VALUES((SELECT ID_RECETA FROM RECETA WHERE TITULO = ?)," +
                        "(SELECT ID_INGREDIENTE FROM INGREDIENTE WHERE NOMBRE = ?),?)";
                PreparedStatement pst_cant = conexion.prepareStatement(squery_cant);
                pst_cant.setString(1, receta.getTitulo());
                pst_cant.setString(2, cantidad.getKey());
                pst_cant.setString(3, cantidad.getValue());
                int cantidades_added = pst_cant.executeUpdate();
                if (cantidades_added > 0) System.out.println("CANTIDAD ADDED");
                else System.out.println("Ha ocurrido un eror intente nuevamente");
                commit();
            }
            QuestRecetas();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        desconectar();
    }

    public static void AddIngrediente(Ingrediente ingrediente) {
        conectar();
        try {
            String squery = "INSERT INTO INGREDIENTE(NOMBRE) VALUES(?)";
            PreparedStatement pst = conexion.prepareStatement(squery);
            pst.setString(1, ingrediente.getNombre());
            int filas_modificadas = pst.executeUpdate();
            if (filas_modificadas > 0) {
                System.out.println("Ingrediente añadido");
                commit();
                QuestIngredientes();
            } else System.out.println("Ha ocurrido un eror intente nuevamente");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        desconectar();
    }


    private static void QuestRecetas() {
        try {
            Statement statement = conexion.createStatement();
            ResultSet result_Recetas = statement.executeQuery("SELECT * FROM RECETA");

            while (result_Recetas.next()) {

                Receta actual = new Receta(result_Recetas.getInt("ID_RECETA"),
                        result_Recetas.getString("TITULO"),
                        result_Recetas.getString("INSTRUCCIONES"));

                String squery = "SELECT I.NOMBRE AS NOMBRE, R.CANTIDAD AS CANTIDAD FROM RECETA_INGREDIENTE R JOIN " +
                        "INGREDIENTE I USING(ID_INGREDIENTE) WHERE ID_RECETA = " + actual.getId();
                Statement st = conexion.createStatement();
                ResultSet result_ingredientes = st.executeQuery(squery);

                while (result_ingredientes.next()) {
                    actual.addIngredientes(result_ingredientes.getString("NOMBRE"),
                            result_ingredientes.getString("CANTIDAD"));
                }
                if (!recetas.contains(actual)) recetas.add(actual);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void QuestIngredientes() {
        try {
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM INGREDIENTE");
            while (resultSet.next()) {
                Ingrediente actual = new Ingrediente(resultSet.getString("NOMBRE"),
                        resultSet.getInt("ID_INGREDIENTE"));
                if (!ingredientes.contains(actual)) ingredientes.add(actual);
            }
            if (ingredientes.size() == 0) System.out.println("Sin ingredientes registrados");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void conectar() {
        try {

            // Cadena de conexión
            String servidor = Config.leer("DB_HOST");
            String puerto = Config.leer("DB_PORT");
            String bd = Config.leer("DB_DATABASE");
            String login = Config.leer("DB_USERNAME");
            String password = Config.leer("DB_PASSWORD");
            String url = "jdbc:oracle:thin:@" + servidor + ":" + puerto + ":" + bd;

            // Establecimiento de conexión
            conexion = DriverManager.getConnection(url, login, password);

            logger.info("Conexión abierta");

        } catch (SQLException e) {
            logger.fatal(e);
        }
    }

    public static void desconectar() {
        try {
            conexion.close();
            logger.info("Conexión cerrada");
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public static void commit() throws SQLException {
        Statement st = conexion.createStatement();
        st.execute("COMMIT");
    }

    public static List<Receta> Recetas() {
        return recetas;
    }

    public static List<Ingrediente> Ingredientes() {
        return ingredientes;
    }
}
