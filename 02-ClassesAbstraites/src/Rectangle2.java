/**
 
 * Les 2 méthodes :
 * perimetre() : héritée de Parallelogramme
 * surface() : implémentée ici (réaliser de A à Z)
 * La classe Rectangle2 n'est plus abstraite. Elle est
 * concrète.
 */
public class Rectangle2 extends Parallelograme {
    // appel le constructeur d'une super-classe (qui est abstraite!)
    public Rectangle2(double lo, double la) {
        super(lo, la);
        // ainsi côté1 <-> longueur,côté2 <-> largeur
    }

    public double surface() {
        return getCote1() * getCote2();
    }

    public String toString() {
        return "Rectangle : <longueur = " + getCote1() +
                ", largeur = " + getCote2() + ">\n" +
                super.toString();
        // appel d'une méthode de la super-classe
        /*
         * notez que Java ne trouve pas toString() dans
         * Parallelogramme. Il va monter jusqu'à FigureGeometrique
         * et applique le toString() de FigureGeometrique
         * C'est le polymorphisme : appliquer la bonne méthode
         */
    }
}

