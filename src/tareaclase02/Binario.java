package tareaclase02;

import java.io.*;

public class Binario {
    public static void main(String[] args) {

        String ruta = "texto.bin";

        try (DataOutputStream n = new DataOutputStream(new FileOutputStream(ruta))) {

            n.writeInt(23);
            n.writeUTF("Texto");
            n.writeDouble(1.2345);

            System.out.println("Datos escritos en " + ruta);
        } catch (IOException e) {
            System.out.println("Error de escritura: " + e.getMessage());
        }

        try (DataInputStream m = new DataInputStream(new FileInputStream(ruta))){

            int i = m.readInt();
            String s = m.readUTF();
            double d = m.readDouble();

            System.out.println("Leído:\n- i = " + i + "\n- s = " + s + "\n- d = " + d);

        } catch (IOException e){
            System.out.println("Error de lectura: " + e.getMessage());
        }
    }
}
