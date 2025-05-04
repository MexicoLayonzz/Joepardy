
import backend.*;
import frontend.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.io.BufferedWriter;

public class App 
{
    public static void main(String[] args) throws Exception 
    {
        //-----------------------------Creación de la Datos-----------------------------
        //Creación de una materia
        Materia materia = new Materia("Matemáticas");
        Categoria algebra = new Categoria("Álgebra");
        Categoria calculo = new Categoria("Cálculo");

        materia.agregarCategoria(algebra);
        materia.agregarCategoria(calculo);

        //Creación de dificultades
        Dificultad facil = new Dificultad(100);
        Dificultad medio = new Dificultad(200);
        Dificultad dificil = new Dificultad(300);
        Dificultad muyDificil = new Dificultad(400);
        Dificultad extremo = new Dificultad(500);

        //Creación de preguntas
        ArrayList<Pregunta> preAlgebra = new ArrayList<Pregunta>() {{
            add(Pregunta.agregarPregunta("¿Qué es 2 + 2?", "4", facil));
            add(Pregunta.agregarPregunta("¿Qué es 3 * 3?", "9", medio));
            add(Pregunta.agregarPregunta("¿Qué es la derivada de x^2?", "2x", dificil));
            add(Pregunta.agregarPregunta("¿Qué es la integral de 1/x?", "ln|x| + C", muyDificil));
            add(Pregunta.agregarPregunta("¿Qué es el límite de (1/x) cuando x tiende a 0?", "infinito", extremo));
        }};

        ArrayList<Pregunta> preCalculo = new ArrayList<Pregunta>() {{
            add(Pregunta.agregarPregunta("¿Qué es el seno de 90 grados?", "1", facil));
            add(Pregunta.agregarPregunta("¿Qué es el coseno de 0 grados?", "1", medio));
            add(Pregunta.agregarPregunta("¿Qué es la tangente de 45 grados?", "1", dificil));
            add(Pregunta.agregarPregunta("¿Qué es la integral de sen(x)?", "-cos(x) + C", muyDificil));
            add(Pregunta.agregarPregunta("¿Qué es el límite de sen(x)/x cuando x tiende a 0?", "1", extremo));
        }};
        
        algebra.agregarPreguntas(preAlgebra);
        calculo.agregarPreguntas(preCalculo);
        //-----------------------------Fin de la Datos-----------------------------

        //-----------------------------Creación de Archivo-----------------------------
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Crear un objeto Gson para convertir a JSON

        String rutaFichero = "storage/materia.json"; // Ruta del archivo de texto

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(rutaFichero))) {
            gson.toJson(materia, bw); // Escribir el contenido en el archivo
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones
        }

        
        //------------------------------Fin de Archivo -----------------------------


    }
}
