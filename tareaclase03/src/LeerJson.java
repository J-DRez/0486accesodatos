import org.json.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class LeerJson {
    public static void main(String[] args) {
        List<Pelicula> lista = new ArrayList<>();

        try (FileReader fr = new FileReader("src\\tareaclase03\\peliculas.json")) {

            JSONTokener tokener = new JSONTokener(fr);

            JSONObject root = new JSONObject(tokener);

            JSONArray listaPeliculas = root.getJSONArray("películas");

            for (int i = 0; i < listaPeliculas.length(); i++) {
                JSONObject pelicula = listaPeliculas.getJSONObject(i);

                String titulo = pelicula.getString("título");
                String director = pelicula.getString("director");
                int anio = pelicula.getInt("año");

                Pelicula p = new Pelicula(titulo, director, anio);
                lista.add(p);

                System.out.println("Título: " + titulo + ", Director: " + director + ", Año: " + anio);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo 'peliculas.json'.");
        } catch (JSONException e) {
            System.out.println("JSON mal formado o clave/valor inesperado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        }
    }
}
