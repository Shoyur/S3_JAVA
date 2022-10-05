/**
 * classe TestAbstraite
 * Pour cet exemple, on se limite au minimum pour faire
 * ressortir le concept de classe abstraite.
 *
 * Exercice :
 * ajouter une classe Triangle. Pourquoi est-elle abstraite ?
 * ajouter une sous-classe TriangleRectangle.
 * Pourquoi est-elle concrète ?
 * Tester le bon fonctionnement
 *
 *
 *
 * Les classes abstraites se trouvent à plusieurs places
 * dans les paquets de Java (comme java.io, java.lang, ...).
 * La gestion des fichiers (textes, binaires, ...) utilise
 * certaines classes abstraites.
 */
public class TestAbstraite {
    static double surfaceTotale(FigureGeometrique[] tableau) {
        double totSurface = 0.0;
        for (int i = 0; i < tableau.length; i++)
            totSurface += tableau[i].surface();
        return totSurface;
    }

    public static void main(String[] args) {
        /*
         * Message d'erreur du genre suivant:
         * L'operateur new est interdit pour une instance de la
         * classe abstraite FigureGeometrique
         * FigureGeometrique fg = new FigureGeometrique();
         */
        Rectangle2 rect1 = new Rectangle2(10.0, 6.5);
        System.out.println("Infos de rect1 :\n" + rect1);
        Cercle c1 = new Cercle(10.0);
        System.out.println("Infos du cercle c1 :\n" + c1);
        FigureGeometrique[] tabFG = { new Rectangle2(10.0, 4.0),
                new Cercle(5.0),
                new Rectangle2(8.6, 4.4),
                new Rectangle2(14.2, 10.8),
                new Carre2(6.0) };
        System.out.println("Surface totale pour acheter " +
                "de la peinture: " +
                surfaceTotale(tabFG));
    }
}
/*
 * Exécution:
 * Infos de rect1 :
 * Rectangle : <longueur = 10.0, largeur = 6.5>
 * Perimetre : 33.0
 * Surface : 65.0
 * Infos du cercle c1 :
 * Cercle de rayon 10.0
 * Perimetre : 62.83185307179586
 * Surface : 314.1592653589793
 * Surface totale pour acheter de la peinture: 345.73981633974483
 */