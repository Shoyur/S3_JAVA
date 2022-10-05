/**
 * Pour un parralelogramme (côtés opposés parallèles) :
 * - le calcul du périmètre est concret : 2 x (côté1 + côté2)
 * - le calcul de la surface est encore abstraite
 * la classe Parallelogramme est encore abstraite
 *
 * On n'a pas le droit d'avoir d'objets construits avec new
 * pour la classe abstraite Parallelogramme
 * Cependant, on a le droit d'avoir des constructeurs!
 * (pour appeler avec this(...) ou super(....)
 */
public abstract class Parallelograme extends FigureGeometrique {
    private double cote1, cote2;

    public Parallelograme(double cote1, double cote2) {
        this.cote1 = cote1;
        this.cote2 = cote2;
    }

    public double perimetre() {
        return 2 * (cote1 + cote2);
    }

    // public abstract double surface();
    public double getCote1() {
        return cote1;
    }

    public double getCote2() {
        return cote2;
    }
}
