// Révision de l'héritage, du polymorphisme
public class Carre2 extends Rectangle2 {
    public Carre2(double cote) {
        super(cote, cote);
    }

    public double diagonale() {
        return getCote1() * Math.sqrt(2.0);
    }

    public String toString() {
        return super.toString() + "Diagonale : " +
                diagonale() + "\n";
    }
}
