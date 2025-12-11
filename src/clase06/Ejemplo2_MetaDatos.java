package clase06;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejemplo2_MetaDatos {
    public static void main(String[] args) {
        // 1. Datos de conexión (Igual que la Clase 05)
        String url = "jdbc:h2:./src/clase05/BDJuegos";  // Adjuntar la ruta de vuestra BBDD (BDJuegos en modo fichero)
        String user = "sa";                             // Usuario por defecto de H2
        String password = "";                           // Contraseña vacía

        try {
            Class.forName("org.h2.Driver");
            System.out.println("Driver H2 cargado correctamente.");

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida con BDJuegos.");

            DatabaseMetaData dbmd = conn.getMetaData();

            System.out.println("Información general de la base de datos:");
            System.out.println("    - Producto: " + dbmd.getDatabaseProductName());
            System.out.println("    - Versión producto: " + dbmd.getDatabaseProductVersion());
            System.out.println("    - Driver " + dbmd.getDriverName());
            System.out.println("    - URL: " + dbmd.getURL());
            System.out.println("    - Usuario: " + dbmd.getUserName());

            System.out.println("\n Tablas existentes en la base de datos:");
            String catalog = null;
            String schemaPattern = null;
            String tableNamePattern = null;
            String[] tipos = {"TABLE"};

        } catch (ClassNotFoundException e) {
            System.out.println("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
