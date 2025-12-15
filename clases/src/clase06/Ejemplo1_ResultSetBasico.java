package clase06;

import java.sql.*;

public class Ejemplo1_ResultSetBasico {
    public static void main(String[] args) {
        // 1. Datos de conexión (Igual que la Clase 05)
        String url = "jdbc:h2:./src/clase05/BDJuegos";  // Adjuntar la ruta de vuestra BBDD (BDJuegos en modo fichero)
        String user = "sa";                             // Usuario por defecto de H2
        String password = "";                           // Contraseña vacía

        try {
            //
            Class.forName("org.h2.Driver");
            System.out.println("Driver H2 cargado correctamente.");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida con BDJuegos.");

            Statement st = conn.createStatement();

            String sql = "SELECT ID, NOMBRE, GENERO, PUNTUACION FROM JUEGO";

            ResultSet rs = st.executeQuery(sql);

            System.out.println("Lista de juegos (usando ResultSet):");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String nombre = rs.getString("NOMBRE");
                String genero = rs.getString("GENERO");
                double puntuacion = rs.getDouble("PUNTUACION");

                System.out.println(" - [" + id + "] " + nombre + " (" + genero + ") -> " + puntuacion);
            }

            rs.close();
            st.close();
            conn.close();
            System.out.println("Conexión cerrada correctamente.");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver H2 no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }
}
