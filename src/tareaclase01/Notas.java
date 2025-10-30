package tareaclase01;
import java.io.*;
import java.util.Scanner;

public class Notas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String ruta = "D:\\PC_Julio\\Documentos\\GitHub\\0486accesodatos\\src\\tareaclase01\\texto.txt";
        System.out.println("- Escribe 3 lineas de texto:");

        try {
            FileWriter fw = new FileWriter(ruta);
            BufferedWriter bw = new BufferedWriter(fw);
            System.out.println("Primera linea:");
            bw.write(sc.nextLine());
            bw.newLine();
            System.out.println("Segunda linea:");
            bw.write(sc.nextLine());
            bw.newLine();
            System.out.println("Tercera linea:");
            bw.write(sc.nextLine());
            bw.close();
            System.out.println("Fichero escrito correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }

        System.out.println("\n- El fichero contiene el siguiente texto:");
        File f = new File(ruta);
        int contadorLineas = 1;
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(contadorLineas + ". " + linea);
                contadorLineas++;
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }

    }
}
