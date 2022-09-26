public class BiblioTab extends Bibliotheque {

    private static int tab[] = new int [20];

    BiblioTab () {}
    
    public String toString() {
        String string = "La bibliothèque contient " + Bibliotheque.cote + " ouvrages.\r";

        // TODO !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // for (i in BiblioTab) { 
        //     string += this.toString() + "\r"; 
        // }

        return string;
    }

}
