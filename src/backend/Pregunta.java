package backend;

public class Pregunta 
{
    private String dificultad;
    private String categoria;
    private String pregunta;
    private String respuesta;

    public Pregunta(String dificultad, String categoria, String pregunta, String respuesta) {
        this.dificultad = dificultad;
        this.categoria = categoria;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

}
