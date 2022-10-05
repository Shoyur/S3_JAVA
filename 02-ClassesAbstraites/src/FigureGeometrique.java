/**
 * classe abstraite FigureGeometrique
 * 1. cette classe contient au moins une méthode abstraite
 * la classe est donc abstraite
 * 2. On n'a pas de formule pour caculer le périmètre
 * ni de surface de n'importe quelle figure.
 * À cette étape-ci, les deux méthodes sont encore abstraites
 * 3. On n'a pas le droit d'avoir des objets construits (avec new)
 * d'une classe abstraite
 * 4. On a le droit d'avoir des méthodes non abstraites (concrètes)
 * (la réalisation de la méthode est faite au complet,
 * exemple toString() de FigureGeometrique)
 */
public abstract class FigureGeometrique {
    public abstract double perimetre();

    public abstract double surface();

    public String toString() {
        return "Perimetre : " + perimetre() + "\n" +
                "Surface : " + surface() + "\n";
    }
}
