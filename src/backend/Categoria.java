package backend;

import java.util.ArrayList;

public class Categoria 
{
    private String nombre;
    private ArrayList<Pregunta> preguntas;

    public Categoria(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public ArrayList<Pregunta> getPreguntas() 
    {
        return preguntas;
    }

    public Categoria agregarCategoria(String nombre) 
    {
        return new Categoria(nombre);
    }

}
