import backend.*;
import frontend.*;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class App 
{
    public static void main(String[] args) throws Exception 
    {

        //------------------------------Mostrar la Ventana-----------------------------
        new MainWindow().setVisible(true); 
        //-------------------------------Fin de la Ventana-----------------------------


    }

    public static void CreacionArchivo(String rutaFichero, Materia materia) 
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Crear un objeto Gson para convertir a JSON

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(rutaFichero))) 
        {
            gson.toJson(materia, bw); // Escribir el contenido en el archivo
        } catch (IOException e) 
        {
            e.printStackTrace(); // Manejo de excepciones
        }
    }

    public static void leerArchivo(String rutaFichero) 
    {
        Gson gson = new Gson(); // Crear un objeto Gson para leer el JSON

        try(BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) 
        {
            Materia materia = gson.fromJson(br, Materia.class); // Leer el contenido del archivo
            System.out.println(materia); // Mostrar el contenido en consola
        } catch (IOException e) 
        {
            e.printStackTrace(); // Manejo de excepciones
        }
    }
}
