package tareaclase03;

import org.json.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EscribirJson {
    public static void main(String[] args) {

        List<Pelicula> peliculas = Arrays.asList(
                new Pelicula("La Jungla de Cristal", "John McTiernan",1988),
                new Pelicula("Harry Potter y la piedra filosofal","Chris Columbus",2001),
                new Pelicula("Wicked","Jon M. Chu",2024)
        );

        JSONObject root = new JSONObject();

        JSONArray listaPeliculas = new JSONArray();

        for (Pelicula p : peliculas) {
            JSONObject jsonPelicula = new JSONObject();

            jsonPelicula.put("título", p.getTitulo());
            jsonPelicula.put("director", p.getDirector());
            jsonPelicula.put("año", p.getAnio());

            listaPeliculas.put(jsonPelicula);
        }

        root.put("películas", listaPeliculas);

        try (FileWriter fw = new FileWriter("src/main/resources/peliculas.json")) {
            fw.write(root.toString(4));
            System.out.println("JSON escrito en peliculas.json");
        } catch (IOException e) {
            System.out.println("Error escribiendo JSON: " + e.getMessage());
        }

        System.out.println("\n--- JSON generado ---");
        // System.out.println(root.toString(2));
    }
}
