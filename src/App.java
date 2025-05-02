
import backend.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class App 
{
    public static void main(String[] args) throws Exception 
    {
        Materia materia = new Materia("Matematicas", "Algebra");
        materia.addPregunta(new Pregunta("200", "Punto cruz", "¿A*B = B*A teniendo que A y B son dos matrices de tamaño n?", "No son iguales"));
        materia.addPregunta(new Pregunta("100", "Determinante", "¿Como se saca el determinante?", "Por la regla de Sarrus"));

        JSONArray jsonArray = new JSONArray(materia.getPreguntas());
    }
}
