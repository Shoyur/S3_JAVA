package edu.info;

import org.json.JSONArray;
import org.json.JSONObject;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        JSONArray listeLivres = Connexion.getInstance().obtenirReponseEnJSONArray("http://localhost:8383/livres");
        // Afficher le titre de tous les livres
        JSONObject unLivre;
        for (int i = 0; i < listeLivres.length(); i++) {
            unLivre = (JSONObject) listeLivres.get(i);
            System.out.println(unLivre.get("Titre"));
        }
    }
}
