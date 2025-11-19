package clase05;

// Importamos las clases JDBC reales
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class TestConexionH2 {
    public static void main(String[] args) {

        // 1) URL de conexión en modo fichero.
        // Formato general H2 en fichero:
        // jdbc:h2/NOMBRE crea fichero en la carpeta actual.
        String url = "jdbc:h2:./src/clase05/BDJuegos";

        // 2) Credenciales por defecto de H2.
        String user = "sa";
        String password = "";

        try {

            // 3) Cargar explícitamente el driver H2.
            Class.forName("org.h2.Driver");
            System.out.println("Driver H2 cargado correctamente.");
            // 4) Pedimos una conexión real al DriverManager.
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida con H2.");

            // 5) (Opcional) Ejecutamos una consulta simple: SELECT 1
            // Escribimos la sentencia SQL
            String sql = "SELECT 1 AS resultado";
            // Creamos un Statement a partir de la conexión.
            // El Statement servirá para enviar la sentencia SQL al motor H2
            Statement st = conn.createStatement();
            // Ejecutamos la consulta con executeQuery
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                int valor = rs.getInt("resultado");
                System.out.println("Resultado de prueba: " + valor);
            }

            // 6) Cerramos recursos
            rs.close();
            st.close();
            conn.close();
            System.out.println("Conexión cerrada correctamente.");

        } catch (ClassNotFoundException e) {
            System.out.println("No se ha encontrado la clase del driver H2: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error SQL al conectar o consultar: " + e.getMessage());
        }
    }
}
