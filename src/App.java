import backend.*;
import frontend.*;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import javax.swing.JOptionPane;
//import javax.swing.SwingUtilities;
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
        editarPreguntaGeometria("storage/matematicas.json"); // Llamar al método para editar la pregunta de geometría

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

    public static void editarPreguntaGeometria(String rutaFichero) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            // Leer el objeto Materia completo desde el JSON
            Materia materia = gson.fromJson(br, Materia.class);
    
            // Buscar la categoría "Geometría"
            for (Categoria categoria : materia.getCategorias()) {
                if (categoria.getNombre().equalsIgnoreCase("Geometría")) {
                    // Buscar la pregunta que queremos editar
                    for (Pregunta pregunta : categoria.getPreguntas()) {
                        if (pregunta.getPregunta().equalsIgnoreCase("¿Qué es un ángulo?")) {
                            // Cambiar el texto de la pregunta
                            pregunta.setPregunta("¿Qué es un ángulo recto?");
                            break;
                        }
                    }
                }
            }
    
            // Escribir el objeto actualizado en el archivo
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaFichero))) {
                gson.toJson(materia, bw);
                System.out.println("Pregunta modificada correctamente.");
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
