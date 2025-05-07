package backend;

import java.util.*;

public class Categoria 
{
    private String nombre;
    private List<Pregunta> preguntas;

    public Categoria(String nombre) 
    {
        this.nombre = nombre;
        this.preguntas = new ArrayList<>();
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public List<Pregunta> getPreguntas() 
    {
        return preguntas;
    }

    public static Categoria add(String nombre) 
    {
        return new Categoria(nombre);
    }

    public void agregarPreguntas(List<Pregunta> nuevasPreguntas) 
{
        if (preguntas == null) 
        {
            preguntas = new ArrayList<>();
        }
        preguntas.addAll(nuevasPreguntas);
    }   
}
