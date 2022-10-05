/**
 * Les 2 méthodes :
 * perimetre() : implémentée ici (réaliser de A à Z)
 * surface() : implémentée ici (réaliser de A à Z)
 * La classe Cercle n'est pas abstraite. Elle est
 * concrète.
 */
public class Cercle extends FigureGeometrique {
    private double rayon;

    public Cercle(double rayon) {
        this.rayon = rayon;
    }

    public double perimetre() {
        return 2 * Math.PI * rayon;
    }

    public double surface() {
        return Math.PI * rayon * rayon;
    }

    public String toString() {
        return "Cercle de rayon " + rayon + "\n" +
                super.toString();
    }
}