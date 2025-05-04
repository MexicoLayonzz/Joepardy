package backend;

public class Pregunta 
{
    private String pregunta;
    private String respuesta;
    private Categoria categoria;

    public Pregunta(String pregunta, String respuesta, Categoria categoria) 
    {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.categoria = categoria;
    }

    public String getPregunta() 
    {
        return pregunta;
    }

    public void setPregunta(String pregunta) 
    {
        this.pregunta = pregunta;
    }

    public String getRespuesta() 
    {
        return respuesta;
    }

    public void setRespuesta(String respuesta) 
    {
        this.respuesta = respuesta;
    }

    public Categoria getCategoria() 
    {
        return categoria;
    }

    public void setCategoria(Categoria categoria) 
    {
        this.categoria = categoria;
    }

    

}
