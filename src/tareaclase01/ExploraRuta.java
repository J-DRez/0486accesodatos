package tareaclase01;
import java.io.File;

public class ExploraRuta {
    public static void main(String[] args) {

//        if (args.length == 0) {
//            System.out.println("Debes introducir la ruta como argumento.");
//            System.out.println("Ejemplo: C:\\MiPC\\Documentos\\Descargas\\Tarea_1.pdf");
//            return;
//        }
//
//        String ruta = args[0];

        String ruta = "D:\\PC_Julio\\Documentos\\UPGRADE\\0486 - Acceso a Datos\\01_Acceso a ficheros\\Tarea_1.pdf";

        File f = new File(ruta);

        if (f.exists()) {
            System.out.print("El elemento existe ");

            // Compruebo si es un archivo
            if (f.isFile()) {
                System.out.println("y es un fichero." +
                        "\nNombre: " + f.getName() +
                        "\nRuta absoluta: " + f.getAbsolutePath() +
                        "\nTamaño: " + f.length() + " bytes.");
            }

            if (f.canRead()) {
                System.out.println("El archivo se puede leer.");
            } else {
                System.out.println("El archivo no se puede leer.");
            }

            if (f.canWrite()) {
                System.out.println("El archivo se puede escribir.");
            } else {
                System.out.println("El archivo no se puede escribir.");
            }

            // Compruebo si es un directorio
            if (f.isDirectory()) {
                System.out.println("y es un directorio.");
                String[] contenido = f.list();

                // Compruebo si el directorio tiene o no archivos
                if (contenido.length == 0) {
                    System.out.println("El directorio está vacío.");
                } else {
                    if (contenido.length == 1) {
                        System.out.println("Contiene " + contenido.length + " elemento.");
                    } else {
                        System.out.println("Contiene " + contenido.length + " elementos.");
                    }

                    for (String nombre : contenido) {
                        System.out.println("- " + nombre);
                    }
                }
            }

        } else {
            System.out.println("El fichero o directorio no existe.");
        }
    }
}
