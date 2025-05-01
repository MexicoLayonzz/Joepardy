package backend;

import java.util.ArrayList;

public class Materia 
{
    private String nombre;
    private String tema;
    private ArrayList<Pregunta> preguntas;
    
    public Materia(String nombre, String tema) {
        this.nombre = nombre;
        this.tema = tema;
        this.preguntas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public void addPregunta(Pregunta pregunta) {
        this.preguntas.add(pregunta);
    }
    
    public void removePregunta(Pregunta pregunta) {
        this.preguntas.remove(pregunta);
    }
}
