package backend;

public class Dificultad {
    private int puntos;

    // Constantes para las dificultades
    public static final Dificultad FACIL = new Dificultad(100);
    public static final Dificultad MEDIO = new Dificultad(200);
    public static final Dificultad DIFICIL = new Dificultad(300);
    public static final Dificultad MUY_DIFICIL = new Dificultad(400);
    public static final Dificultad EXTREMO = new Dificultad(500);

    public Dificultad(int puntos) {
        this.puntos = puntos;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}