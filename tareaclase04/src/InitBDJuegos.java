import java.sql.*;

public class InitBDJuegos {

    private static final String URL = "jdbc:h2:./tareaclase04/src/BDJuegos";
    private static final String USER = "sa";
    private static final String PASS = "";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement st = conn.createStatement()) {

            System.out.println("--- Inicio de creación de Base de Datos ---");

            // 1. REINICIAR: Borra la tabla si existe para empezar de cero siempre
            st.execute("DROP TABLE IF EXISTS JUEGO");
            System.out.println("Tabla anterior borrada (si existía).");

            // 2. CREAR TABLA
            String crearTabla = """
                CREATE TABLE JUEGO (
                    ID INT AUTO_INCREMENT PRIMARY KEY,
                    NOMBRE VARCHAR(100),
                    GENERO VARCHAR(50),
                    NOTA DOUBLE
                );
                """;
            st.execute(crearTabla);
            System.out.println("Tabla JUEGO preparada.");

            // 3. INSERTAR DATOS
            String sqlInsert1 = "INSERT INTO JUEGO (NOMBRE, GENERO, NOTA) VALUES ('The Legend of Zelda', 'Aventura', 9.7)";
            String sqlInsert2 = "INSERT INTO JUEGO (NOMBRE, GENERO, NOTA) VALUES ('FIFA', 'Deportes', 9.0)";
            String sqlInsert3 = "INSERT INTO JUEGO (NOMBRE, GENERO, NOTA) VALUES ('Monster Hunter Wilds', 'Acción RPG', 9.5)";
            String sqlInsert4 = "INSERT INTO JUEGO (NOMBRE, GENERO, NOTA) VALUES ('Gran Turismo 7', 'Conducción', 8.7)";

            // Ejecutamos las inserciones
            st.executeUpdate(sqlInsert1);
            st.executeUpdate(sqlInsert2);
            st.executeUpdate(sqlInsert3);
            st.executeUpdate(sqlInsert4);

            System.out.println("--- Fin del proceso ---");

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }
}
