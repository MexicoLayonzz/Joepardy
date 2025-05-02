
import backend.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import frontend.*;

import javax.swing.SwingUtilities;

public class App 
{
    public static void main(String[] args) throws Exception 
    {

        ArrayList<String> tem = new ArrayList<>();
        tem.add("Algebra");
        tem.add("Geometria");
        tem.add("Trigonometria");        
        Materia materia = new Materia("Matematicas");

        for (String tema : tem) {
            materia.addTema(tema);
        }
        materia.addPregunta(new Pregunta("200", "Punto cruz", "¿A*B = B*A teniendo que A y B son dos matrices de tamaño n?", "No son iguales"));
        materia.addPregunta(new Pregunta("100", "Determinante", "¿Como se saca el determinante?", "Por la regla de Sarrus"));

        JSONArray preguntasJson = new JSONArray();
        for (Pregunta p : materia.getPreguntas()) {
            JSONObject obj = new JSONObject();
            obj.put("valor", p.getDificultad());
            obj.put("categoria", p.getCategoria());
            obj.put("pregunta", p.getPregunta());
            obj.put("respuesta", p.getRespuesta());
            preguntasJson.put(obj);
        }

        JSONArray temasJson = new JSONArray();
        for (String tema : materia.getTemas()) {
            JSONObject obj = new JSONObject();
            obj.put("tema", tema);
        }

        JSONObject materiaJson = new JSONObject();
        materiaJson.put("materia", materia.getNombre());
        materiaJson.put("categoria", temasJson);
        materiaJson.put("preguntas", preguntasJson);

        try (FileWriter file = new FileWriter("materia.json")) {
            file.write(materiaJson.toString(4)); // Indent with 4 spaces
            System.out.println("JSON file created: " + materiaJson.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Read the JSON file and print its content

        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal(); // Assuming VentanaPrincipal is a JFrame class
        });
    }
}
