package backend;

public class Pregunta 
{
    private String pregunta;
    private String respuesta;
    private Dificultad dificultad;

    public Pregunta(String pregunta, String respuesta, Dificultad dificultad) 
    {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.dificultad = dificultad;
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

    public Dificultad getDificultad() 
    {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) 
    {
        this.dificultad = dificultad;
    }

    public static Pregunta agregarPregunta(String pregunta, String respuesta, Dificultad dificultad) 
    {
        return new Pregunta(pregunta, respuesta, dificultad);
    }

    public static Pregunta eliminarPregunta(String pregunta, String respuesta, Dificultad dificultad) 
    {
        return new Pregunta(pregunta, respuesta, dificultad);
    }

    public static Pregunta modificarPregunta(String pregunta, String respuesta, Dificultad dificultad) 
    {
        return new Pregunta(pregunta, respuesta, dificultad);
    }
    

}
