package backend;


import java.util.ArrayList;

public class Materia 
{
    private String nombre;
    private ArrayList<Categoria> categorias;

    public Materia(String nombre) 
    {
        this.nombre = nombre;
        this.categorias = new ArrayList<>();
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public ArrayList<Categoria> getCategorias() 
    {
        return categorias;
    }

    public void setCategorias(ArrayList<Categoria> categorias)
    {
        this.categorias = categorias;
    }

    public void agregarCategoria(Categoria categoria) 
    {
        this.categorias.add(categoria);
    }
    
    public void eliminarCategoria(Categoria categoria) 
    {
        this.categorias.remove(categoria);
    }
}
