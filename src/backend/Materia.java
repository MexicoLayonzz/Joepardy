package backend;


import java.util.ArrayList;

public class Materia 
{
    private String nombre;
    private ArrayList<String> temas;
    private ArrayList<Pregunta> preguntas;

    public Materia(String nombre) {
        this.nombre = nombre;
        this.temas = new ArrayList<>();
        this.preguntas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getTemas() {
        return temas;
    }

    public void setTemas(ArrayList<String> temas) {
        this.temas = temas;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public void addTema(String tema) {
        this.temas.add(tema);
    }

    public void addPregunta(Pregunta pregunta) {
        this.preguntas.add(pregunta);
    }
}
