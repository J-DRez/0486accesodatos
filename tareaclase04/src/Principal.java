import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Principal {

    private static final String URL  = "jdbc:h2:./tareaclase04/src/BDJuegos";
    private static final String USER = "sa";
    private static final String PASS = "";

    public static void main(String[] args) {

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Driver no encontrado. " + e.getMessage());
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Conexión establecida con BDJuegos.\n");
            boolean ejecutando = true;

            while (ejecutando) {
                mostrarMenu();

                int opcion = leerEntero(sc);

                switch (opcion) {
                    case 1:
                        listarJuegos(conn);
                        ejecutando = gestionarMenuPostAccion(sc);
                        break;
                    case 2:
                        insertarJuego(conn, sc);
                        ejecutando = gestionarMenuPostAccion(sc);
                        break;
                    case 3:
                        actualizarNota(conn, sc);
                        ejecutando = gestionarMenuPostAccion(sc);
                        break;
                    case 4:
                        eliminarJuego(conn, sc);
                        ejecutando = gestionarMenuPostAccion(sc);
                        break;
                    case 5:
                        if (confirmarSalida(sc)) {
                            ejecutando = false;
                            System.out.println("¡Hasta pronto!");
                        }
                        break;
                    default:
                        System.err.println("Opción no válida. Inténtelo de nuevo.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar la base de datos.");
            System.out.println("Error SQL -> " + e.getMessage());
        }
    }

    private static boolean gestionarMenuPostAccion(Scanner sc) {
        while (true) {
            System.out.println("\n------------------------------------------------------");
            System.out.println("   [M] Menú Principal");
            System.out.println("   [S] Salir");
            System.out.print("--> ");

            String respuesta = sc.nextLine().trim();

            if (respuesta.equalsIgnoreCase("M")) {
                return true;
            }
            else if (respuesta.equalsIgnoreCase("S")) {
                if (confirmarSalida(sc)) {
                    return false;
                }
            }
            else {
                System.err.println("Opción desconocida.");
            }
        }
    }

    private static boolean confirmarSalida(Scanner sc) {
        while (true) {
            System.out.print("¿Está seguro que desea salir? (y/n): ");
            String confirmacion = sc.nextLine().trim().toLowerCase();

            if (confirmacion.equals("y")) {
                return true;
            } else if (confirmacion.equals("n")) {
                return false;
            } else {
                System.out.println("Por favor, responda 'y' para sí o 'n' para no.\n");
            }
        }
    }

    private static int leerEntero(Scanner sc) {
        try {
            String input = sc.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n---- GESTIÓN DE JUEGOS ----");
        System.out.println("1. Mostrar juegos");
        System.out.println("2. Insertar juego nuevo");
        System.out.println("3. Actualizar Nota");
        System.out.println("4. Eliminar juego");
        System.out.println("5. Salir");
        System.out.print("--> ");
    }

    private static void listarJuegos(Connection conn) throws SQLException {
        String sql = "SELECT * FROM JUEGO";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n>>> LISTADO DE JUEGOS <<<");
            System.out.printf("%-5s %-20s %-15s %-10s%n", "ID", "NOMBRE", "GÉNERO", "NOTA");
            System.out.println("-------------------------------------------------------");

            boolean hayDatos = false;

            while (rs.next()) {
                hayDatos = true;
                System.out.printf("%-5d %-20s %-15s %-10.1f%n",
                        rs.getInt("ID"),
                        rs.getString("NOMBRE"),
                        rs.getString("GENERO"),
                        rs.getDouble("NOTA"));
            }
            if (!hayDatos) System.out.println("(La tabla está vacía)");
        }
    }

    private static void insertarJuego(Connection conn, Scanner sc) throws SQLException {
        System.out.println("\n>>> INSERTAR JUEGO <<<");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Género: ");
        String genero = sc.nextLine();

        System.out.print("Nota (use coma para decimales): ");
        double puntos = sc.nextDouble();

        sc.nextLine(); // Limpieza de buffer

        String sql = "INSERT INTO JUEGO (NOMBRE, GENERO, NOTA) VALUES (?, ?, ?)";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nombre);
            pst.setString(2, genero);
            pst.setDouble(3, puntos);
            pst.executeUpdate();
            System.out.println("Juego insertado correctamente.");
        }
    }

    private static void actualizarNota(Connection conn, Scanner sc) throws SQLException {
        System.out.println("\n>>> ACTUALIZAR NOTA <<<");
        System.out.print("ID del juego a modificar: ");
        int id = sc.nextInt();

        System.out.print("Nueva nota: ");
        double puntos = sc.nextDouble();

        sc.nextLine(); // Limpieza de buffer

        String sql = "UPDATE JUEGO SET NOTA = ? WHERE ID = ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setDouble(1, puntos);
            pst.setInt(2, id);

            int filas = pst.executeUpdate();
            if (filas > 0) {
                System.out.println("Nota actualizada.");
            } else {
                System.err.println("No se encontró un juego con ID " + id);
            }
        }
    }

    private static void eliminarJuego(Connection conn, Scanner sc) throws SQLException {
        System.out.println("\n>>> ELIMINAR JUEGO <<<");
        System.out.print("ID del juego a eliminar: ");
        int id = sc.nextInt();

        sc.nextLine(); // Limpieza de buffer

        String sql = "DELETE FROM JUEGO WHERE ID = ?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);

            int filas = pst.executeUpdate();
            if (filas > 0) {
                System.out.println("Juego eliminado.");
            } else {
                System.err.println("No existe el ID " + id);
            }
        }
    }
}
