import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        System.out.println("---- TABLA JUEGOS ----");
        System.out.println("""
                
                Escoja una opción:\
                
                1. Mostrar juegos.\
                
                2. Insertar juego nuevo.\
                
                3. Actualizar puntuación.\
                
                4. Eliminar juego.\
                
                5. Salir.""");

        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 5) {
            System.out.print("\n--> ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Opción 1");
                    break;
                case 2:
                    System.out.println("Opción 2");
                    break;
                case 3:
                    System.out.println("Opción 3");
                    break;
                case 4:
                    System.out.println("Opción 4");
                    break;
                case 5:
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.err.println("Introduce una opción de la lista.");
            }
        }
    }
}
